<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
  <title>Delete book</title>
  <style><%@include file="css/register.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="delete_book.header"/></h2>
<form action="controller">
  <input type="hidden" name="command" value="delete_book"/>
  <div>
    <label for="title"><fmt:message key="add_book.label.title"/></label>
    <input type="text" id="title" name="title" required>
  </div>
  <span class="message">${error}</span>
  <div>
    <input type="submit" value="<fmt:message key="delete_book"/>">
  </div>
</form>
<ul>
  <form action="controller">
    <input type="hidden" name="command" value="view_all_books">
    <button type="submit" class="custom-button"><fmt:message key="books.all"/></button>
    <input type="hidden" name="command" value="view_menu">
    <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
  </form>
</ul>
</body>
</html>
