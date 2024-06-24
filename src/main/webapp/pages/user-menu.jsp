<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/taglib.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>User menu</title>
  <style><%@include file="css/menu.css"%></style>
</head>
<body>
<jsp:include page="header.jsp" />
<h2><fmt:message key="menu.welcome"/></h2>
<p><fmt:message key="menu.select"/></p>
<ul>
  <li><form action="controller">
    <input type="hidden" name="command" value="view_user">
    <button type="submit" class="custom-button"><fmt:message key="view_account"/></button>
  </form></li>
  <li><form action="controller">
    <input type="hidden" name="command" value="view_change_password">
    <input type="submit" value="<fmt:message key="change_password"/>">
  </form></li>
  <li><form action="controller">
    <input type="hidden" name="command" value="view_add_book">
    <button type="submit" class="custom-button"><fmt:message key="add_book.header"/></button>
  </form></li>
  <li><form action="controller">
    <input type="hidden" name="command" value="view_delete_book">
    <button type="submit" class="custom-button"><fmt:message key="delete_book.header"/></button>
  </form></li>
  <li><form action="controller">
    <input type="hidden" name="command" value="view_all_books">
    <button type="submit" class="custom-button"><fmt:message key="books.all"/></button>
  </form></li>
<%--  <li><form action="controller">--%>
<%--    <input type="hidden" name="command" value="view_create_contact">--%>
<%--    <button type="submit" class="custom-button"><fmt:message key="create_contact"/></button>--%>
<%--  </form></li>--%>
<%--  <li><form action="controller">--%>
<%--    <input type="hidden" name="command" value="view_update_contact_name">--%>
<%--    <button type="submit" class="custom-button"><fmt:message key="change_contact.name"/></button>--%>
<%--  </form></li>--%>
<%--  <li><form action="controller">--%>
<%--    <input type="hidden" name="command" value="view_update_contact_number">--%>
<%--    <button type="submit" class="custom-button"><fmt:message key="change_contact.number"/></button>--%>
<%--  </form></li>--%>
<%--  <li><form action="controller">--%>
<%--    <input type="hidden" name="command" value="view_delete_contact">--%>
<%--    <button type="submit" class="custom-button"><fmt:message key="delete_contact"/></button>--%>
<%--  </form></li>--%>
  <li><form action="controller">
    <input type="hidden" name="command" value="delete_user">
    <button type="submit" class="custom-button"><fmt:message key="delete_account"/></button>
  </form></li>
  <li><form action="controller">
    <input type="hidden" name="command" value="logout">
    <button type="submit" class="custom-button"><fmt:message key="log_out"/></button>
  </form></li>
  <ctg:login-time/>
<%--  <li><a href="books.jsp"><fmt:message key="view_contacts"/></a></li>--%>
<%--  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/delete"><fmt:message key="delete_account"/></a></li>--%>
</ul>
</body>
</html>