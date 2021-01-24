<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <title>shop</title>
    </head>
    <body>
    <c:url value="/user/product.html" var="UserProductUrl"/>
    <u:menu/>
    <div class="container_For_Products">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col col-1"><fmt:message key="table.dish" bundle="${message}"/></div>
                <div class="col col-2"><fmt:message key="table.photo" bundle="${message}"/></div>
                <div class="col col-3"><fmt:message key="table.price" bundle="${message}"/></div>
                <div class="col col-4">+</div>
            </li>
            <c:forEach var="product" items="${requestScope.products}">
                <li class="table-row">
                    <div class="col col-1">${product.productName}</div>
                    <div class="col col-2">photo</div>
                    <div class="col col-3">${product.price}$</div>
                    <div class="col col-4">
                        <FORM id="form-${product.identity}" action="${UserProductUrl}" method="post">
                            <INPUT type="hidden" name="identity" value="${product.identity}">
                            <button type="submit">+</button>
                        </FORM>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    </body>
</html>