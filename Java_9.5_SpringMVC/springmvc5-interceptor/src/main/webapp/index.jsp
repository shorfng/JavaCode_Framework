<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>SpringMVC 测试页</title>

    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#ajaxBtn").bind("click", function () {
                // 发送ajax请求
                $.ajax({
                    url: '/json/handlejson',
                    type: 'POST',
                    data: '{"id":"1","name":"TD"}',
                    contentType: 'application/json;charset=utf-8',
                    dataType: 'json',
                    success: function (data) {
                        alert(data.name);
                    }
                })
            })
        })
    </script>

    <style>
        div {
            padding: 10px 10px 0 10px;
        }
    </style>
</head>

<body>
<div>
    <h2>Ajax json交互</h2>
    <fieldset>
        <input type="button" id="ajaxBtn" value="ajax提交"/>
    </fieldset>
</div>

</body>
</html>
