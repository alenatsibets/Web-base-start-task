<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webbasestarttask.model.entity.User" %>
<% User user = (User) request.getSession().getAttribute("user"); %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>User</title>
    <style><%@include file="css/user.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="user.details"/></h2>
<img src="uploadAvatar?email=${user.email}" alt="Image" />
<br/>
<table >
    <tr>
        <th><fmt:message key="user.header.username"/></th>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <th><fmt:message key="user.header.email"/></th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th><fmt:message key="locale"/></th>
        <td><ctg:locale-info/></td>
    </tr>
</table>
<ul>
    <form action="uploadAvatar" method="post" enctype="multipart/form-data">
<%--        <input type="hidden" name="command" value="file_upload">--%>
        <input type="file" name="avatar" >
        <input type="submit" value="submit">
    </form>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
    </form>
</ul>
</body>
</html>