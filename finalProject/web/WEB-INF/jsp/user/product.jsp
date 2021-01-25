<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
<head>
    <title>product</title>
    <c:set var="product" value="${requestScope.product}"/>
</head>
<body>
<u:menu/>
<c:url value="/user/add/product.html" var="UserAddProductUrl"/>
<form action="${UserAddProductUrl}" method="post">
<div class="containerProduct">
    <div class="product">
        <p><fmt:message key="table.dish" bundle="${message}"/></p>
        <h1>${product.productName}</h1>
        <h2>${product.price}$</h2>
        <p class="desc">${product.definition}</p>
        <div class="buttons">
            <button type="submit" name="productIdentity" value="${product.identity}" class="all_button"><fmt:message key="product.Add.To.Cort" bundle="${message}"/></button>
        </div>
    </div>
</div>
</form>
</body>
</html>
