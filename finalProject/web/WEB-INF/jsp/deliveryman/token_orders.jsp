<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>main</title>
</head>
<body class="deliveryman_body">
<u:menu/>
<c:url value="/deliveryman/take_order.html" var="TakeOrderUml"/>
<div class="container_For_active_orders">
    <ul class="responsive-table-active-order">
        <li class="table-header-active-order">
            <div class="col col-1">order</div>
            <div class="col col-4">accept</div>o
        </li>
        <c:forEach var="order" items="${requestScope.newActiveOrder}">
            <li class="table-row-active-order">
                <div class="col col-1-active-order">${order.identity}
                    <p><b>total price:</b> ${order.totalPrice}$</p>
                    <c:forEach var="product" items="${order.productList}">
                        <p><b>product</b>: ${product.productName}; price: ${product.price}$</p>
                    </c:forEach>
                    <p><b>address:</b> ${order.address}</p>
                    <p><b>telephone number:</b> ${order.phoneNumber}</p>
                    <p><b>name:</b> ${order.customer.name}</p>
                </div>
                <div class="col col-4-active-order">
                    <FORM id="form-${order.identity}" action="${TakeOrderUml}" method="post">
                        <INPUT type="hidden" name="identity" value="${order.identity}">
                        <button type="submit" class="take_order_button">take this order</button>
                    </FORM>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>