<%--
  Created by IntelliJ IDEA.
  User: alena
  Date: 18.03.24
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Internal Server Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
        }
        h1 {
            font-size: 36px;
            margin-bottom: 20px;
            color: #f44336; /* красный цвет */
        }
        p {
            font-size: 18px;
            margin-bottom: 30px;
        }
        a {
            text-decoration: none;
            color: #4caf50;
            font-weight: bold;
            transition: color 0.3s;
        }
        a:hover {
            color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>500 - Internal Server Error</h1>
    <p>An unexpected condition was encountered while processing the request. Please try again later.</p>
    <a href="index.jsp">Go to Home Page</a>
</div>
</body>
</html>