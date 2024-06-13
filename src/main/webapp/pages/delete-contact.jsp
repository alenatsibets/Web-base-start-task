<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Delete contact</title>
    <style><%@include file="css/delete-contact.css"%></style>
</head>
<body>
<h2><fmt:message key="delete_contact.header"/></h2>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<c:if test="${not empty successMessage}">
    <div style="color: green;">${successMessage}</div>
</c:if>
<form action="${pageContext.request.contextPath}/delete-contact" method="post">
    <label for="name"><fmt:message key="delete_contact.label.name"/></label><br>
    <input type="text" id="name" name="name" required><br>
    <input type="submit" value="<fmt:message key="delete_contact.update"/>">
</form>
</body>
</html>
