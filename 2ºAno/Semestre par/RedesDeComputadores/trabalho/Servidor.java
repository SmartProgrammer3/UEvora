import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static long tempoInicioConexao;

    public static long getTempoInicio() {
        return tempoInicioConexao;
    }


    public static void main(String[] args) {
        ServerSocket servidor = null;

        try {        
            servidor = new ServerSocket(5555);
            tempoInicioConexao = System.currentTimeMillis();

            while (true) {
                // Aguarda a conexão de um cliente ao servidor
                Socket cliente = servidor.accept();
                System.out.println("O cliente juntou-se à sessão: " + cliente.getInetAddress() + ".");
                
                ClientThread Thread = new ClientThread(cliente, tempoInicioConexao);

                Thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class ClientThread extends Thread {
    private Socket clienteSocket;
    private long tempoInicioAberturaServidor;
    private String cliente = "aluno";
    private int numeroQuestao = 0;

    private Hashtable<String, String> tabelaDosLogins;
    private Hashtable<String, String> tabelaDasPresencas;
    private Hashtable<Integer, QuestaoResposta> tabelaDasPerguntas;
    private HashMap<Integer, String> tabelaRespostas; 

    public long getTempoInicio(){
        return tempoInicioAberturaServidor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }


    public ClientThread(Socket clienteeSocket, long inicioTempoServidor) {
        this.clienteSocket = clienteeSocket;
        this.tempoInicioAberturaServidor = inicioTempoServidor;
      

        tabelaDosLogins = new Hashtable<>();
        tabelaDasPresencas = new Hashtable<>();
        tabelaDasPerguntas = new Hashtable<>();
        tabelaRespostas = new HashMap<>();
    }



    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream())); // Ler as mensagens enviadas pelo cliente
            PrintWriter output = new PrintWriter(clienteSocket.getOutputStream(), true); // Para enviar mensagens de volta ao cliente

            output.println("Seja bem-vindo! Começe por se registar com o comando REG, o respetivo número, WITHPASS, a respetiva passe (Ex: REG lXXXXX WITHPASS 12345)!");            
            boolean continuar = true;

            String linhaInput = input.readLine();
            String[] inicioUser = linhaInput.split(" ");
            String comandoInicio = inicioUser[0];
            String numeroRegisto = "", palavraPasse = "";
            String comandoPasse = inicioUser[2];
            String texto = "";


            while( ((!comandoInicio.equalsIgnoreCase("REG") || numeroRegisto.equals(null)) || (!comandoPasse.equalsIgnoreCase("WITHPASS") || palavraPasse.equals(null))) || continuar){

                if ((comandoInicio.equalsIgnoreCase("REG") && !numeroRegisto.equals(null)) && (comandoPasse.equalsIgnoreCase("WITHPASS") && !palavraPasse.equals(null))) {
                    numeroRegisto = inicioUser[1];
                    palavraPasse = inicioUser[3];
                    tabelaDosLogins.put(numeroRegisto, palavraPasse);

                    output.println("Registo concluído. Faça o login agora, com o comando IAM número WITHPASS passe." + "\nEND");
                    break;
                } 

                output.println("Registo Inválido. Use o formato dito em cima." + "\nEND");
                linhaInput = input.readLine();
                inicioUser = linhaInput.split(" ");
                comandoInicio = inicioUser[0];
                numeroRegisto = inicioUser[1];
                comandoPasse = inicioUser[2];
                palavraPasse = inicioUser[3];
            }

            
            while( (!comandoInicio.equalsIgnoreCase("IAM") || numeroRegisto.equals(null)) || (!comandoPasse.equalsIgnoreCase("WITHPASS") || palavraPasse.equals(null)) || continuar){
                linhaInput = input.readLine();
                inicioUser = linhaInput.split(" ");
                comandoInicio = inicioUser[0];
                numeroRegisto = inicioUser[1];
                comandoPasse = inicioUser[2];
                palavraPasse = inicioUser[3];

                
                if( (comandoInicio.equalsIgnoreCase("IAM") && !numeroRegisto.equals(null)) && (comandoPasse.equalsIgnoreCase("WITHPASS") && !palavraPasse.equals(null))) {
                    
                    if( palavraPasse.equals(tabelaDosLogins.get(numeroRegisto)) ){
                        
                        output.println("HELLO " + numeroRegisto + "\nEND");
                        presencasRegisto(numeroRegisto);

                        break;
                    } else{
                        output.println("ERROR " + numeroRegisto + "\nEND");
                    }

                
                } else{
                    output.println("Login Inválido. Use o formato dito em cima." + "\nEND");
                }
            }

            while ( (linhaInput = input.readLine()) != null) {           
                texto = comandos(linhaInput);
                System.out.println(texto);
                output.println(texto + "\nEND");
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }




    private String comandos(String linha) {
        String resposta = "";
        String linhaSemEspaco[] = linha.split(" ");
        String comando = linhaSemEspaco[0];

        switch(comando.toUpperCase()){
            case "ASK":
                resposta = comandoASK(linha);
                break;
            case "ANSWER":
                linha = linha.substring(7); // Vai retornar sem o comando answer e o " " (6+1 = 7)
                resposta = comandoANSWER(linha);
                break;
            case "LISTQUESTIONS":
                resposta = comandoListQuestions();
                break;
            case "PRESENCE":
                String numeroRegisto = linhaSemEspaco[1];
                resposta = comandoPresenca(numeroRegisto);
                break;

        }




        return resposta;
    }




    // Verificar a presença do cliente
    private String comandoPresenca(String cliente){
        if(tabelaDasPresencas.get(cliente) == null){
            return "Este utilizador não se encontra no servidor.";
        }
        return  tabelaDasPresencas.get(cliente);
    }

    
    private String comandoListQuestions(){
        StringBuilder respostaLista = new StringBuilder();
        

        for (int i = 1; i <= tabelaDasPerguntas.size(); i++) {

            QuestaoResposta question = tabelaDasPerguntas.get(i);

            respostaLista.append("( " + i + " ) " + question.getPergunta() + "\n");

            if (tabelaRespostas.get(i) != null) {
                respostaLista.append( "( " + getCliente() + " ) " + tabelaRespostas.get(i) + "\n");
                respostaLista.append("( " + "professor" + " ) " + question.getResposta() + "\n");

            } else if (getCliente().equalsIgnoreCase("professor")) {
                respostaLista.append("professor " + question.getResposta() + "\n");
            } else {
                respostaLista.append("(NOT ANSWERED)\n");
            }
        }

        return respostaLista.toString();
    }


    private String comandoANSWER(String resposta){
        String RespostaSemEspaco[] = resposta.split(" ");
        int numeroQuestao = Integer.parseInt(RespostaSemEspaco[0]);
        resposta = resposta.substring(2); // Fica só a resposta, sem o número da questão e sem o espaço entre o número e a resposta.

        QuestaoResposta questao = tabelaDasPerguntas.get(numeroQuestao);

        if (questao == null) {
            return ("Não existe nenhuma pergunta com esse indíce: " + numeroQuestao);
        }

        if (getCliente().equalsIgnoreCase("professor")) {

            if (questao.getResposta() != null) { // Se a pergunta não foi feita pelo aluno, e foi pelo professor, então esta é diferente de null, o que significa que já tem resposta.
                return "Resposta já enviada pelo professor para a pergunta Nº " + numeroQuestao;
            }

            questao.setResposta(resposta);
            tabelaDasPerguntas.put(numeroQuestao, questao);
        } else {
            tabelaRespostas.put(numeroQuestao, resposta);
        }

        
        return "REGISTERED: Question " + numeroQuestao;
    }


    private String comandoASK(String pergunta){
        String resposta = "";
        int pontoInterrogacao = pergunta.indexOf("?");

        if(pontoInterrogacao == -1){
            return "Formato da questão Inválido. Deve ter um ponto de interrogação (?)";
        }

        resposta = pergunta.substring(pontoInterrogacao + 1);
        pergunta = pergunta.substring(4, pontoInterrogacao + 1); // Substring 4 -> ponto de interrogação +1, de modo a remover o comando da pergunta (ask).
        System.out.println("Utilizador pergunta: " + pergunta);

        numeroQuestao++;
        if (getCliente().equalsIgnoreCase("professor")) {

            QuestaoResposta questao = new QuestaoResposta(pergunta, resposta);
            tabelaDasPerguntas.put(numeroQuestao, questao);
        } else {
            QuestaoResposta questao = new QuestaoResposta(pergunta, null);
            tabelaDasPerguntas.put(numeroQuestao, questao);

        }
        return "QUESTION" + " " + numeroQuestao + ": " + pergunta;
    }





    private void presencasRegisto(String cliente) {
        long tempoAtrasado = System.currentTimeMillis() - getTempoInicio();

        tempoAtrasado /= 60000; // Está em ms e queremos passar para minutos. 60.000 ms = 1 min
        String presenca;

        switch (cliente){
            case "professor":

                setCliente("professor");
                break;

            default:

                if (tempoAtrasado < 20) {
                    presenca = "Presente";
                } 
                else if ((tempoAtrasado >= 20) && (tempoAtrasado <= 45)){
                    presenca = "Atrasado";
                } else {
                    presenca = "Falta";

                }
                
                setCliente(cliente);
                tabelaDasPresencas.put(cliente, presenca);
                break;
        }
    }
}



class QuestaoResposta{
    private String Pergunta;
    private String Resposta;

    public QuestaoResposta(String pergunta, String resposta) {
        setPergunta(pergunta); 
        setResposta(resposta);
    }

    public String getPergunta() {
        return Pergunta;
    }
    public void setPergunta(String pergunta) {
        Pergunta = pergunta;
    }
    public String getResposta() {
        return Resposta;
    }
    public void setResposta(String resposta) {
        Resposta = resposta;
    }
}


















