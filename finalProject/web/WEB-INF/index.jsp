<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <title>start</title>
    </head>
<body>
<c:url value="/index.html" var="indexUrl"/>
<form action="${indexUrl}" method="post">
<c:choose>
    <c:when test="${sessionScope.authorizedUser == null}">
        <c:url value="/login.html" var="loginUrl"/>
        <A href="${loginUrl}" class="buttonA">login</A>
    </c:when>
    <c:when test="${sessionScope.authorizedUser != null}">
        <h1>something happens</h1>
    </c:when>
</c:choose>
</form>
</body>
</html>