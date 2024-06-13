<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>Unsuccessful</title>
</head>
<body>
<h1><fmt:message key="unsuccessful"/></h1>
<h2><fmt:message key="unsuccessful.message"/></h2>
<h2><fmt:message key="unsuccessful.menu"/></h2>
<ul>
    <li><a href="http://localhost:8080/Web_base_start_task_war_exploded/user-menu"><fmt:message key="menu"/></a></li>
</ul>
</body>
</html>
