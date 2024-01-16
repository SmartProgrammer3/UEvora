<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Eventify - Registar tempo</title>
    <link rel="stylesheet" href="../static/css/registarTempoParticipante.css">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>
<div id="wrapper" style="padding-top:100px">
    <div id="content">
        <a class="back-button" href="/">< Voltar </a>
        <br>
        <div class="page-content">
            <div class="form-container">
                <div style="text-align: center;">
                    <h2> <u> Tempo </u> </h2>
                </div>
                

                <form class="form" id="form1" method="GET" action="/registarTempo">
                    <p class="input-label"> Nome do evento</p>
                    <input type="text" name="nomeEvento">
                    <p class="input-label"> Numero do participante </p>
                    <input type="number" min="0" name="nParticipante">
                    <p class="input-label"> Ponto do participante na prova </p>
                    <div class="radio-group">
                        <div class="radio-subgroup">
                            <div class="radio">
                                <input type="radio" checked name="ponto" value="start">
                                <p class="radio-label"> Start </p>
                            </div>
                            <div class="radio">
                                <input type="radio" name="ponto" value="p1">
                                <p class="radio-label"> P1 </p>
                            </div>
                            <div class="radio">
                                <input type="radio" name="ponto" value="p2">
                                <p class="radio-label"> P2 </p>
                            </div>
                            <div class="radio">
                                <input type="radio" name="ponto" value="p3">
                                <p class="radio-label"> P3 </p>
                            </div>
                            <div class="radio">
                                <input type="radio" name="ponto" value="finish">
                                <p class="radio-label"> Finish </p>
                            </div>
                        </div>
                    </div>
                    <div class="datetime-section">
                        <p class="input-label"> Hora:Minuto:Segundo </p>
                        <input type="time" name="horas" value="11:59:59 PM" step="1">
                    </div>
                    <button type="submit"> Registar </button>
                </form>
            </div>
        </div>
    </div>
    <div id="cover"></div>
</div>
</body>
</html>
