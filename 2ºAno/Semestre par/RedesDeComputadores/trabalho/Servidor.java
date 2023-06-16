import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static long tempoInicioConexao;
    final int númeroPorta = 5555; // Porta que o servidor irá ouvir

    


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

    private Hashtable<String, String> tabelaDosLogins;
  

    public ClientThread(Socket clientSocket, long serverstart) {
        this.clienteSocket = clientSocket;
      

        tabelaDosLogins = new Hashtable<>();
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
                output.flush(); // limpar buffer

                if( (comandoInicio.equalsIgnoreCase("IAM") && !numeroRegisto.equals(null)) && (comandoPasse.equalsIgnoreCase("WITHPASS") && !palavraPasse.equals(null))) {

                    if( palavraPasse.equals(tabelaDosLogins.get(numeroRegisto)) ){
    
                        output.println("HELLO " + numeroRegisto + "\nEND");


                        break;
                    } else{
                        output.println("ERRO R" + numeroRegisto + "\nEND");
                    }

                
                } else{
                    output.println("Login Inválido. Use o formato dito em cima." + "\nEND");
                }

                linhaInput = input.readLine();
                inicioUser = linhaInput.split(" ");
                comandoInicio = inicioUser[0];
                numeroRegisto = inicioUser[1];
                comandoPasse = inicioUser[2];
                palavraPasse = inicioUser[3];
            }




        
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}



















