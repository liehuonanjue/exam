<%--
  Created by IntelliJ IDEA.
  User: 齐亮
  Date: 2018/8/5
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span>这是信息</span>

<table border="1">
    <tr>
        <td>ID</td>
        <td>商品</td>
        <td>单价</td>
        <td>数量</td>
        <td>总价</td>
        <td>销售日期</td>
        <td>销售员</td>
    </tr>
    <tr class="tab">
    </tr>
</table>
<p>
    <a href="javascript:page(i=1)">首页</a>|
    <a href="javascript: page(--i)">上一页</a>|
    <a href="javascript: page(++i)">下一页</a>|
    <a href="javascript: void(0)" class="mo">末页</a>
    第<span id="no">1</span>页/ 工<span class="sum2">2</span>页/
    共<span class="sum"></span>条记录）
</p>

<script type="application/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="application/x-javascript">

    function fsum() {
        $.get('/visitServlet?oper=getTotalusers', function (data) {
            $(".sum").html(data);
            $(".sum2").html(parseInt(data / 5) + 1);
        });
    }


    $(".mo").click(function () {
        i = $(".sum2").html();
        page(i);
    });
    var i = 1;
    $(function () {
        fsum();
        $("#no").html(i);
        page(i);

    })

    function page(index) {
        if (i < 1) {
            alert("不能再小了")
            i++;
            return
        } else if (i > $(".sum2").html()) {
            alert("不能再大了")
            i--;
            return;
        }
        $("#no").html(i);
        $.ajaxSettings.async = true;
        $.getJSON('/visitServlet?oper=listselectpage', {page: i, by: "price"}, function (data) {
            var tbody = $(".tab");
            tbody.html("");
            $.each(data, function (index, data) {
                var td = $("<tr><td>" + data.id + "</td>" +
                    "   <td>" + data.product + "</td>   " +
                    "   <td>" + data.price + "</td>   " +
                    "   <td>" + data.quantity + "</td>   " +
                    "   <td>" + new Date(data.saleDate).toLocaleString() + "</td>   " +
                    "   <td>" + data.totalPrice + "</td>   " +
                    "   <td>" + data.user + "</td>  /tr> ");
                tbody.append(td);
            });
            console.log(data);
        });
    }

</script>
</body>
</html>
