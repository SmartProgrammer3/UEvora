package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Registar{
    private String username, password, email, genero, escalao;
    private String nomeEvento, dataEvento, descricao, valor;
    private String tempoParticipante, pontoParticipante;
    private int numParticipante;


    // para registar tempos
    public Registar(int numParticipante, String nomeEvento, String pontoParticipante, String tempoParticipante) {
        this.numParticipante = numParticipante;
        this.nomeEvento = nomeEvento;
        this.pontoParticipante = pontoParticipante;
        this.tempoParticipante = tempoParticipante;      
    }

    public Registar() {

    }

    // Serve como auxílio para procurar, por exemplo para ir buscar a respetiva role na app.
    public Registar(String username) {
        this.username = username;
    }

    // Para facilitar as inscrições
    public Registar(String username, String nomeEvento) {
        this.username = username;
        this.nomeEvento = nomeEvento;
    }


    // Para registar utilizadores
    public Registar(String username, String password, String email, String genero, String escalao){
        this.username = username;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.escalao = escalao;
    }

    // Para registar eventos
    public Registar(String nomeEvento, String dataEvento, String descricao, String valor) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.valor = valor;
    }

    // para registar o utilizador na base de dados
    public int RegistarNome() throws Exception {
        BCryptPasswordEncoder benc = new BCryptPasswordEncoder();
        String ep = benc.encode(password.subSequence(0, password.length()));

        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();


        ResultSet resultSet = statement.executeQuery("SELECT username from tabelaDeUtilizadores where username = '" + username + "'");
        
        if (resultSet.next()) {
            connect.disconnect();
            return -1;
        }

        statement.executeUpdate("INSERT INTO tabelaDeUtilizadores VALUES ('" + username + "', '" + ep + "', '" + email + "', '" + genero + "', '" + escalao + "', 1)");
        statement.executeUpdate("INSERT INTO roleUtilizadores VALUES ('" + username + "', 'ROLE_ATLETA')");

        connect.disconnect();

        return 1;
    }

    // Para vir buscar a role do utilizador
    public String getRole() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        ResultSet resultSet = statement.executeQuery("SELECT user_role from roleUtilizadores where username = '" + username + "'");
        resultSet.next();

        return resultSet.getString("user_role"); 
    }


    public int RegistarEvento() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        ResultSet resultSet = statement.executeQuery("SELECT nomeevento from eventos where nomeevento = '" + nomeEvento + "'");
        
        if (resultSet.next()) {
            connect.disconnect();
            return -1;
        }
        statement.executeUpdate("INSERT INTO eventos VALUES ('" + nomeEvento + "', '" + descricao + "', '" + dataEvento + "', " + Float.parseFloat(valor) + ")");

        connect.disconnect();
        return 1;
    }



    public static JSONObject getReferenciaMB(String amount) throws Exception {
        try {
            URL url = new URL("https://magno.di.uevora.pt/tweb/t2/mbref4payment");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // Definir o método para POST
            con.setDoOutput(true); // Permitir saída

            // Enviar dados no corpo da solicitação, o metodo POST envia em bytes
            String postData = "amount=" + amount;
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder result = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    System.out.println(line);
                    result.append(line);
                }
            }

            return new JSONObject(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Obtém eventos disponíveis para inscrição, excluindo aqueles nos quais o participante já está inscrito e/ou a data já passou, 
     * Retorna Um JSONArray, que contém objetos JSONObject para cada evento disponível, o nome do evento, preço e data do evento.
    */
    public JSONArray getEventosDisponiveis() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String query = "SELECT nomeevento, data, valor FROM eventos WHERE data>='" + dateFormat.format(date) + "'" +
                " EXCEPT SELECT nomeevento, data, valor from eventos NATURAL INNER JOIN inscricoes WHERE nomeParticipante='" + username + "' ORDER BY data";

        ResultSet resultSet = statement.executeQuery(query);

        JSONArray arrayDeEventos = new JSONArray();

        while (resultSet.next()) {
            JSONObject umEvento = new JSONObject();
            umEvento.put("NomeDoEvento", resultSet.getString("nomeevento"));
            umEvento.put("PrecoDoEvento", resultSet.getString("valor"));
            umEvento.put("DataDoEvento", resultSet.getString("data"));
            arrayDeEventos.put(umEvento);
        }

        return arrayDeEventos;
    }


    // É uma especie de id para cada inscricao. Vai dar jeito depois para o numero de participantes no evento.
    protected int getIDTabelaInscricao(String nomeEvento) {
        int id = -1;
        String query = "SELECT MAX(nparticipante) as ultimoID FROM inscricoes WHERE nomeevento = '" + nomeEvento + "'";
        
        try {
            PostGresConnect pc = new PostGresConnect();
            pc.connect();

            Statement statement = pc.getStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();

            if (resultSet.getString("ultimoID") == null) {
                id = 0;
            } else {
                id = Integer.parseInt(resultSet.getString("ultimoID"));
            }
            pc.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }



    public JSONObject fazerInscricao() throws Exception {
        String referencia = "", entidade = "", aPagar = "";
        int id = getIDTabelaInscricao(nomeEvento) + 1;

        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        // Verifica se o utilizador está na base de dados.
        ResultSet resultSet = statement.executeQuery("SELECT username from tabelaDeUtilizadores where username = '" + username + "'");
        if (!resultSet.next()) {
            connect.disconnect();
            return null;
        }

        // Verifica se o evento existe na base de dados.
        resultSet = statement.executeQuery("SELECT nomeevento, valor from eventos where nomeevento = '" + nomeEvento + "'");
        if (!resultSet.next()) {
            connect.disconnect();
            return null;
        } 
        System.out.println(resultSet.getString("valor"));
        aPagar = resultSet.getString("valor");

        // Verifica se o utilizador já está inscrito no evento
        resultSet = statement.executeQuery("SELECT nomeParticipante from inscricoes where nomeevento = '" + nomeEvento + "' and nomeParticipante = '" + username + "'");
        if (resultSet.next()) {
            connect.disconnect();
            return null;
        }

        JSONObject a = getReferenciaMB(aPagar);
        entidade = a.getString("mb_entity");
        referencia = a.getString("mb_reference");



        statement.executeUpdate("INSERT INTO inscricoes VALUES ('" + username + "', '" + nomeEvento + "', " + id + ")");
        statement.executeUpdate("INSERT INTO referencias VALUES ('" + username + "', " + entidade + ", " + referencia + ", " + a.getString("mb_amount") + ", '" + nomeEvento + "')");

        connect.disconnect();

        JSONObject insc = new JSONObject();
        insc.put("ID", id);
        insc.put("Entidade", entidade);
        insc.put("ref", referencia);
        insc.put("quantia", aPagar);

        return insc;
    }


    // Retorna um array JSON com todos os eventos pagos
    public JSONArray getEventosPagos(String username) throws Exception {
        JSONArray eventosPagos = new JSONArray();

        PostGresConnect connect = new PostGresConnect();
        connect.connect();

        String query = "SELECT * FROM eventos NATURAL INNER JOIN pagamentos WHERE username='" + username + "' ORDER BY data";

        Statement statement = connect.getStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            JSONObject eventoSim = new JSONObject();

            eventoSim.put("NomeDoEvento", resultSet.getString("nomeevento"));
            eventoSim.put("PrecoDoEvento", resultSet.getString("valor"));
            eventoSim.put("DataDoEvento", resultSet.getString("data"));
            eventosPagos.put(eventoSim);
        }

        return eventosPagos;
    }


    // Retorna um array JSON com todos os eventos não pagos
    public JSONArray getEventosNaoPagos(String username) throws Exception {
        JSONArray eventosNaoPagos = new JSONArray();

        PostGresConnect connect = new PostGresConnect();
        connect.connect();

        String query = "SELECT nomeevento, data, valor FROM eventos NATURAL INNER JOIN inscricoes WHERE nomeParticipante = '" + username +
                "' EXCEPT SELECT nomeevento, data, valor FROM eventos NATURAL INNER JOIN pagamentos WHERE username='" + username + "' ORDER BY data";

        Statement statement = connect.getStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            JSONObject eventoNao = new JSONObject();

            eventoNao.put("NomeDoEvento", resultSet.getString("nomeevento"));
            eventoNao.put("PrecoDoEvento", resultSet.getString("valor"));
            eventoNao.put("DataDoEvento", resultSet.getString("data"));
            eventosNaoPagos.put(eventoNao);
        }

        return eventosNaoPagos;
    }



    public JSONObject getDetalhesDoPagamento() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        // Retirar os detalhes da tabela referencias, entidade, referencia, valor
        String query = "SELECT * FROM referencias WHERE username='" + username + "' AND nomeevento='" + nomeEvento + "'";

        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        JSONObject referencia = new JSONObject();

        referencia.put("Entidade", resultSet.getString("entidade"));
        referencia.put("Referencia", resultSet.getString("referencia"));
        referencia.put("Valor", resultSet.getString("valor"));

        return referencia;
    }


    public void pagar() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        String query = "INSERT INTO pagamentos VALUES ('" + username + "', '" + nomeEvento + "')";

        statement.executeUpdate(query);
        connect.disconnect();
    }



    public JSONArray getEventos(String filtro) throws Exception {
        char auxComparar = '=';

        switch (filtro) {
            case "passados":
                auxComparar = '<';
                break;
            case "futuros":
                auxComparar = '>';
                break;
            case "todos":
                auxComparar = ' ';
            default:
                break;
        }

        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        Date dataAtual = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy"); // Formato da data

        String query = "";

        if (auxComparar != ' ') {
            query = "SELECT * FROM eventos WHERE data" + auxComparar + "'" + formatoData.format(dataAtual) + "' ORDER BY data";
        } else {
            query = "SELECT * FROM eventos";
        }


        ResultSet resultSet = statement.executeQuery(query);

        JSONArray eventos = new JSONArray();

        while (resultSet.next()) {
            JSONObject evento = new JSONObject();

            evento.put("nomeDoEvento", resultSet.getString("nomeevento"));
            evento.put("descricaoDoEvento", resultSet.getString("descricao"));
            evento.put("dataDoEvento", resultSet.getString("data"));
            evento.put("preco", resultSet.getString("valor"));

            eventos.put(evento);
        }
        return eventos;
    }


    static String getPontoDaProvaAnterior(String ponto) {
        if (ponto.equals("p1")) {
            return "start";
        } 
        else if (ponto.equals("p2")) {
            return "p1";
        } 
        else if (ponto.equals("p3")) {
            return "p2";
        }
        else if (ponto.equals("finish")) {
            return "p3";
        }
        return "";
    }



    public int registarTempo() throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        ResultSet resultSet = statement.executeQuery("SELECT nparticipante from inscricoes where nomeevento = '" + nomeEvento + "' and nparticipante = '" + numParticipante + "'");

        // Este atleta ainda não esta inscrito no evento
        if (!resultSet.next()) {
            connect.disconnect();
            return -1; 
        }

        resultSet = statement.executeQuery("SELECT nomeevento, nparticipante from pagamentos natural inner join inscricoes where nomeevento ='" + nomeEvento + "' and nparticipante ='" + numParticipante + "'");

        // Este atleta ainda não pagou a inscrição
        if (!resultSet.next()) {
            connect.disconnect();
            return -2; 
        }


        String pontoAnterior = getPontoDaProvaAnterior(pontoParticipante);

        if (!pontoParticipante.equals("start")) {
            String query = "SELECT * from tempos where nomeevento = '" + nomeEvento + "' and nparticipante = " + numParticipante + " and pontodaprova = '" + pontoAnterior + "'";
            resultSet = statement.executeQuery(query);
            // Se ainda não existe tempo para o ponto da prova anterior, logo nao podemos registar o tempo para este ponto da prova.  
            if (!resultSet.next()) {
                connect.disconnect();
                return -3;   
            }
        }
      

        resultSet = statement.executeQuery("SELECT * from tempos where nomeevento = '" + nomeEvento + "' and nparticipante = '" + numParticipante + "' and pontodaprova = '" + pontoParticipante + "'");
        // Já existe um tempo registado para aquele ponto de partida, naquele evento, para aquele atleta
        if (resultSet.next()) {
            connect.disconnect();
            return -4; 
        }
      
        
        resultSet = statement.executeQuery("SELECT * from eventos where nomeevento = '" + nomeEvento + "'");
        resultSet.next();


        String dataDoEventoAAvaliar = resultSet.getString("data");

        statement.executeUpdate("INSERT INTO tempos VALUES ('" + nomeEvento + "', '" + numParticipante + "', '" + pontoParticipante + "', TO_TIMESTAMP('" + dataDoEventoAAvaliar + " " + tempoParticipante + "', 'YYYY-MM-DD HH24:MI:SS'))");


        connect.disconnect();
        return 1;
    }



    // Vir buscar a descricao, data e valor do evento
    public JSONObject informacaoDoEvento(String nomeEvento) throws Exception {
        JSONObject infoEvento = new JSONObject();

        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();

        String query = "SELECT * FROM eventos WHERE nomeevento='" + nomeEvento + "'";
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            infoEvento.put("descricaoDoEvento", resultSet.getString("descricao"));
            infoEvento.put("dataDoEvento", resultSet.getString("data"));
            infoEvento.put("preco", resultSet.getString("valor"));
        }

        return infoEvento;
    }


    public JSONArray participantesDoEvento(String nomeEvento) throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();
    
        // Atualize esta linha para incluir o campo genero
        // Primeiro, obtemos todos os participantes e marcamos todos como não pagos
        String query = "SELECT username, escalao, nparticipante, genero FROM inscricoes INNER JOIN tabelaDeUtilizadores ON username = nomeParticipante WHERE nomeevento='" + nomeEvento + "'";
        ResultSet resultSet = statement.executeQuery(query);
        JSONArray listaDeParticipantes = new JSONArray();

        while (resultSet.next()) {
            JSONObject participante = new JSONObject();

            participante.put("nomeParticipante", resultSet.getString("username"));
            participante.put("escalaoParticipante", resultSet.getString("escalao"));
            participante.put("nParticipante", resultSet.getInt("nparticipante"));
            participante.put("generoParticipante", resultSet.getString("genero"));
            participante.put("pagamento", false);

            listaDeParticipantes.put(participante);
        }

        // Ir buscar o nparticipante dos que já fizeram o pagamento do evento
        query = "SELECT username, nparticipante from pagamentos inner join inscricoes ON nomeParticipante=username WHERE pagamentos.nomeevento='" + nomeEvento + "'";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int nparticipantePagamentosRealizados = resultSet.getInt("nparticipante");

            // Procura e atualiza o estado de pagamento para verdadeiro.
            for (int i = 0; i < listaDeParticipantes.length(); i++) {
                JSONObject participanteNoEvento = listaDeParticipantes.getJSONObject(i);

                if (participanteNoEvento.getInt("nParticipante") == nparticipantePagamentosRealizados) {
                    participanteNoEvento.put("pagamento", true);
                }
            }
        }

        return listaDeParticipantes;
    }
    
    
    // Método para verificar se o participante tem algum tempo finish (Facilita os calculos para o metodo do getFinalizado)
    public boolean verificarFinalizacao(String nomeEvento, int nparticipante) throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();
        // Vai verificar se o participante tem um ponto da prova de finish, ou seja finalizou a prova.
        String query = "SELECT COUNT(*) as count FROM tempos " +
                       "WHERE nomeevento = '" + nomeEvento + "' AND " +
                       "nparticipante = " + nparticipante + " AND " +
                       "pontodaprova = 'finish';";
    
        ResultSet resultSet = statement.executeQuery(query);

    
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            if(count > 0){
                return true; 
            }
        }
        return false;
    }

    // Método para retornar os tempos e participantes que finalizaram a prova.
    public JSONArray getFinalizados(String nomeEvento) throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();
    
        JSONArray finalizados = new JSONArray();
        // Vai buscar os participantes do evento cuja classificação estamos a avaliar
        JSONArray participantes = participantesDoEvento(nomeEvento);
    
        for (int i = 0; i < participantes.length(); i++) {

            JSONObject participante = participantes.getJSONObject(i);
            int nparticipante = participante.getInt("nParticipante");
    
            // Verificamos se o participante finalizou a prova
            if (verificarFinalizacao(nomeEvento, nparticipante)) {

                
                String queryTempo = "SELECT EXTRACT(EPOCH FROM (finish.tempodatahora - start.tempodatahora)) AS tempoTotal " +   //  total de segundos que compõem a duração desde o ponto da prova 'start' até 'finish'
                                    "FROM tempos start JOIN tempos finish " +
                                    "ON start.nparticipante = finish.nparticipante AND start.nomeevento = finish.nomeevento " +
                                    "WHERE start.pontodaprova = 'start' AND finish.pontodaprova = 'finish' AND " +
                                    "start.nomeevento = '" + nomeEvento + "' AND start.nparticipante = " + nparticipante + ";";
    
                ResultSet resultSetTempo = statement.executeQuery(queryTempo);
                if (resultSetTempo.next()) {
                    JSONObject finalizado = new JSONObject();
                    finalizado.put("nparticipante", nparticipante);
                    finalizado.put("nomeParticipante", participante.getString("nomeParticipante"));
                    finalizado.put("tempoTotal", resultSetTempo.getDouble("tempoTotal"));
                    finalizado.put("escalao", participante.getString("escalaoParticipante"));
                    finalizado.put("genero", participante.getString("generoParticipante"));
                    finalizados.put(finalizado);
                }
            }
        }
        statement.close();
        connect.disconnect();
    
        return finalizados;
    }


    public JSONObject getUltimoPontoETempo(String nomeEvento, int nparticipante) throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();
    
        String query = "SELECT pontodaprova, EXTRACT(EPOCH FROM (tempodatahora - (SELECT tempodatahora FROM tempos WHERE pontodaprova = 'start' AND nparticipante = " + nparticipante + " AND nomeevento = '" + nomeEvento + "'))) AS tempoTotal " +
                       "FROM tempos " +
                       "WHERE nparticipante = " + nparticipante + " AND nomeevento = '" + nomeEvento + "' " +
                       "ORDER BY tempodatahora DESC LIMIT 1;";
    
        ResultSet resultSet = statement.executeQuery(query);
        JSONObject ultimoPontoETempo = new JSONObject();
    
        if (resultSet.next()) {
            ultimoPontoETempo.put("ultimoPontoDaProva", resultSet.getString("pontodaprova"));
            ultimoPontoETempo.put("tempoTotal", resultSet.getDouble("tempoTotal"));
        }
    
        statement.close();
        connect.disconnect();
    
        return ultimoPontoETempo;
    }
    
    
    
    public JSONArray getNaoFinalizados(String nomeEvento) throws Exception {
        JSONArray naoFinalizados = new JSONArray();
        JSONArray participantes = participantesDoEvento(nomeEvento);

        for (int i = 0; i < participantes.length(); i++) {
            JSONObject participante = participantes.getJSONObject(i);
            int nparticipante = participante.getInt("nParticipante");
        
            // Verifica se o participante não finalizou a prova
            if (!verificarFinalizacao(nomeEvento, nparticipante)) {
                JSONObject ultimoPontoETempo = getUltimoPontoETempo(nomeEvento, nparticipante);
        
                if(ultimoPontoETempo.length() > 0) {
                    JSONObject naoFinalizado = new JSONObject();
                    naoFinalizado.put("nparticipante", nparticipante);
                    naoFinalizado.put("nomeParticipante", participante.getString("nomeParticipante"));
                    naoFinalizado.put("tempoTotal", ultimoPontoETempo.getDouble("tempoTotal"));
                    naoFinalizado.put("ultimoPontoDaProva", ultimoPontoETempo.getString("ultimoPontoDaProva"));
                    naoFinalizado.put("escalao", participante.getString("escalaoParticipante"));
                    naoFinalizado.put("genero", participante.getString("generoParticipante"));
                    naoFinalizados.put(naoFinalizado);
                }
            }
        }
        
        return naoFinalizados;
    }




    public JSONObject classificacaoDoEvento(String nomeEvento) throws Exception {
        JSONObject resultado = new JSONObject();
    

        JSONArray finalizados = getFinalizados(nomeEvento);
        resultado.put("finalizados", finalizados);
    
        JSONArray naoFinalizados = getNaoFinalizados(nomeEvento);
        resultado.put("naoFinalizados", naoFinalizados);    
        return resultado;
    }
    

    public JSONObject temposPorPonto(String nomeEvento, int nparticipante) throws Exception {
        PostGresConnect connect = new PostGresConnect();
        connect.connect();
        Statement statement = connect.getStatement();
    
        // Criar um JSONObject para armazenar os tempos de cada ponto da prova para o participante especificado
        JSONObject temposParticipante = new JSONObject();
    
        // Buscar os tempos para cada ponto da prova para o participante específico
        String query = "SELECT pontodaprova, tempodatahora " +
                       "FROM tempos " +
                       "WHERE nomeevento = '" + nomeEvento + "' AND nparticipante = " + nparticipante + ";";
    
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            temposParticipante.put(resultSet.getString("pontodaprova"), resultSet.getTimestamp("tempodatahora").toString());
        }
    
        statement.close();
        connect.disconnect();
    
        return temposParticipante;
    }
    
    
    




}









