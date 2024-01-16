<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Eventify - Eventos futuros</title>
    <link rel="stylesheet" type="text/css" href="../static/css/eventos.css"> 
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
<div id="wrapper" style="padding-top:130px">
    <div id="content">
        <a class="back-button" onclick="history.back()" href="/"> Voltar à página inicial </a>
        <br>
        <div class="page-content">
            <div class="pag-events-container">
                <h2> Eventos por ocorrer </h2>
                ${eventosFuturos}
                ${navegacao}
            </div>
        </div>
    </div>
    <div id="cover"></div>
</div>
</body>
</html>