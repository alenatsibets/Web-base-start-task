<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<fmt:setLocale value="be"/>
<fmt:setBundle basename="prop.page_content"/>
<html>
<head>
    <title>JSP</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        form {
            width: 300px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .message {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="login"/>
    <label for="login"><fmt:message key="label.login"/></label>
    <input type="text" id="login" name="login" value=""/>
    <br/>
    <label for="pass"><fmt:message key="label.pass"/></label>
    <input type="password" id="pass" name="pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="<fmt:message key="input.submit"/>"/>
    <br/>
    <span class="message">${login_msg}</span>
</form>
</body>
</html>