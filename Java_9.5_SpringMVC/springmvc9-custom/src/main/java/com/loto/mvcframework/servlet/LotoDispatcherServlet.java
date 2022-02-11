package com.loto.mvcframework.servlet;

import com.loto.mvcframework.annotations.LotoAutowired;
import com.loto.mvcframework.annotations.LotoController;
import com.loto.mvcframework.annotations.LotoRequestMapping;
import com.loto.mvcframework.annotations.LotoService;
import com.loto.mvcframework.pojo.Handler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-06 16:18</p>
 * <p>PageName：servlet.java</p>
 * <p>Function：</p>
 */

public class LotoDispatcherServlet extends HttpServlet {
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>(); // 缓存扫描到的类的全限定类名
    private Map<String, Object> ioc = new HashMap<String, Object>();  // ioc容器
    //private Map<String,Method> handlerMapping = now HashMap<>(); // 存储url和Method之间的映射关系
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1 加载配置文件 springmvc.properties
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        doLoadConfig(contextConfigLocation);

        // 2 扫描相关的类，扫描注解
        doScan(properties.getProperty("scanPackage"));

        // 3 初始化bean对象（实现ioc容器，基于注解）
        doInstance();

        // 4 实现依赖注入
        doAutoWired();

        // 5 构造 HandlerMapping 处理器映射器，将配置好的 url和Method 建立映射关系
        initHandlerMapping();

        System.out.println("Loto mvc 初始化完成....");

        // 等待请求进入，处理请求
    }

    /**
     * 加载配置文件
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描类
     */
    private void doScan(String scanPackage) {
        // 拿到 classpath 的路径（com.loto.mvcframework  转换成 com/loto/mvcframework）
        String scanPackagePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + scanPackage.replaceAll("\\.", "/");
        File pack = new File(scanPackagePath);

        File[] files = pack.listFiles();
        for (File file : files) {
            // 判断，如果有子文件夹，则递归调用
            if (file.isDirectory()) {
                doScan(scanPackage + "." + file.getName());  // com.Loto.demo.controller
            } else if (file.getName().endsWith(".class")) {
                // 否则，判断是否是以 .class 结尾
                // 获取全限定类名
                String className = scanPackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    /**
     * 初始化 bean 对象（实现 ioc 容器）<p>
     * 基于 classNames 缓存的类的全限定类名，以及反射技术，完成对象创建和管理
     */
    private void doInstance() {
        if (classNames.size() == 0) return;

        try {
            for (int i = 0; i < classNames.size(); i++) {
                // com.loto.example.controller.QueryController
                String className = classNames.get(i);

                // 反射
                Class<?> aClass = Class.forName(className);

                // 区分 controller 和 service
                if (aClass.isAnnotationPresent(LotoController.class)) {
                    // controller 的id此处不做过多处理，不取value了，拿类的首字母小写作为id，保存到ioc中
                    String simpleName = aClass.getSimpleName();           // QueryController
                    String lowerFirstSimpleName = lowerFirst(simpleName); // queryController

                    Object o = aClass.newInstance();
                    ioc.put(lowerFirstSimpleName, o);
                } else if (aClass.isAnnotationPresent(LotoService.class)) {
                    // 获取注解
                    LotoService annotation = aClass.getAnnotation(LotoService.class);

                    // 获取注解的 value 值
                    String beanName = annotation.value();

                    // 注解的 value 值不为空，表示指定了id，以指定的为准
                    if (!"".equals(beanName.trim())) {
                        ioc.put(beanName, aClass.newInstance());
                    } else {
                        // 注解的 value 值为空，表示没有指定，就以类名首字母小写
                        beanName = lowerFirst(aClass.getSimpleName());
                        ioc.put(beanName, aClass.newInstance());
                    }

                    // service 层往往是有接口的，面向接口开发，此时再以接口名为id，放入一份对象到ioc中，便于后期根据接口类型注入
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (int j = 0; j < interfaces.length; j++) {
                        Class<?> anInterface = interfaces[j];
                        // 以接口的全限定类名作为 id 放入
                        ioc.put(anInterface.getName(), aClass.newInstance());
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现依赖注入
     */
    private void doAutoWired() {
        if (ioc.isEmpty()) {
            return;
        }

        // 有对象，再进行依赖注入处理

        // 遍历ioc中所有对象，查看对象中的字段，是否有@LotoAutowired注解，如果有需要维护依赖注入关系
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 获取bean对象中的字段信息
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();

            // 遍历判断处理
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                if (!declaredField.isAnnotationPresent(LotoAutowired.class)) {
                    continue;
                }

                // 有该注解
                LotoAutowired annotation = declaredField.getAnnotation(LotoAutowired.class);
                String beanName = annotation.value();  // 需要注入的 bean 的 id
                if ("".equals(beanName.trim())) {
                    // 没有配置具体的bean id，那就需要根据当前字段类型注入（接口注入）
                    beanName = declaredField.getType().getName();
                }

                // 开启赋值
                declaredField.setAccessible(true);

                try {
                    declaredField.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 构造 HandlerMapping 处理器映射器，将配置好的 url和Method 建立映射关系
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 获取ioc中当前遍历的对象的class类型
            Class<?> aClass = entry.getValue().getClass();

            if (!aClass.isAnnotationPresent(LotoController.class)) {
                continue;
            }

            String baseUrl = "";
            if (aClass.isAnnotationPresent(LotoRequestMapping.class)) {
                LotoRequestMapping annotation = aClass.getAnnotation(LotoRequestMapping.class);
                // 等同于/loto
                baseUrl = annotation.value();
            }

            // 获取方法
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];

                //  方法没有标识 LotoRequestMapping，就不处理
                if (!method.isAnnotationPresent(LotoRequestMapping.class)) {
                    continue;
                }

                // 如果标识，就处理
                LotoRequestMapping annotation = method.getAnnotation(LotoRequestMapping.class);
                String methodUrl = annotation.value();  // /query
                String url = baseUrl + methodUrl;       // 计算出来的url：/loto/query

                // 把 method 所有信息及 url 封装为一个 Handler
                Handler handler = new Handler(entry.getValue(), method, Pattern.compile(url));

                // 计算方法的参数位置信息
                // query(HttpServletRequest request, HttpServletResponse response, String name)
                Parameter[] parameters = method.getParameters();
                for (int j = 0; j < parameters.length; j++) {
                    Parameter parameter = parameters[j];

                    // 如果是 request 和 response 对象，那么参数名称写 HttpServletRequest 和 HttpServletResponse
                    if (parameter.getType() == HttpServletRequest.class || parameter.getType() == HttpServletResponse.class) {
                        handler.getParamIndexMapping().put(parameter.getType().getSimpleName(), j);
                    } else {
                        // <name,2>
                        handler.getParamIndexMapping().put(parameter.getName(), j);
                    }
                }

                // 建立 url 和 method 之间的映射关系（map缓存起来）
                handlerMapping.add(handler);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求：根据url，找到对应的Method方法，进行调用

        // 根据 uri 获取到能够处理当前请求的 hanlder（从 handlermapping 中）
        Handler handler = getHandler(req);

        if (handler == null) {
            resp.getWriter().write("404 not found");
            return;
        }

        // 参数绑定
        // 获取所有参数类型数组，这个数组的长度就是最后要传入的args数组的长度
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();

        // 根据上述数组长度创建一个新的数组（参数数组，是要传入反射调用的）
        Object[] paraValues = new Object[parameterTypes.length];

        // 以下就是为了向参数数组中塞值，而且还得保证参数的顺序和方法中形参顺序一致

        Map<String, String[]> parameterMap = req.getParameterMap();

        // 遍历 request 中所有参数（填充除了request，response之外的参数）
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            // name=1&name=2   name [1,2]
            String value = StringUtils.join(param.getValue(), ",");  // 如同 1,2

            // 如果参数和方法中的参数匹配上了，填充数据
            if (!handler.getParamIndexMapping().containsKey(param.getKey())) {
                continue;
            }

            // 方法形参确实有该参数，找到它的索引位置，对应的把参数值放入paraValues
            Integer index = handler.getParamIndexMapping().get(param.getKey());// name 在第 2 个位置

            // 把前台传递过来的参数值填充到对应的位置去
            paraValues[index] = value;
        }

        // 0
        int requestIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getSimpleName());
        paraValues[requestIndex] = req;

        // 1
        int responseIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getSimpleName());
        paraValues[responseIndex] = resp;

        // 最终调用 handler 的 method 属性
        try {
            handler.getMethod().invoke(handler.getController(), paraValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();

        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }

        return null;
    }

    /**
     * 首字母小写方法
     */
    public String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        if ('A' <= chars[0] && chars[0] <= 'Z') {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }
}
