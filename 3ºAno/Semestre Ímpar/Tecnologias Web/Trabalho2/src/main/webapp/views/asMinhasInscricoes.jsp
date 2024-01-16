<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Eventify - As suas inscrições</title>
    <link rel="stylesheet" type="text/css" href="../static/css/asMinhasInscricoes.css">
    <link rel="icon" href="../static/img/logotipo.png">
<body>
    <div id="wrapper" style="padding-top:130px">
        <div id="content">
            <a class="back-button" href="/"> Voltar à página inicial </a>
            <div class="page-content">
                <div class="events-container">
                    <h2>${username}, aqui estão as tuas inscrições</h2>
                    ${listaEventos} 
                </div>
            </div>
        </div>
        <div id="cover"></div>
    </div>
</body>
</html>
