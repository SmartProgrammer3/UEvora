<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Eventify - Pesquisa os teus eventos. </title>
    <link rel="stylesheet" type="text/css" href="../static/css/pesquisEventos.css">
    <link rel="icon" href="../static/img/logotipo.png">
    <script src="../static/utils/script.js"></script>
</head>
<body>
<div id="wrapper" style="padding-top:130px">
    <div id="content">
        <a class="back-button" onclick="history.back()" href="/"> Voltar à página inicial </a>
        <br>
        <div class="page-content">
            <div class="pag-events-container">
                <h2> Pesquisar os eventos! </h2>
                <div class="search">
                    <input name="event-name" placeholder="Nome parcial do evento">
                    <input type="date" name="event-date">
                    <button onclick="filtro()"> Pesquisar </button>
                </div>
                <table id="eventTable">
                    <thead>
                        <tr>
                            <th> Nome do Evento </th>
                            <th> Data do Evento </th>
                            <th> Detalhes do Evento </th>
                        </tr>
                    </thead>
                    <tbody id="listaDeEventos">
                        ${eventos}
                    </tbody>
                </table>
                <div id="pagination">
                </div>
            </div>
        </div>
    </div>
    <div id="cover"></div>
</div>
    <script>
        mostrarEventos();
        updatePagina();
    </script>
</body>
</html>
