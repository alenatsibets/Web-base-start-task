<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webbasestarttask.entity.User" %>
<% User user = (User) request.getSession().getAttribute("user"); %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>User</title>
    <style><%@include file="css/user.css"%></style>
</head>
<body>
<h2><fmt:message key="user.details"/></h2>
<table >
    <tr>
        <th><fmt:message key="user.header.username"/></th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th><fmt:message key="user.header.email"/></th>
        <td>${user.email}</td>
    </tr>
</table>
<ul>
    <li><a href="user-menu.jsp"><fmt:message key="menu"/></a></li>
</ul>
</body>
</html>