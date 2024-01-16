<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring Security Basic - Form Based JDBC Authentication</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/loginStyles.css"/>">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
    <div id="login-box">
        <h2> Login </h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <form name='loginForm' action="<c:url value='j_spring_security_check' />" method="POST">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit" value="Submit" /></td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <div id="register-link">
            <p>Registe-se caso não tenha conta: <a href="/newuser">aqui</a></p>
        </div>
    </div>
</body>
</html>
