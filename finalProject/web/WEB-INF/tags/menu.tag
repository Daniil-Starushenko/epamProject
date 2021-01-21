<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<DIV class ="header">
    <html>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <c:if test="${not empty sessionScope.authorizedUser}">
            <c:forEach items="${sessionScope.menu}" var="item">
                <c:url value="${item.url}" var="itemUrl"/>
                <A class="aMenu" href="${itemUrl}">${item.name}</A>
            </c:forEach>
                <A class="aEdit" href="">${sessionScope.authorizedUser.login}</A>
    </c:if>
    </html>
</DIV>
