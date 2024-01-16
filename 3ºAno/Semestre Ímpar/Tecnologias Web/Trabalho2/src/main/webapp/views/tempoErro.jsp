<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eventify - Erro no Registo de Tempo</title>
    <link rel="icon" href="../static/img/errado.png">
    <link rel="stylesheet" href="../static/css/tempoErro.css">
</head>
<body>
    <div id="error-box">
        <h2>Falha no Registo de Tempo</h2>
        <table>
            <tr>
                <td>${sucesso}</td>
            </tr>
            <tr>
                <td>${motivo}</td>
            </tr>
        </table>
        <form action="<c:url value='/registarTempoParticipante' />" method="GET">
            <button type="submit">Tentar Novamente</button>
        </form>
        <p>Voltar para a página inicial? Clique <a href="<c:url value="/" />">aqui</a>.</p>
    </div>
</body>
</html>
