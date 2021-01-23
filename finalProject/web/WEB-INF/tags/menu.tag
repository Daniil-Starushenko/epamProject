<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>

    <html>
<DIV class ="header">
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <c:if test="${not empty sessionScope.authorizedUser}">
            <c:forEach items="${sessionScope.menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <A class="aMenu" href="${itemUrl}"><fmt:message key="${item.name}" bundle="${message}"/></A>
            </c:forEach>
                <c:url value="/user/profile.html" var="userProfileUrl"/>
                <A class="aEdit" href="${userProfileUrl}">${sessionScope.authorizedUser.login}</A>
    </c:if>
</DIV>
    </html>
