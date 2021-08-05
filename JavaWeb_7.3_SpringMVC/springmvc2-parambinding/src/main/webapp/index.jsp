<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC 测试页</title>
    <style>
        div {
            padding: 10px 10px 0 10px;
        }
    </style>
</head>

<body>
<div>
    <h2>Spring MVC 请求参数绑定</h2>
    <fieldset>
        <p>测试用例：SpringMVC 对原生 servlet api 的支持</p>
        <a href="/parambinding/handleServlet?id=1">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC 接收简单数据类型参数</p>
        <a href="/parambinding/simpledatatype?id=1">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC 使用@RequestParam 接收简单数据类型参数(形参名和参数名不一致)</p>
        <a href="/parambinding/simpledatatype?ids=1">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收pojo类型参数</p>
        <a href="/parambinding/handlepojo?id=1&username=TD">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收pojo包装类型参数</p>
        <a href="/parambinding/handlepackagetype?user.id=1&user.username=TD">点击测试</a>
    </fieldset>

    <fieldset>
        <p>测试用例：SpringMVC接收日期类型参数</p>
        <a href="/parambinding/handledatetype?birthday=2019-10-08">点击测试</a>
    </fieldset>
</div>

</body>
</html>
