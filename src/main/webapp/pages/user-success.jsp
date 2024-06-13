<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Success</title>
    <style><%@include file="css/success.css"%></style>
</head>
<body>
<h1><fmt:message key="success"/></h1>
<p>${successMessage}</p>
<h2><fmt:message key="user_success"/></h2>
<ul>
    <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/user"><fmt:message key="view_account"/></a></li>
</ul>
</body>
</html>