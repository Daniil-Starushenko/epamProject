<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>main</title>
</head>
<body class="deliveryman_body">
<u:menu/>
<div class="deliveryman_main_buttons">
    <c:url value="/deliveryman/new_orders.html" var="NewOrderDeliveryman"/>
    <form action="${NewOrderDeliveryman}" method="post">
        <button type="submit">show new orders</button>
    </form>
    <c:url value="/deliveryman/token_orders.html" var="TokenOrderDeliveryman"/>--%>
    <form action="${TokenOrderDeliveryman}" method="post">
        <button type="submit">current orders</button>
    </form>
</div>
</body>
</html>
