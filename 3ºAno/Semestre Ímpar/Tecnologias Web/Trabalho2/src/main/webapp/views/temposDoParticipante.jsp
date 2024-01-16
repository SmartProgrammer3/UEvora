<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="../static/img/logotipo.png">
    <title>Tempos do Participante</title>
    <link rel="stylesheet" type="text/css" href="../static/css/tempoParticipante.css">
</head>
<body>
    <h2>Tempos do Participante</h2>
    <div>
        ${tabelaTempos}
    </div>
    <a href="/classificacoes?evento=${evento}" class="back-button">Voltar</a>
</body>
</html>
