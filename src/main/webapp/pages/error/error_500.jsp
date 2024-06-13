<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Internal Server Error</title>
    <style><%@include file="../css/error.css"%></style>
</head>
<body>
<div class="container">
    <h1><fmt:message key="error_500.name"/></h1>
    <p><fmt:message key="error_500.explanation"/></p>
    <a href="index.jsp"><fmt:message key="error_505.exit"/></a>
</div>
</body>
</html>