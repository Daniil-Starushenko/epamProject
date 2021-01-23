<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>basket</title>
</head>
<body>
    <u:menu/>
    <c:url value="/user/basket.html" var="UserBasketUrl"/>
        <div class="container_For_Products">
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-1">dish</div>
                    <div class="col col-2">photo</div>
                    <div class="col col-3">price</div>
                    <div class="col col-4">delete</div>
                </li>
                <c:forEach var="basketElement" items="${requestScope.basketList}">
                    <li class="table-row">
                        <div class="col col-1">${basketElement.productName}</div>
                        <div class="col col-2">photo</div>
                        <div class="col col-3">${basketElement.price}$</div>
                        <div class="col col-4">
                            <form action="${UserBasketUrl}" method="post">
                                <INPUT type="hidden" name="identityToDelete" value="${basketElement.identity}">
                                <button type="submit">-</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <a href="#openModal">order now</a>
            <div id="openModal" class="modalDialog">
                <div>
                    <c:url value="/user/order.html" var="userOrderUrl"/>
                    <FORM action="${userOrderUrl}" method="post">
                    <a href="#close" title="Закрыть" class="close">X</a>
                    <h2>order</h2>
                    <INPUT type="text"
                           placeholder="address"
                           name="address"
                           value="${param.address}"
                           class="inputBasket"
                           required="required">
                    <INPUT type="tel" pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
                           placeholder="+375(__)*******"
                           name="telNumber"
                           class="inputBasket"
                           required="required">
                    <p>total price: ${requestScope.totalPrice}$</p>
                    <c:if test="${sessionScope.basket.size() != 0}">
                    <input type="hidden" name="totalPrice" value="${requestScope.totalPrice}">
                    <BUTTON type="submit">order</BUTTON>
                    </c:if>
                        <c:if test="${sessionScope.basket.size() == 0}">
                            <p>empty basket</p>
                        </c:if>
                    </FORM>
                </div>
            </div>
        </div>
</body>
</html>
