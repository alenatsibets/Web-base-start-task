<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Contacts</title>
  <style><%@include file="css/books.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h1><fmt:message key="books"/></h1>

<c:choose>
  <c:when test="${empty booksList}">
    <p><fmt:message key="no_books"/></p>
  </c:when>
  <c:otherwise>

    <table border="1">
      <tr>
        <th><fmt:message key="books.header.name"/></th>
        <th><fmt:message key="books.header.author"/></th>
        <th><fmt:message key="books.header.publisher"/></th>
        <th><fmt:message key="books.header.year"/></th>
      </tr>
      <c:forEach items="${booksList}" var="book">
        <tr>
          <td>${book.title}</td>
          <td>${book.author}</td>
          <td>${book.publisher}</td>
          <td>${book.year}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>
<div id="pagination">
  <c:if test="${currentPage > 1}">
    <form action="controller" method="get">
      <input type="hidden" name="command" value="view_all_books">
      <input type="hidden" name="page" value="${currentPage - 1}">
      <button type="submit" class="custom-button">Previous</button>
    </form>
  </c:if>

  <c:forEach var="i" begin="1" end="${totalPages}">
    <form action="controller" method="get" style="display:inline;">
      <input type="hidden" name="command" value="view_all_books">
      <input type="hidden" name="page" value="${i}">
      <button type="submit" class="custom-button"><c:out value="${i}"/></button>
    </form>
  </c:forEach>

  <c:if test="${currentPage < totalPages}">
    <form action="controller" method="get">
      <input type="hidden" name="command" value="view_all_books">
      <input type="hidden" name="page" value="${currentPage + 1}">
      <button type="submit" class="custom-button">Next</button>
    </form>
  </c:if>
</div>

<ul>
  <form action="controller">
    <input type="hidden" name="command" value="view_add_book">
    <button type="submit" class="custom-button"><fmt:message key="add_book.header"/></button>
  </form>
  <form action="controller">
    <input type="hidden" name="command" value="view_delete_book">
    <button type="submit" class="custom-button"><fmt:message key="delete_book.header"/></button>
  </form>
  <form action="controller">
    <input type="hidden" name="command" value="view_menu">
    <button type="submit" class="custom-button"><fmt:message key="menu"/></button>
  </form>
</ul>
</body>
</html>
