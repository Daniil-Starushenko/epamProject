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
    <c:url value="/admin/product/create.html" var="adminProductEditUml"/>
    <FORM action="${adminProductEditUml}" method="post" class="editProductClass" enctype="multipart/form-data">
        <INPUT type="text" placeholder="name" name="EditProductName" required="required" class="inputEdit">
        <div class="fl_upload">
            <label><input type="file" id="fl_inp" name="pngPath" accept="image/jpeg">choose file</label>
            <div id="file_name">file not chosen</div>
        </div>
        <textarea required="required" placeholder="description" name="description"></textarea>
        <input type="text" required="required" name="editPrice" placeholder="price: 12.35">
        <p style="color: red;">${requestScope.message}</p>
        <BUTTON type="submit">Add product</BUTTON>
    </FORM>
    <c:url value="/admin/main.html" var="MainUrl"/>
    <form action="${MainUrl}" method="post">
        <button type="submit" class="main_button">go to main</button>
    </form>
    <script type="text/javascript">
        <%@include file="/js/main.js" %>
    </script>
    </body>
</html>