<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Create contact</title>
    <style><%@include file="css/create-contact.css"%></style>
</head>
<body>
<h2><fmt:message key="create_contact.header"/></h2>
<form action="${pageContext.request.contextPath}/new-contact" method="post">
    <div>
        <label for="name"><fmt:message key="create_contact.label.name"/></label>
        <input type="text" id="name" name="name" required pattern="\w+" title="<fmt:message key="create_contact.label.name.rule"/>">
    </div>
    <div>
        <label for="number"><fmt:message key="create_contact.label.number"/></label>
        <input type="text" id="number" name="number" required>
    </div>
    <div>
        <input type="submit" value="<fmt:message key="create_contact.submit"/>">
    </div>
</form>
</body>
</html>
