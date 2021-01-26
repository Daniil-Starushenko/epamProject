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
    <c:url value="/admin/product/edit.html" var="AdminProductEditUrl"/>
    <form action="${AdminProductEditUrl}" method="post">--%>
        <button type="submit">show new orders</button>
    </form>
<%--    <c:url value="/admin/edit/deliveryman.html" var="createDeliveryManUrl"/>--%>
<%--    <form action="${createDeliveryManUrl}" method="post">--%>
        <button type="submit">current orders</button>
<%--    </form>--%>
<%--    <form>--%>
<%--        <button type="submit">show users</button>--%>
<%--    </form>--%>
</div>
</body>
</html>
