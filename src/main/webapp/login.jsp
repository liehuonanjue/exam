<%--
  Created by IntelliJ IDEA.
  User: 齐亮
  Date: 2018/8/5
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form>
    <input type="text" placeholder="请输入用户名" name="name">
    <input type="password" placeholder="请输入密码" name="pwd">
    <input type="button" id="block" value="登陆">
</form>

<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">
    $("#block").click(function () {
        keycode();
    })


    function keycode() {
        if ($("[name=name]").val() == "")
            alert("用户名不能为空");
        else if ($("[name=pwd]").val() == "")
            alert("密码不能为空");
        else {
            $.post('/visitServlet?oper=login', {
                "username": $("[name=name]").val(),
                "password": $("[name=pwd]").val()
            }, function (data) {
                if (data == "true") {
                    alert("登陆成功")
                    document.location = 'index.jsp'
                } else {
                    alert("登陆失败！用户名或密码错误")
                    window.location.reload();
                }
            })
        }
    }
</script>
</body>
</html>
