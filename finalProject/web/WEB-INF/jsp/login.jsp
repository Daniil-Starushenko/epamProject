<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib prefix="tags" uri="customtags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <title>login</title>
        <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
              rel="stylesheet" type="text/css"/>
    </head>
<body>
    <c:url var="LanguageUrl" value="/user/language.html"/>
    <c:set var="jspPage" value="/login.html"/>
    <tags:language jspPage="${jspPage}" actionUrl="${LanguageUrl}"/>
    <c:url value="/login.html" var="loginUrl"/>
    <form action="${loginUrl}" method="post" class="loginClass">
        <input type="text" placeholder="<fmt:message key="login.username" bundle="${message}"/>" name="login" value="${param.login}">
        <input type="password" placeholder="password" name="password">
        <button type="submit"> <fmt:message key="login" bundle="${message}"/></button>
    </form>
    <c:url value="/register.html" var="registerUrl"/>
    <form action="${registerUrl}" method="post">
        <button type="submit" class="registration_button">registration</button>
    </form>
        <u:message/>
</body>
</html>