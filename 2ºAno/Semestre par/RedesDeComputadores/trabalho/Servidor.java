import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    private static long tempoInicioConexao;
    private static Hashtable<Integer, QuestaoResposta> tabelaDasPerguntas;
    private static HashMap<Integer, String> tabelaRespostas;
    public static ArrayList<String> listaDosFicheiros;

    public static long getTempoInicio() {
        return tempoInicioConexao; 
    }

    public static Hashtable<Integer, QuestaoResposta> getTabelaDasPerguntas() {
        return tabelaDasPerguntas;
    }

    public static HashMap<Integer, String> getTabelaRespostas() {
        return tabelaRespostas;
    }

    public static void main(String[] args) {
        ServerSocket servidor = null;
        tabelaDasPerguntas = new Hashtable<>();
        tabelaRespostas = new HashMap<>();
        listaDosFicheiros = new ArrayList<>();

        try {        

            servidor = new ServerSocket(5555);
            tempoInicioConexao = System.currentTimeMillis();

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("O cliente juntou-se à sessão: " + cliente.getInetAddress() + ".");
                
                ClientThread clientThread = new ClientThread(cliente, tempoInicioConexao, tabelaDasPerguntas, tabelaRespostas, listaDosFicheiros);
                clientThread.start();
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
    private InputStream input;
    private OutputStream output;

    private Hashtable<String, String> tabelaDosLogins;
    private Hashtable<String, String> tabelaDasPresencas;
    private Hashtable<Integer, QuestaoResposta> tabelaDasPerguntas;
    private HashMap<Integer, String> tabelaRespostas; 
    protected ArrayList<String> listaDosFicheiros;

    public long getTempoInicio() {
        return tempoInicioAberturaServidor;
    }
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ClientThread(Socket clienteeSocket, long inicioTempoServidor, Hashtable<Integer, QuestaoResposta> tabelaDasPerguntas, HashMap<Integer, String> tabelaRespostas, ArrayList<String> listaDosFicheiros) throws IOException {
        this.clienteSocket = clienteeSocket;
        this.tempoInicioAberturaServidor = inicioTempoServidor;
        this.tabelaDasPerguntas = tabelaDasPerguntas;
        this.tabelaRespostas = tabelaRespostas;
        this.listaDosFicheiros = listaDosFicheiros;
        tabelaDosLogins = new Hashtable<>();
        tabelaDasPresencas = new Hashtable<>();

        input = clienteSocket.getInputStream();
        output = clienteSocket.getOutputStream();
    }

    public void run() {
        try {
            
            byte[] ddd = new byte[1024];
        
            ddd = ("Seja bem-vindo! Começe por se registar com o comando REG, o respetivo número, WITHPASS, a respetiva passe (Ex: REG lXXXXX WITHPASS 12345)!").getBytes();
            output.write(ddd, 0, ddd.length);            
            output.flush();

            boolean continuar = true;

            int numerobytes;
            numerobytes = input.read(ddd);
            if (numerobytes == -1){
                System.out.println("ERRO numerobytes = -1  -> end of stream reached");
            }
            String linhaInput = new String(ddd, 0, numerobytes);
            String[] inicioUser = linhaInput.split(" ");
            String comandoInicio = inicioUser[0];
            String numeroRegisto = "", palavraPasse = "";
            String comandoPasse = inicioUser[2];
            String texto = "";

            // Fazer o registo.
            while( ((!comandoInicio.equalsIgnoreCase("REG") || numeroRegisto.equals(null)) || (!comandoPasse.equalsIgnoreCase("WITHPASS") || palavraPasse.equals(null))) || continuar){

                if ((comandoInicio.equalsIgnoreCase("REG") && !numeroRegisto.equals(null)) && (comandoPasse.equalsIgnoreCase("WITHPASS") && !palavraPasse.equals(null))) {
                    numeroRegisto = inicioUser[1];
                    palavraPasse = inicioUser[3];
                    tabelaDosLogins.put(numeroRegisto, palavraPasse);
                    
                    //output.flush();
                    ddd = ("Registo concluído. Faça o login agora, com o comando IAM número WITHPASS passe." + "\nFIM").getBytes();
                    System.out.println(ddd.length);
                    output.write(ddd,0,ddd.length);  
                    output.flush();
                    break;
                } 
                ddd = ("Registo Inválido. Use o formato dito em cima." + "\nFIM").getBytes();
                System.out.println(ddd.length);
                output.write(ddd,0,ddd.length);  
                output.flush();


                numerobytes = input.read(ddd);
                linhaInput = new String(ddd, 0, numerobytes);
                inicioUser = linhaInput.split(" ");
                comandoInicio = inicioUser[0];
                numeroRegisto = inicioUser[1];
                comandoPasse = inicioUser[2];
                palavraPasse = inicioUser[3];
            }

            while( (!comandoInicio.equalsIgnoreCase("IAM") || numeroRegisto.equals(null)) || (!comandoPasse.equalsIgnoreCase("WITHPASS") || palavraPasse.equals(null)) || continuar){                                

                numerobytes = input.read(ddd);
                linhaInput = new String(ddd, 0, numerobytes);         
                inicioUser = linhaInput.split(" ");
                comandoInicio = inicioUser[0];
                numeroRegisto = inicioUser[1];
                comandoPasse = inicioUser[2];
                palavraPasse = inicioUser[3];
  
                
                if( (comandoInicio.equalsIgnoreCase("IAM") && !numeroRegisto.equals(null)) && (comandoPasse.equalsIgnoreCase("WITHPASS") && !palavraPasse.equals(null))) {
                   
                    if( palavraPasse.equals(tabelaDosLogins.get(numeroRegisto)) ){
                        setCliente(numeroRegisto);
                        System.out.println("olaaa");
                        
                        ddd = ("HELLO " + numeroRegisto + "\nFIM").getBytes();
                        output.write(ddd,0,ddd.length); 
                        output.flush();
                        presencasRegisto(numeroRegisto);
                     

                        break;
                    } else{
                       
                        ddd = ("ERROR " + numeroRegisto + "\nFIM").getBytes();
                        output.write(ddd,0,ddd.length); 
                        output.flush();
                    }

                
                } else{
                    ddd = ("Login Inválido. Use o formato dito em cima." + "\nFIM").getBytes();
                    output.write(ddd,0,ddd.length);  
                    output.flush();

                }
            }

                
            while ( (numerobytes = input.read(ddd)) != -1  ) {   
                linhaInput = new String(ddd, 0, numerobytes);
                System.out.println(numerobytes);
                System.out.println(linhaInput);
                if (linhaInput.equals("exit")) {

                    ddd = ("Até FIM").getBytes();
                    output.write(ddd,0,ddd.length); 
                    break;
                }

                texto = comandos(linhaInput);
                System.out.println(texto);

                ddd = (texto + "\nFIM").getBytes();
                output.write(ddd,0,ddd.length); 
                output.flush();
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
    }





    // Lista de comandos possíveis de realizar dentro do servidor
    private String comandos(String linha) {
        String resposta = "";
        String linhaSemEspaco[] = linha.split(" ");
        String comando = linhaSemEspaco[0];

        switch(comando.toUpperCase()){
            case "ASK":         // Fazer perguntas
                resposta = comandoASK(linha);
                break;  
            case "ANSWER":          // Responder
                linha = linha.substring(7); // Vai retornar sem o comando answer e o " " (6+1 = 7)
                resposta = comandoANSWER(linha);
                break;
            case "LISTQUESTIONS":       // Fazer lista das questões e respetivas respostas do professor e dos alunos
                resposta = comandoListQuestions() + "ENDQUESTIONS";
                break;
            case "PUTFILE":
                resposta = comandoPUTFILE(linha);
                break;

            case "GETFILE": 
                resposta = comandoGETFILE(Integer.parseInt(linha)); 
                break;

            case "LISTFILES":
                resposta = comandoLISTFILES();
                break;

            case "PRESENCE":            // Verificar a presenças dos respetivos clientes / alunos
                String numeroRegisto = linhaSemEspaco[1];
                resposta = comandoPresenca(numeroRegisto);
                break;
            case "REMOVE":              // Comando utilizado apenas para o cliente "professor" para eliminar todas as respostas erradas (diferentes que a sua)
                String numeroQuestao = linhaSemEspaco[1];
                resposta = comandoRemoveAnswer(numeroQuestao);
                break;
            
            default:
                return "Não tens comando para isso";

        }
        return resposta;
    }

  
    public String comandoLISTFILES() {
        File diretorio = new File(".");
        File[] listaDeFicheiros = diretorio.listFiles();
        
        if (listaDeFicheiros != null) {
            for (File ficheiro : listaDeFicheiros) {
                if (ficheiro.isFile()) {
                    System.out.println(ficheiro.getName());
                }
            }
        }
        return "FIM";   
    }


    private String comandoGETFILE(int n){
        return "comandoGETFILE";
    }

    private String comandoPUTFILE(String args) {

        String[] parts = args.split(" ");
        String filename = "put_" + parts[0];
        int bytes = Integer.parseInt(parts[1]);

        File file = new File(filename);  //path + filename
        FileOutputStream fos = null;
        InputStream in = null;

        byte[] bytearray = new byte[bytes];
        int read;

        try {
            fos = new FileOutputStream(file);
            in = clienteSocket.getInputStream();

            read = in.read(bytearray);

            fos.write(bytearray, 0, read);

            // Adiciona o nome do ficheiro a lista de ficheiros
            synchronized (listaDosFicheiros) {
                listaDosFicheiros.add(filename);
            }

        } catch (IOException e) {
            System.out.println("Falhou: " + filename);
            e.printStackTrace();

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
        }

        return "UPLOADED" + " " + filename;
    }


    private String comandoRemoveAnswer(String numeroQuestao) {
        String RespostaSemEspaco[] = numeroQuestao.split(" "); // Retirar o número da questão
        int numerQuestao = Integer.parseInt(RespostaSemEspaco[0]);  // Está em string passar para inteiro

        if (getCliente().equalsIgnoreCase("professor")) {   // Tem que ser o professor
            QuestaoResposta questao = tabelaDasPerguntas.get(numerQuestao); // Retira a questão a avaliar

            if (questao != null) { // Verifica se a questão é válida
                if (questao.getResposta() != null) { // Verifica se tem resposta
                    String respostaProfessor = questao.getResposta();  // Guarda a resposta do professor
                    String respostas = tabelaRespostas.get(numerQuestao);

                    if (respostas != null && !respostas.isEmpty()) { // Verifica se hà respostas associadas à questão

                        String[] partesRespostas = respostas.split(";"); // Divide cada resposta com ;
                        StringBuilder respostasCorretas = new StringBuilder();

                        for (String resposta : partesRespostas) {  // Avalia cada resposta
                            String respostaCliente = resposta.substring(resposta.indexOf(" ") + 1); // Obtém a parte da resposta após o primeiro espaço em branco, excluindo assim o número associado à resposta. 

                            if (respostaCliente.equals(respostaProfessor)) { // Verifica se a resposta do cliente, representada por respostaCliente, é igual à resposta do professor, representada por respostaProfessor.
                                respostasCorretas.append(resposta).append(";"); // Se a resposta do cliente for igual à resposta do professor, a resposta completa é adicionada ao StringBuilder respostasCorretas, juntamente com um ponto e vírgula.
                            }
                        }
                        
                        // Verifica se há respostas corretas encontradas, ou seja, se o StringBuilder respostasCorretas contém algum texto.
                        if (respostasCorretas.length() > 0) {
                            tabelaRespostas.put(numerQuestao, respostasCorretas.substring(0, respostasCorretas.length() - 1)); // Se houver respostas corretas, atualiza a tabela de respostas (tabelaRespostas) com a nova string de respostas corretas, removendo o último ponto e vírgula adicionado.
                        } else {
                            tabelaRespostas.remove(numerQuestao); // Se não houver respostas corretas encontradas, remove a entrada correspondente à questão da tabela de respostas (tabelaRespostas).
                        }
                    }
                }

                questao.setResposta(null);
                tabelaDasPerguntas.put(numerQuestao, questao);
                return "Respostas removidas para a questão " + numerQuestao;

            } else {
                return "Estás a tentar remover uma resposta que não existe";
            }
        } else {
            return "Não tens permissão. Só o professor pode remover respostas erradas";
        }
    }


    // Verificar a presença do cliente
    private String comandoPresenca(String cliente){
        if(tabelaDasPresencas.get(cliente) == null){
            return "Este utilizador não se encontra no servidor.";
        }
        return  tabelaDasPresencas.get(cliente);
    }

    
    private String comandoListQuestions() {
        StringBuilder respostaLista = new StringBuilder();

        for (int i = 1; i <= tabelaDasPerguntas.size(); i++) {  // Avaliar todas as questões da tabela (Para mostrar nos terminais)
            QuestaoResposta question = tabelaDasPerguntas.get(i); // Obtém a questão da tabela de perguntas 

            respostaLista.append("( " + i + " ) " + question.getPergunta() + " ?" + "\n");

            if (tabelaRespostas.containsKey(i)) { // Verifica se a tabela de respostas (tabelaRespostas) contém uma entrada para a questão com o índice i.
                String respostas = tabelaRespostas.get(i); // Obtém a string de respostas associadas à questão com o índice i da tabela de respostas e a armazena na variável respostas.
                String[] partesRespostas = respostas.split(";"); 

                for (String resposta : partesRespostas) {
                    String[] partes = resposta.split(" ");
                    String cliente = partes[0]; // O cliente
                    String respostaCliente = resposta.substring(resposta.indexOf(" ") + 1); // A resposta do respetivo cliente
                    respostaLista.append("( " + cliente + " ) " + respostaCliente + "\n");
                }
            }

            if (getCliente().equalsIgnoreCase("professor")) { // Se for o professor, mostra a sua resposta (só ao professor)
                respostaLista.append("( professor ) " + question.getResposta() + "\n");
            } else {
                // Não foi respondida ainda
                respostaLista.append("(NOT ANSWERED)\n");

            }
        }

        return respostaLista.toString();
    }




    // Respostas
    private String comandoANSWER(String resposta) {
        String RespostaSemEspaco[] = resposta.split(" ");
        int numeroQuestao = Integer.parseInt(RespostaSemEspaco[0]); // numero da questao
        resposta = resposta.substring(2); // Fica só a resposta, sem o número da questão e sem o espaço entre o número e a resposta.

        QuestaoResposta questao = tabelaDasPerguntas.get(numeroQuestao); 

        if (questao == null) {
            return ("Não existe nenhuma pergunta com esse índice: " + numeroQuestao);
        }

        if (getCliente().equalsIgnoreCase("professor")) {
            questao.setResposta(resposta);
            tabelaDasPerguntas.put(numeroQuestao, questao);
        } else {
            String respostaCliente = getCliente() + " " + resposta;

            if (tabelaRespostas.containsKey(numeroQuestao)) {
                String respostasAntigas = tabelaRespostas.get(numeroQuestao);
                tabelaRespostas.put(numeroQuestao, respostasAntigas + ";" + respostaCliente);
            } else {
                tabelaRespostas.put(numeroQuestao, respostaCliente);
            }
        }

        return "REGISTERED: Question " + numeroQuestao;
    }



    // Questoes
    private String comandoASK(String pergunta) {
        String resposta = "";
        int pontoInterrogacao = pergunta.indexOf("?");

        if (pontoInterrogacao == -1) {
            return "Formato da questão inválido. Deve ter um ponto de interrogação (?)";
        }

        resposta = pergunta.substring(pontoInterrogacao + 1);  // Para o caso do professor, se depois da pergunta (?), ele quiser meter a resposta
        pergunta = pergunta.substring(4, pontoInterrogacao); // Substring 4 -> ponto de interrogação (+1?), de modo a remover o comando da pergunta (ask).
        System.out.println("Utilizador pergunta: " + pergunta);

        int numeroQuestao = tabelaDasPerguntas.size() + 1;

        if (getCliente().equalsIgnoreCase("professor")) {
            QuestaoResposta questao = new QuestaoResposta(pergunta, resposta);
            tabelaDasPerguntas.put(numeroQuestao, questao);
        } else {
            QuestaoResposta questao = new QuestaoResposta(pergunta, null);
            tabelaDasPerguntas.put(numeroQuestao, questao);
        }

        return "QUESTION" + " " + numeroQuestao + ": " + pergunta + "?";
    }



    // Verificar a presença 
    private void presencasRegisto(String cliente) {
        long tempoAtrasado = System.currentTimeMillis() - getTempoInicio();

        tempoAtrasado /= 60000; // Está em ms e queremos passar para minutos. 60.000 ms = 1 min
        String presenca;

        switch (cliente){
            case "professor":
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
              
                tabelaDasPresencas.put(cliente, presenca);
                break;
        }
    }
}


// Classe que guarda as perguntas e respostas, respetivamente
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