<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>SpringMVC 测试页</title>
</head>

<body>
<div>
    <h2>SpringMVC 对 Restful 风格 url 的支持</h2>
    <fieldset>
        <a href="/restful/handle/15">rest_get（查询）</a>

        <p></p>

        <form method="post" action="/restful/handle">
            <input type="submit" value="rest_post（新增）"/>
            <input type="text" name="username" placeholder="输入用户名"/>
        </form>

        <form method="post" action="/restful/handle/15/td">
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="rest_put（修改）"/>
        </form>

        <form method="post" action="/restful/handle/15">
            <input type="hidden" name="_method" value="delete"/>
            <input type="submit" value="rest_delete（删除）"/>
        </form>
    </fieldset>
</div>

</body>
</html>
