<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>main</title>
</head>
<body class="admin_body">
    <u:menu/>
    <div class="admin_main_buttons">
        <c:url value="/admin/product/init.html" var="AdminProductCreateUrl"/>
        <form action="${AdminProductCreateUrl}" method="post">
            <button type="submit">add new product</button>
        </form>
        <c:url value="/admin/edit/deliveryman.html" var="createDeliveryManUrl"/>
        <form action="${createDeliveryManUrl}" method="post">
            <button type="submit">add new deliveryman</button>
        </form>
        <c:url value="/admin/show/products.html" var="ShowProductsUrl"/>
        <form action="${ShowProductsUrl}">
            <button type="submit">show products</button>
        </form>
    </div>
</body>
</html>