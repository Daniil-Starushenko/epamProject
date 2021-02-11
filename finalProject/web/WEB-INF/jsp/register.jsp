<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib prefix="tags" uri="customtags"%>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
<head>
    <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
          rel="stylesheet" type="text/css"/>
</head>
<u:message/>
<c:url value="/register.html" var="registerUrl"/>
<FORM action="${registerUrl}" method="post" class="loginClass">
    <input type="text" placeholder="name" name="name" required="required" value="${param.name}">
    <INPUT type="text" placeholder="Username" name="login" required="required" value="${param.login}">
    <INPUT type="password" placeholder="Password" required="required" name="password">
    <input type="email" placeholder="mail@gmail.com" required="required" name="mail">
    <BUTTON type="submit">reg</BUTTON>
</FORM>
<c:url value="/login.html" var="LoginUrl"/>
<form action="${LoginUrl}" method="post">
    <button type="submit" class="registration_button">Login</button>
</form>
</html>