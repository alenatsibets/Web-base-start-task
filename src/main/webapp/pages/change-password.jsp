<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Password change</title>
    <style><%@include file="css/register.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<form action="controller">
    <input type="hidden" name="command" value="change_password"/>
    <fmt:message key="old_password"/>
    <input type="password" name="old_password" value="" required pattern=".{8,}" title="<fmt:message key="password.rule"/>"/>
    <br/>
    <fmt:message key="new_password"/>
    <input type="password" name="new_password" value="" required pattern=".{8,}" title="<fmt:message key="password.rule"/>"/>
    <br/>
    <span class="message">${password_msg}</span>
    <input type="submit" name="sub" value="<fmt:message key="change_password.submit"/>"/>
    <br/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="view_menu">
    <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
</form>
</body>
</html>
