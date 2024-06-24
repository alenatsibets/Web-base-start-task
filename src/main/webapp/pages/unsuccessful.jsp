<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Unsuccessful</title>
    <style><%@include file="css/main.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h1><fmt:message key="unsuccessful"/></h1>
<h2><fmt:message key="unsuccessful.message"/></h2>
<h2><fmt:message key="unsuccessful.menu"/></h2>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
    </form>
</ul>
</body>
</html>
