<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registo Confirmado</title>
    <link rel="icon" href="../static/img/logotipo.png">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        #confirm-box {
            border: 2px solid #ccc;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="confirm-box">
        <h2>Parabéns pelo registo</h2>
        <p>Agora já podes usufruir dos nossos serviços!</p>
        <p>Faça o seu login <a href="<c:url value="/login" />">aqui</a>.</p>
    </div>
</body>
</html>
