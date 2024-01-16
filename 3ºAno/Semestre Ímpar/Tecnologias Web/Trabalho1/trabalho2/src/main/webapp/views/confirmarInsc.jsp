<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> Eventify - Pagamento </title>
    <link rel="icon" href="../static/img/logotipo.png">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        #payment-box {
            border: 2px solid #ccc;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="payment-box">
        <h2> Detalhes do Pagamento - Referencia MB </h2>
        ${pagamento}
        <a href="/"> Voltar </a>
    </div>
</body>
</html>
