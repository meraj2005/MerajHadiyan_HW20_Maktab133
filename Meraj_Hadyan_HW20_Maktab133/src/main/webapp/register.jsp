<%--
  Created by IntelliJ IDEA.
  User: abdol
  Date: 10/26/2025
  Time: 7:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css2/register.css">
</head>
<body>

<h1>register</h1>
<form action="register" method="post">
    <h2>register Fields:</h2>

    <div class="register-form">
        <label>Username:</label>
        <input type="text" name="username" required>
    </div>

    <div class="register-form">
        <label>Email:</label>
        <input type="email" name="email" required>
    </div>

    <div class="register-form">
        <label>Password:</label>
        <input type="password" name="password" required>
    </div>

    <div class="register-form">
        <button type="submit" class="submit-btn">Register</button>
        <p class="login">
            <a href="login">login</a>
        </p>
    </div>

</form>

</body>
</html>
