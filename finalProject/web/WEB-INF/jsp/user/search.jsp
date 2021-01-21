<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <title>shop</title>
    </head>
    <body>
    <c:url value="/user/product.html" var="UserProductUrl"/>
    <u:menu/>
    <div class="productTable">
        <table style="border-style: solid;
        width: 100%;
        border-collapse: collapse;">
            <thead>
            <tr>
                <td style="width: 20%;"></td>
                <td style="width: 20%;"></td>
                <td style="width: 20%"></td>
                <td style="width: 10%;"></td>
            </tr>
            </thead>
            <c:forEach var="product" items="${requestScope.products}">
                <tr style="height: 50px;
                 text-align: center">
                    <td>${product.productName}
                        <FORM id="form-${product.identity}" action="${UserProductUrl}" method="post">
                            <INPUT type="submit" name="identity" value="${product.identity}">
                        </FORM>
                    </td>
                    <td><c:out value="${product.price}$"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</html>
