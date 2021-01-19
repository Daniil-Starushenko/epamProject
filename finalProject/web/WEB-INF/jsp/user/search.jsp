<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <title>shop</title>
    </head>
    <body>
    <c:url value="/user/search.html" var="UserSearchUrl"/>
    <form action="${UserSearchUrl}" method="post" class="productTable">
        <table style="border-style: solid;
        width: 100%;
        border-collapse: collapse;">
            <thead>
            <tr>
                <td style="width: 30%;"></td>
                <td style="width: 30%;"></td>
                <td style="width: 40%;"></td>
            </tr>
            </thead>
            <c:forEach var="product" items="${requestScope.products}">
             <tr style="height: 50px;
                 border-style: solid;
                 border-collapse: collapse;
                 align-content: center;
                 border-right-width: 0;
                 border-left-width: 0;
                 border-top-width: 0;
                 border-bottom-width:1px;
                 border-bottom-color: #F1C05E">
                 <td><c:out value="${product.productName}"/></td>
                 <td><c:out value="${product.price}$"/></td>
                 <td><button type="submit" name="productToAdd" value="${product.identity}">add to order</button></td>
             </tr>
            </c:forEach>
        </table>
    </form>
    </body>
</html>

