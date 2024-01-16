<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Eventify - Inscreva-se! </title>
    <link rel="stylesheet" type="text/css" href="../static/css/inscreverEvento.css"> 
    <link rel="icon" href="../static/img/logotipo.png">
<body>
    <div id="wrapper" style="padding-top:130px">
        <div id="content">
            <a class="back-button" href="/"> Voltar </a>
            <div class="page-content">
                <div class="events-container">
                    <h2> Inscreva-se num evento! </h2>
                    ${events}
                </div>
            </div>
        </div>
        <div id="cover"></div>
    </div>
</body>
</html>
