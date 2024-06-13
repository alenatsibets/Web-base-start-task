<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Register Page</title>
    <style><%@include file="css/register.css"%></style>
</head>
<body>
<h2><fmt:message key="register.header"/></h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <div>
        <label for="username"><fmt:message key="register.label.username"/></label>
        <input type="text" id="username" name="username" required pattern="\w+" title="<fmt:message key="register.label.username.rule"/>">
    </div>
    <div>
        <label for="email"><fmt:message key="register.label.email"/></label>
        <input type="email" id="email" name="email" required>
    </div>
    <label for="password"><fmt:message key="register.label.password"/></label>
    <input type="password" id="password" name="password" required pattern=".{8,}" title="<fmt:message key="register.label.password.rule"/>">
    <div>
        <input type="submit" value="<fmt:message key="register.submit"/>">
    </div>
</form>
<ul>
    <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/login"><fmt:message key="log_in"/></a></li>
</ul>
</body>
</html>
