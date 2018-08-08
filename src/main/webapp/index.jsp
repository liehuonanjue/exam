<%--
  Created by IntelliJ IDEA.
  User: 齐亮
  Date: 2018/8/5
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>主界面22</title>
</head>
<body>
<h1>欢迎${upwdname.realName}<a href="/visitServlet?oper=removeAttribute">退出登陆</a></h1>
<a href="javascript:void(0)" id="sale">销售</a>
<a href="javascript:void(0)" id="findSale">销售信息查询</a>
<a href="javascript:void(0)" id="find">查看库存</a>
<iframe src="" width="100%" height="100%">

</iframe>
<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">
    $("#sale").bind("click", function () {
        $("iframe").attr("src", "addsell.jsp");
    });
    $("#findSale").bind("click", function () {
        $("iframe").attr("src", "selectsell.jsp");
    });
    $("#find").bind("click", function () {
        $("iframe").attr("src", "selectrepertory.jsp");
    });
</script>
</body>
</html>
