<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/style1.css" %>
    </style>
    <title>edit</title>
</head>
<body class="admin_body">
<u:menu/>
<c:url value="/admin/change/product.html" var="adminProductChangeUml"/>
<form action="${adminProductChangeUml}" method="post" class="editProductClass" enctype="multipart/form-data">
    <input type="text" name="EditProductName" required="required" class="inputEdit"
           value="${requestScope.productToChange.productName}">
    <div class="fl_upload">
        <label><input type="file" id="fl_inp" name="pngPath" accept="image/jpeg">choose file</label>
        <div id="file_name">file not chosen</div>
    </div>
    <textarea required="required" placeholder="description"
              name="description">${requestScope.productToChange.definition}</textarea>
    <input type="text" required="required" name="editPrice" placeholder="price: 12.35"
           value="${requestScope.productToChange.price}">
    <p style="color: red;">${requestScope.message}</p>
    <button type="submit">change info</button>
    <input type="hidden" name="identityToChange" value="${requestScope.productToChange.identity}">
</form>
<c:url value="/admin/main.html" var="MainUrl"/>
<form action="${MainUrl}" method="post">
    <button type="submit" class="main_button">go to main</button>
</form>
<script type="text/javascript">
    <%@include file="/js/main.js" %>
</script>
</body>
</html>