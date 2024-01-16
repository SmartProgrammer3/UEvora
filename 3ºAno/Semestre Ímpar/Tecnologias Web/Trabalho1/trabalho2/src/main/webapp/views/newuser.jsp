<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring Security Basic - Form Based JDBC Authentication</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/registoStyles.css'/>">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
    <div id="register-box">
        <h2>Registe-se</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <form name='registerForm' action="/register" method="POST">
            <table>
                <tr>
                    <td>Nome de Utilizador:</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><input type='email' name='email' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td>Género:</td>
                    <td>
                        <div class="radio-group">
                            <label><input type='radio' name='genero' value='Masculino'>Masculino</label>
                            <label><input type='radio' name='genero' value='Feminino'>Feminino</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Escalão:</td>
                    <td>
                        <div class="radio-group">
                            <label><input type='radio' name='escalao' value='Júnior'>Júnior</label>
                            <label><input type='radio' name='escalao' value='Sénior'>Sénior</label>
                            <label><input type='radio' name='escalao' value='Vet35'>Vet35</label>
                            <label><input type='radio' name='escalao' value='Vet50'>Vet50</label>
                            <label><input type='radio' name='escalao' value='Vet65'>Vet65</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit" value="Registar" /></td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <div id="login-link">
            <p>Já tem uma conta? <a href="/login">Faça Login</a></p>
        </div>
    </div>
</body>
</html>
