<%--
  Created by IntelliJ IDEA.
  User: 齐亮
  Date: 2018/8/5
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<span> 添加销售</span>
<span>商品名称</span><select name="stair" id="name"></select>
<span>销售单价:</span><input type="text" value="" id="price">
<span>销售数量:</span><input type="text" value="" id="num">
<span><input type="button" value="保存" id="button"></span>
<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">
    $.getJSON('/visitServlet?oper=selectsale', function (data) {
        $.each(data, function (i, dom) {
            $("#name").append("<option value =" + dom.id + ">" + dom.productName + "</option>");
        });
    });

    $("#button").click(function () {
        $.post('/visitServlet?oper=addsale', {
            "price": $("#price").val(),
            "name": $("#name").val(),
            "num": $("#num").val(),
            "realName": ${upwdname.id}
        }, function (data) {
            if (data == "true") {
                alert("添加成功")
                document.location = 'index.jsp'
            } else {
                alert("添加失败！")
                window.location.reload();
            }
        })
    });


</script>
</body>
</html>
