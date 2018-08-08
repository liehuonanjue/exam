<%--
  Created by IntelliJ IDEA.
  User: 齐亮
  Date: 2018/8/5
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span>这是库存</span>
<span>商品名称</span><select name="stair" id="name"></select>
<span id="sss"> 库存数量</span>
<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">
    $.getJSON('/visitServlet?oper=selectsale', function (data) {
        $.each(data, function (i, dom) {
            $("#name").append("<option value =" + dom.id + ">" + dom.productName + "</option>");
        });
    });
    nam();
    function nam() {
        $.getJSON('/visitServlet?oper=selectsale', function (data) {
            $.each(data, function (i, dom) {
                if ($("#name").val() == dom.id) {
                    $("#sss").html("库存数量：" + dom.quanntiy);

                }
            });
        });
    }
    $("#name").change(function () {
        nam()

    });

</script>
</body>
</html>
