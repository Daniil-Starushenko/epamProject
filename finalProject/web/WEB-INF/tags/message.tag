<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.text}" scope="session"/>
<fmt:setBundle basename="locale.text" var="message"/>
    <c:if test="${not empty requestScope.message}">
        <div class="alert" style="background-color: ${requestScope.messageType}">
            <span class="close-button" onclick="this.parentElement.style.display='none';">x</span>
            ${requestScope.message}
        </div>
    </c:if>