<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Update username</title>
    <style><%@include file="css/update-username.css"%></style>
</head>
<body>
<h2><fmt:message key="update_username.header"/></h2>
<form action="${pageContext.request.contextPath}/update" method="post">
    <div>
        <label for="username"><fmt:message key="update_username.label.username"/></label>
        <input type="text" id="username" name="username" required pattern="\w+" title="<fmt:message key="update_username.label.username.rule"/>">
    </div>
    <div>
        <input type="submit" value="<fmt:message key="update_username.submit"/>">
    </div>
</form>
</body>
</html>