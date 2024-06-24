<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Register Page</title>
    <style><%@include file="css/register.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="register.header"/></h2>
<form action="controller">
    <input type="hidden" name="command" value="register"/>
    <div>
        <label for="username"><fmt:message key="register.label.username"/></label>
        <input type="text" id="username" name="username" required pattern="\w+" title="<fmt:message key="register.label.username.rule"/>">
    </div>
    <div>
        <label for="email"><fmt:message key="register.label.email"/></label>
        <input type="email" id="email" name="email" required>
    </div>
    <label for="password"><fmt:message key="register.label.password"/></label>
    <input type="password" id="password" name="password" required pattern=".{8,}" title="<fmt:message key="password.rule"/>">
    <span class="message">${signup_error}</span>
    <div>
        <input type="submit" value="<fmt:message key="register.submit"/>">
    </div>
</form>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_login">
        <button type="submit" class="custom-button"><fmt:message key="log_in"/></button>
    </form>
</ul>
</body>
</html>
