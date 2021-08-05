package com.loto.springmvc.controller;

import com.loto.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-05 21:18</p>
 * <p>PageName：JsonController.java</p>
 * <p>Function：SpringMVC Ajax Json 交互
 */

@Controller
@RequestMapping("json")
public class JsonController {
    // @ResponseBody 注解作用：将 controller 的⽅法返回的对象通过适当的转换器转换为指定的格式之后，写⼊到 response 对象的 body 区，通常⽤来返回 JSON 数据或者是 XML 数据
    // 添加 @ResponseBody 之后，不再走视图解析器那个流程，而是直接将数据写入到输⼊流中，效果等同于通过 response 对象输出指定格式的数据
    @RequestMapping("/handlejson")
    @ResponseBody
    public User handleJson(@RequestBody User user) {
        // 业务逻辑处理，修改 name
        user.setName("蓝田");
        return user;
    }
}
