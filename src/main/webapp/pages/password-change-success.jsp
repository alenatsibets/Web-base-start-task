<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Password change successful</title>
    <style><%@include file="css/success.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="password_change_success"/></h2>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <input type="submit" value="<fmt:message key="menu"/>">
    </form>
</ul>
</body>
</html>
