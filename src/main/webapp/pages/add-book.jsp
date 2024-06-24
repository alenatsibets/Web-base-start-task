<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Add book</title>
    <style><%@include file="css/register.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="add_book.header"/></h2>
<form action="controller">
    <input type="hidden" name="command" value="add_book"/>
    <div>
        <label for="title"><fmt:message key="add_book.label.title"/></label>
        <input type="text" id="title" name="title" required>
    </div>
    <div>
        <label for="author"><fmt:message key="add_book.label.author"/></label>
        <input type="text" id="author" name="author" required>
    </div>
    <div>
        <label for="publisher"><fmt:message key="add_book.label.publisher"/></label>
        <input type="text" id="publisher" name="publisher" required>
    </div>
    <div>
        <label for="year"><fmt:message key="add_book.label.year"/></label>
        <input type="text" id="year" name="year" required>
    </div>
    <span class="message">${error}</span>
    <div>
        <input type="submit" value="<fmt:message key="add_book.submit"/>">
    </div>
</form>
<ul>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
    </form>
</ul>
</body>
</html>
