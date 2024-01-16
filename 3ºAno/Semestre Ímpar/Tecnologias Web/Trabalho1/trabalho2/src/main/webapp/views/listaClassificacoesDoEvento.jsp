<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Classificações do Evento</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/classificacoes.css">
    <link rel="icon" href="../static/img/logotipo.png">
    <script src="../static/utils/script.js"></script>
</head>
<body>
    <h2>Classificações do Evento</h2>

    <div class="search">
        <input id="nomedoatleta" placeholder="Nome do Atleta">
        <select id="escalaodosatletas">
            <option value="">Escolha o Escalão</option>
            <option value="Júnior"> Júnior </option>
            <option value="Sénior"> Sénior </option>
            <option value="Vet35"> Vet35 </option>
            <option value="Vet50"> Vet50 </option>
            <option value="Vet65"> Vet65 </option>
        </select>
        <button onclick="filtroClassificacao()">Filtrar</button>
    </div>

    <table id="classificacoes-table">
        <thead>
            <tr>
                <th>Número do Participante</th>
                <th>Nome</th>
                <th>Genero</th>
                <th>Escalão</th>
                <th>Tempo Total (Último Ponto)</th>
                <th></th>
            </tr>
        </thead>
        <tbody id="listaDeClassificacoes">
            ${classificacoes}
        </tbody>
    </table>
    <a href="/informacaoEvento?evento=${evento}" class="back-button">Voltar</a>
</body>
</html>

