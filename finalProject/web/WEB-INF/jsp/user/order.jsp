<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>active orders</title>
</head>
<body>
<u:menu/>
<c:url value="/user/show/order.html" var="UserOrderUml"/>
<div class="container_For_Orders">
    <ul class="orders-table">
        <li class="table-header-1">
            <div class="col col-1">order</div>
            <div class="col col-2"><p>status</p></div>
            <div class="col col-3">total price</div>
        </li>
        <c:forEach var="order" items="${requestScope.userOrders}">
            <li class="table-row">
                <div class="col col-1">${order.identity}</div>
                <div class="col col-2"><p>in basket</p></div>
                <div class="col col-3">${order.totalPrice}$</div>
            </li>
<%--            <c:forEach var="product" items="${order.orderProducts}">--%>
<%--                <li class="table-order-row">--%>
<%--                    <div class="col col-1">${product.productList.get(0).productName}</div>--%>
<%--                    <div class="col col-2"></div>--%>
<%--                    <div class="col col-3">${product.productList.get(0).price}$</div>--%>
<%--                </li>--%>
<%--            </c:forEach>--%>
        </c:forEach>
    </ul>
        <ul class="orders-table">
            <li class="table-header-2">
                <div class="col col-1">order</div>
                <div class="col col-2"><p>status</p></div>
                <div class="col col-3">total price</div>
            </li>
            <c:forEach var="orderTransit" items="${requestScope.transitUserOrders}">
                <li class="table-row">
                    <div class="col col-1">${orderTransit.identity}</div>
                    <div class="col col-2"><p>in transit</p></div>
                    <div class="col col-3">${orderTransit.totalPrice}$</div>
                </li>
            </c:forEach>
        </ul>
</div>
</body>
</html>
