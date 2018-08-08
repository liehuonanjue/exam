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
<h1>欢迎${upwdname.realName} ${upwdname.id} <a href="#">退出登陆</a></h1>
<ul class="nav" id="side-menu">
    <li>
        <a href="addsell.jsp" target="menuFrame"><b>销售</b></a>
    </li>
    <li>
        <a href="selectsell.jsp" target="menuFrame"><b>销售信息</b></a>
    </li>
    <li>
        <a href="selectrepertory.jsp" target="menuFrame"><b>查看库存</b></a>
    </li>
</ul>

<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">
    ("li").on('click', function () {
        var href = $(this).find("a").attr('href');
        $('#mainContents').empty();
        $('#mainContents').load("../new/" + href);
        //阻止跳转
        $(this).parents('li').addClass('active').siblings('li').removeClass('active');
        return false;
    });

</script>
</body>
</html>
