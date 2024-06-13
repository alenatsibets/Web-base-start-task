<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>User menu</title>
  <style><%@include file="css/user-menu.css"%></style>
</head>
<body>
<h2><fmt:message key="menu.welcome"/></h2>
<p><fmt:message key="menu.select"/></p>
<ul>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/user"><fmt:message key="view_account"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/update"><fmt:message key="change_username"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/new-contact"><fmt:message key="create_contact"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/update-contact-name"><fmt:message key="change_contact.name"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/update-contact-number"><fmt:message key="change_contact.number"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/delete-contact"><fmt:message key="delete_contact"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/contacts"><fmt:message key="view_contacts"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/logout"><fmt:message key="log_out"/></a></li>
  <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/delete"><fmt:message key="delete_account"/></a></li>
</ul>
</body>
</html>