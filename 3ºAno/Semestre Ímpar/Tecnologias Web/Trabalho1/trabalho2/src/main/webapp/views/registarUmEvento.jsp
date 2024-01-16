<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registar um Evento</title>
    <link rel="stylesheet" type="text/css" href="../static/css/eventoregistar.css">
    <link rel="icon" href="../static/img/logotipo.png">
</head>
<body>

    <h1> Registar um Evento </h1>

    <div>
        <form name='eventForm' action="/regevento" method="GET">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
            <table>
                <tr>
                    <th>Detalhes do Evento</th>
                </tr>
                <tr>
                    <td><label for="nomeEvento">Nome do Evento:</label></td>
                    <td><input type="text" id="nomeEvento" name="nomeEvento" required></td>
                </tr>
                <tr>
                    <td><label for="dataEvento">Data do Evento:</label></td>
                    <td><input type="date" id="dataEvento" name="dataEvento" required></td>
                </tr>
                <tr>
                    <td><label for="descricaoEvento">Descrição do Evento:</label></td>
                    <td><textarea id="descricaoEvento" name="descricaoEvento" required></textarea></td>
                </tr>
                <tr>
                    <td><label for="valorInscricao">Valor da Inscrição:</label></td>
                    <td><input type="text" id="valorInscricao" name="valorInscricao" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Criar Evento"></td>
                </tr>
            </table>
        </form>

        <a href="/">Voltar</a>
    </div>

</body>
</html>
