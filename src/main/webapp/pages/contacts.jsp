<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.webbasestarttask.entity.Contact" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Contacts</title>
  <style><%@include file="css/contacts.css"%></style>
</head>
<body>
<h1><fmt:message key="contacts"/></h1>

<c:choose>
  <c:when test="${empty contactsList}">
    <p><fmt:message key="no_contacts"/></p>
  </c:when>
  <c:otherwise>

    <table border="1">
      <tr>
        <th><fmt:message key="contacts.header.name"/></th>
        <th><fmt:message key="contacts.header.number"/></th>
      </tr>
      <c:forEach items="${contactsList}" var="contact">
        <tr>
          <td>${contact.name}</td>
          <td>${contact.number}</td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<ul>
  <li><a href="user-menu.jsp"><fmt:message key="menu"/></a></li>
</ul>
</body>
</html>
