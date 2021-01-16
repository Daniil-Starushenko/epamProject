<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<H2>FOOD STORE</H2>
<c:url value="/index.html" var="indexUrl"/>
<c:choose>
    <c:when test="${sessionScope.authorizedUser == null}">
        <c:url value="login.html" var="loginUrl"/>
        <A href="${loginUrl}">login</A>
    </c:when>
    <c:when test="${sessionScope.authorizedUser != null}">
        <H1>no action</H1>
    </c:when>
</c:choose>

</html>