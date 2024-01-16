<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Informação do Evento</title>
    <link rel="stylesheet" type="text/css" href="../static/css/infoEvento.css">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>

    <h1>Informação do Evento - ${evento}</h1>

    <div class="container">
        <div class="event-info">
            ${info}
        </div>

        <div class="participants">
            ${participantes}
        </div>

        <div class="classification" style="padding-top:50px">
            ${classificacao}
        </div>
        <button onclick="history.back()" class="back-button">Voltar à página anterior</button>  <!-- A função JavaScript history.back() faz parte do objeto window.history e permite navegar uma etapa para trás no histórico da sessão,ou seja, permite voltar à página de onde foi direcionado para esta página.-->
    </div>

</body>
</html>

