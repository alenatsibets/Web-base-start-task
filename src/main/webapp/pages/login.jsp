<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>JSP</title>
    <style><%@include file="css/login.css"%></style>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    <label for="login"><fmt:message key="login.label.login"/></label>
    <input type="text" id="login" name="login" value=""/>
    <br/>
    <label for="pass"><fmt:message key="login.label.pass"/></label>
    <input type="password" id="pass" name="pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="login.input.submit"/>"/>
    <br/>
    <span class="message">${login_msg}</span>
</form>
</body>
</html>