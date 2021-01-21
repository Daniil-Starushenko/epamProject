<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
              rel="stylesheet" type="text/css"/>
    </head>
    <c:url value="/login.html" var="loginUrl"/>
    <FORM action="${loginUrl}" method="post" class="loginClass">
        <INPUT type="text" placeholder="Username" name="login" value="${param.login}">
        <INPUT type="password" placeholder="Password" name="password">
        <BUTTON type="submit">login</BUTTON>
    </FORM>
</html>