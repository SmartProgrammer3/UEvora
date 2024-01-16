<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pagamento Confirmado</title>
    <link rel="icon" href="../static/img/certo.png">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        #confirm-box {
            border: 2px solid #ccc;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="confirm-box">
        <h2>Transação realizada com sucesso</h2>
        <p>Boa sorte no teu evento.</p>
        <table>
            <tr>
                <td>Estado da transação:</td>
                <td>Realizada com sucesso.</td>
            </tr>
            <tr>
                <td>Desejamos-te uma:</td>
                <td>Boa sorte no teu evento.</td>
            </tr>
        </table>
        <form action="<c:url value="/inscricoesRealizadas" />" method="get">
            <input type="submit" value="Voltar às minhas inscrições">
        </form>
    </div>
</body>
</html>
