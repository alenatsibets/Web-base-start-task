<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content" />
<!DOCTYPE html>
<html>
<head>
    <title>JSP</title>
    <style><%@include file="css/login.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />

<form action="controller">
    <input type="hidden" name="command" value="login"/>
    <label for="email"><fmt:message key="login.label.login"/></label>
    <input type="text" id="email" name="email" value="" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$" title="<fmt:message key="email.rule"/>"/>
    <br/>
    <label for="pass"><fmt:message key="login.label.pass"/></label>
    <input type="password" id="pass" name="pass" value="" required pattern=".{8,}" title="<fmt:message key="password.rule"/>"/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="login.input.submit"/>"/>
    <br/>
    <span class="message">${login_msg}</span>
</form>
<form action="controller">
    <input type="hidden" name="command" value="view_sign_up">
    <input type="submit" value="<fmt:message key="register"/>">
</form>
</body>
</html>