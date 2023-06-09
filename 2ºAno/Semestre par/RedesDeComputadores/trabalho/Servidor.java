import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Servidor{

    public static void main(String[] args) {
        int portNumber = 5555; // Porta que o servidor irá ouvir
        
        // Cria uma instância do banco de dados
        DataBase database = new DataBase();


        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Servidor TCP iniciado na porta " + portNumber);

            while (true) {
                // Aguarda por uma conexão de um cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Obtém os fluxos de entrada e saída para comunicação com o cliente
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                // Lê e processa os dados recebidos do cliente
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String clientMessage = new String(buffer, 0, bytesRead);
                System.out.println("Mensagem do cliente: " + clientMessage);
                
                // Verifica se o comando é REG (registro de palavra-passe)
                if (clientMessage.startsWith("REG")) {
                    // Extrai o número de aluno e a palavra-passe do comando
                    String[] commandParts = clientMessage.substring(4).trim().split(" ");
                    String studentNumber = commandParts[0];
                    String password = commandParts[1];

                    // Registra o número de aluno e a palavra-passe no banco de dados
                    database.registrar(studentNumber, password);

                    // Envia uma resposta de confirmação para o cliente
                    String serverResponse = "REGISTRATION_SUCCESS";
                    outputStream.write(serverResponse.getBytes());
                }
                // Verifica se o comando é IAM (login)
                else if (clientMessage.startsWith("IAM")) {
                    // Extrai o número de aluno e a palavra-passe do comando
                    String[] commandParts = clientMessage.substring(4).trim().split(" ");
                    String studentNumber = commandParts[0];
                    String password = commandParts[1];

                    // Verifica a autenticação do aluno usando o banco de dados
                    boolean autenticado = database.autenticar(studentNumber, password);

                    // Envia a resposta para o cliente
                    String serverResponse;
                    if (autenticado) {
                        serverResponse = "LOGIN_SUCCESS";
                    } else {
                        serverResponse = "LOGIN_FAILURE";
                    }

                outputStream.write(serverResponse.getBytes());
                }

                // Fecha os fluxos e a conexão com o cliente
                outputStream.close();
                inputStream.close();
                clientSocket.close();
                serverSocket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
}



