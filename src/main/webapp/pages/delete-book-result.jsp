<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Deleting successful</title>
    <style><%@include file="css/menu.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="delete_book.success"/></h2>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_all_books">
        <button type="submit" class="custom-button"><fmt:message key="books.all"/></button>
    </form>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
    </form>
</ul>
</body>
</html>
