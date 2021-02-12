<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
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
            <div class="col col-1"><fmt:message key="order.table.header.order" bundle="${message}"/></div>
            <div class="col col-2"><p><fmt:message key="order.table.header.status" bundle="${message}"/></p></div>
            <div class="col col-3"><fmt:message key="order.table.header.totalPrice" bundle="${message}"/></div>
        </li>
        <c:forEach var="order" items="${requestScope.userOrders}">
            <li class="table-row">
                <div class="col col-1">${order.identity}</div>
                <div class="col col-2"><p><fmt:message key="order.status.kind.inBasket" bundle="${message}"/></p></div>
                <div class="col col-3">${order.totalPrice}$</div>
            </li>
        </c:forEach>
    </ul>
        <ul class="orders-table">
            <li class="table-header-2">
                <div class="col col-1"><fmt:message key="order.table.header.order" bundle="${message}"/></div>
                <div class="col col-2"><p><fmt:message key="order.table.header.status" bundle="${message}"/></p></div>
                <div class="col col-3"><fmt:message key="order.table.header.totalPrice" bundle="${message}"/></div>
            </li>
            <c:forEach var="orderTransit" items="${requestScope.transitUserOrders}">
                <li class="table-row">
                    <div class="col col-1">${orderTransit.identity}</div>
                    <div class="col col-2"><p><fmt:message key="order.status.kind.inTransit" bundle="${message}"/></p></div>
                    <div class="col col-3">${orderTransit.totalPrice}$</div>
                </li>
            </c:forEach>
        </ul>

    <ul class="orders-table">
        <li class="table-header-3">
            <div class="col col-1"><fmt:message key="order.table.header.order" bundle="${message}"/></div>
            <div class="col col-2"><p><fmt:message key="order.table.header.status" bundle="${message}"/></p></div>
            <div class="col col-3"><fmt:message key="order.table.header.totalPrice" bundle="${message}"/></div>
        </li>
        <c:forEach var="readyOrder" items="${requestScope.readyOrders}">
            <li class="table-row">
                <div class="col col-1">${readyOrder.identity}</div>
                <div class="col col-2"><p><fmt:message key="user.orders.status.ready" bundle="${message}"/></p></div>
                <div class="col col-3">${readyOrder.totalPrice}$</div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
