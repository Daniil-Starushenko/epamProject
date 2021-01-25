<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
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
                    <div class="col col-1"><fmt:message key="table.dish" bundle="${message}"/></div>
                    <div class="col col-2"><fmt:message key="table.photo" bundle="${message}"/></div>
                    <div class="col col-3"><fmt:message key="table.price" bundle="${message}"/></div>
                    <div class="col col-4"><fmt:message key="table.delete" bundle="${message}"/></div>
                </li>
                <c:forEach var="basketElement" items="${requestScope.basketList}">
                    <li class="table-row">
                        <div class="col col-1">${basketElement.productName}</div>
                        <div class="col col-2">photo</div>
                        <div class="col col-3">${basketElement.price}$</div>
                        <div class="col col-4">
                            <form action="${UserBasketUrl}" method="post">
                                <INPUT type="hidden" name="identityToDelete" value="${basketElement.identity}">
                                <button type="submit" class="all_button">-</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <a href="#openModal"><fmt:message key="basket.modal.orderNow" bundle="${message}"/></a>
            <div id="openModal" class="modalDialog">
                <div>
                    <c:url value="/user/order.html" var="userOrderUrl"/>
                    <FORM action="${userOrderUrl}" method="post">
                    <a href="#close" title="Закрыть" class="close">X</a>
                    <h2><fmt:message key="basket.modal.h" bundle="${message}"/></h2>
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
                    <p><fmt:message key="basket.modal.totalPrice" bundle="${message}"/> ${requestScope.totalPrice}$</p>
                    <c:if test="${sessionScope.basket.size() != 0}">
                    <input type="hidden" name="totalPrice" value="${requestScope.totalPrice}">
                    <BUTTON type="submit" class="all_button"><fmt:message key="basket.modal.button.order" bundle="${message}"/></BUTTON>
                    </c:if>
                        <c:if test="${sessionScope.basket.size() == 0}">
                            <p><fmt:message key="basket.modal.emptyBasket" bundle="${message}"/></p>
                        </c:if>
                    </FORM>
                </div>
            </div>
        </div>
</body>
</html>
