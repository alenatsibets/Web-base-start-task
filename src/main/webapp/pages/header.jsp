<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Header</title>
</head>
<body>
<div class="header">
    <div class="locale-selector">
        <form action="changeLocale" method="get">
            <select name="lang" onchange="this.form.submit()">
                <option value="en" ${sessionScope.locale eq 'en' ? 'selected' : ''}>English</option>
                <option value="be" ${sessionScope.locale eq 'be' ? 'selected' : ''}>Беларуская</option>
            </select>
        </form>
    </div>
</div>
</body>
</html>
