<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <style><%@include file="../css/error.css"%></style>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <h1><fmt:message key="error_404.name"/></h1>
    <p><fmt:message key="error_404.explanation"/></p>
    <form action="controller">
        <input type="hidden" name="command" value="view_menu">
        <button type="submit" class="custom-button"><fmt:message key="error_404.exit"/></button>
    </form>
</div>
</body>
</html>