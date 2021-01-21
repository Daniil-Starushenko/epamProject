<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
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
    <%--    <div class="images">--%>
    <%--        <img src="" />--%>
    <%--    </div>--%>
    <p class="pick">choose quantity</p>

    <div class="product">
        <p>DISH</p>
        <h1>${product.productName}</h1>
        <h2>${product.price}$</h2>
        <p class="desc">${product.definition}</p>
        <div class="buttons">
            <button type="submit" name="productIdentity" value="${product.identity}" class="add">Add to Cart</button>
        </div>
    </div>
</div>
</form>
</body>
</html>
