<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
<html>
<div class="language">
    <c:url value="/user/language.html" var="languageUrl"/>
    <form action="${languageUrl}" method="post">
        <button name="lang" value="eng" type="submit" class="all_button">
            <fmt:message key="english" bundle="${message}"/>
        </button>
        <button name="lang" value="rus" type="submit" class="all_button">
            <fmt:message key="russian" bundle="${message}"/>
        </button>
    </form>
</div>
<br>
<style><%@include file="/WEB-INF/css/style1.css"%></style>
    <head>
        <link href="${pageContext.request.contextPath}/WEB-INF/css/style1.css"
              rel="stylesheet" type="text/css"/>
    </head>
    <c:url value="/login.html" var="loginUrl"/>
    <FORM action="${loginUrl}" method="post" class="loginClass">
        <INPUT type="text" placeholder="<fmt:message key="login.username" bundle="${message}"/>" name="login" value="${param.login}">
        <INPUT type="password" placeholder="password" name="password">
        <p style="color: red">${requestScope.message}</p>
        <BUTTON type="submit"> <fmt:message key="login" bundle="${message}"/></BUTTON>
    </FORM>
    <c:url value="/register.html" var="registerUrl"/>
    <form action="${registerUrl}" method="post">
        <button type="submit" class="registration_button">registration</button>
    </form>
</html>