<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Registration successful</title>
    <style><%@include file="css/success.css"%></style>
</head>
<body>
<h2><fmt:message key="registration_success"/></h2>
<p><fmt:message key="registration_success.login"/></p>
<ul>
    <li><a href=http://localhost:8080/Web_base_start_task_war_exploded/login"><fmt:message key="log_in"/></a></li>
</ul>
</body>
</html>
