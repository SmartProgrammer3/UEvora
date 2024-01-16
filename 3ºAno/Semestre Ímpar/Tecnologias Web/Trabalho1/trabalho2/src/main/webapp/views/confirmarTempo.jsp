<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eventify - Tempo registado com sucesso</title>
    <link rel="icon" href="../static/img/logotipo.png">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        #options-box {
            border: 2px solid #ccc;
            padding: 20px;
            text-align: center;
        }

        .option-link {
            display: block;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div id="options-box">
        <h2>Escolha a opção de registo</h2>
        <p>Selecione uma das seguintes opções:</p>
        <a class="option-link" href="<c:url value="/registarTempoParticipante" />"> Registar outro tempo? </a>
        <p>Voltar para a página inicial? Clique <a href="<c:url value="/" />">aqui</a>.</p>
    </div>
</body>
</html>
