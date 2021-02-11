<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib prefix="tags" uri="customtags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
<html>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
<head>
    <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <c:url var="LanguageUrl" value="/user/language.html"/>
    <c:set var="jspPage" value="/register.html"/>
    <tags:language jspPage="${jspPage}" actionUrl="${LanguageUrl}"/>
    <u:message/>
    <c:url value="/register.html" var="registerUrl"/>
    <form action="${registerUrl}" method="post" class="loginClass">
        <input type="text" placeholder="<fmt:message key="login.name" bundle="${message}"/>" name="name" required="required" value="${param.name}">
        <input type="text" placeholder="<fmt:message key="login.username" bundle="${message}"/>" name="login" required="required" value="${param.login}">
        <input type="password" placeholder="<fmt:message key="login.password" bundle="${message}"/>" required="required" name="password">
        <input type="email" placeholder="mail@gmail.com" required="required" name="mail">
        <button type="submit"><fmt:message key="registration.button.reg" bundle="${message}"/></button>
    </form>
    <c:url value="/login.html" var="LoginUrl"/>
    <form action="${LoginUrl}" method="post">
    <button type="submit" class="registration_button"><fmt:message key="registration.login.button" bundle="${message}"/></button>
</form>
</body>
</html>