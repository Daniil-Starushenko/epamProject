<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
<head>
    <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
          rel="stylesheet" type="text/css"/>
</head>
<c:url value="/register.html" var="registerUrl"/>
<FORM action="${registerUrl}" method="post" class="loginClass">
    <input type="text" placeholder="name" name="name" value="${param.name}">
    <INPUT type="text" placeholder="Username" name="login" value="${param.login}">
    <INPUT type="password" placeholder="Password" name="password">
    <input type="email" placeholder="mail@gmail.com" name="mail">
    <BUTTON type="submit">reg</BUTTON>
</FORM>
</html>