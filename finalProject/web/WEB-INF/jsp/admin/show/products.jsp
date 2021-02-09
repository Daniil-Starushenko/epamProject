<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/style1.css" %>
    </style>
    <title>product list</title>
</head>
<body class="admin_body">
<u:menu/>
<c:url value="/admin/main.html" var="MainUrl"/>
<form action="${MainUrl}" method="post">
    <button type="submit" class="main_button">go to main</button>
</form>
<u:message/>
<div class="container_For_Products">
    <ul class="responsive-table">
        <c:forEach var="product" items="${requestScope.adminProductList}">
            <li class="table-row">
                <div class="col col-1">${product.productName}
                    <c:url value="/admin/change/product.html" var="ChangeProductUrl"/>
                    <form action="${ChangeProductUrl}" method="post">
                        <input type="hidden" name="identity" value="${product.identity}">
                        <p>
                            <button type="submit" class="admin-edit-product">edit info</button>
                        </p>
                    </form>
                </div>
                <div class="col col-2">photo</div>
                <div class="col col-3">${product.price}$</div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>