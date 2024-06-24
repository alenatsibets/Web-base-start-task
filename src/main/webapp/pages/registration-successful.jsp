<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Registration successful</title>
    <style><%@include file="css/success.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="registration_success"/></h2>
<p><fmt:message key="registration_success.login"/></p>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_login">
        <input type="submit" value="<fmt:message key="log_in"/>">
    </form>
</ul>
</body>
</html>
