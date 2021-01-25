<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <style><%@include file="/WEB-INF/css/style1.css"%></style>
    <title>edit</title>
</head>
<body class="admin_body">
<u:menu/>
<c:url value="/admin/deliveryman/create.html" var="adminDeliverymanCreateUml"/>
<FORM action="${adminDeliverymanCreateUml}" method="post" class="editProductClass">
    <input type="text" placeholder="name" name="name" value="${param.name}" required="required">
    <INPUT type="text" placeholder="Username" name="login" value="${param.login}" required="required">
    <INPUT type="password" placeholder="Password" name="password" required="required">
    <input type="email" placeholder="mail@gmail.com" name="mail" required="required">
    <INPUT type="tel" pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
           placeholder="+375(__)*******"
           name="telNumber"
           required="required">
    <BUTTON type="submit">create deliveryman</BUTTON>
</FORM>
    <c:url value="/admin/main.html" var="MainUrl"/>
    <form action="${MainUrl}" method="post">
        <button type="submit" class="main_button">go to main</button>
    </form>
</body>
</html>