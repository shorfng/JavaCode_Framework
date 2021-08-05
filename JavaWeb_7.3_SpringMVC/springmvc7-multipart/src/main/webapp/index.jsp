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
    <h2> multipart 文件上传</h2>
    <fieldset>
        <form method="post" enctype="multipart/form-data" action="/multipart/upload">
            <input type="file" name="uploadFile"/>
            <input type="submit" value="上传"/>
        </form>
    </fieldset>
</div>

</body>
</html>
