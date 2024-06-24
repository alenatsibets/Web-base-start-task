<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Verification</title>
    <style><%@include file="css/register.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<form action="controller">
    <input type="hidden" name="command" value="verification"/>
    <fmt:message key="verification_code"/> <input type="text" name="verification_code" value=""/>
    <br/>
    <span class="message">${error}</span>
    <input type="submit" name="sub" value="Submit"/>
    <br/>
</form>
</body>
</html>
