<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eventify - Evento registado com sucesso.</title>
    <link rel="icon" href="../static/img/certo.png">
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
        <h2> Registo do evento concluído </h2>
        <p> Foi adicionado com sucesso </p>
        <p> Voltar para a página inicial: Clique <a href="<c:url value="/" />">aqui</a>.</p>
        <p> Quer adicionar mais algum?: Clique <a href="<c:url value="/registarUmEvento" />">aqui</a>.</p>
    </div>
</body>
</html>
