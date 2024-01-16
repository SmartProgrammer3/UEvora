<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title> Eventify - Detalhes do Pagamento</title>
    <link rel="icon" href="../static/img/logotipo.png">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        #payment-box {
            border: 2px solid #ccc;
            padding: 20px;
            text-align: center;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .payment-details {
            margin-bottom: 20px;
        }

    </style>
</head>
<body>
    <div id="payment-box">
        <h2>Detalhes do Pagamento</h2>
        <div class="payment-details">${pagamento}</div>
        <form method="GET" action="/confirmarPagamento">
            <input type="hidden" name="evento" value="${evento}">
            <button type="submit"> Confirmar pagamento</button>
        </form>
        <form action="<c:url value="/inscricoesRealizadas" />" method="get">
            <input type="submit" value="Cancelar e voltar às minhas inscrições ">
        </form>
    </div>
</body>
</html>
