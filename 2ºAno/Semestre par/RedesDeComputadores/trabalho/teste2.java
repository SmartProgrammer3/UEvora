import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class servidor {
    public static void main(String[] args) {
        int portNumber = 5555; // Porta que o servidor irá ouvir

        // Inicia o timer
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

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

                // Verifica se o comando é IAM (registro de presença)
                if (clientMessage.startsWith("IAM")) {
                    // Calcula o tempo decorrido
                    long endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;

                    // Extrai o número de aluno do comando
                    String studentNumber = clientMessage.substring(4).trim();

                    // Realiza o registro de presença conforme as regras especificadas
                    String serverResponse;
                    if (isLate(studentNumber)) {
                        if (isVeryLate(studentNumber)) {
                            serverResponse = "PRESENCA_NAO_REGISTADA";
                        } else {
                            serverResponse = "PRESENCA_MEIA";
                        }
                    } else {
                        serverResponse = "PRESENCA_COMPLETA";
                    }

                    // Envia a resposta para o cliente
                    outputStream.write(serverResponse.getBytes());
                } else {
                    // Implemente outras funcionalidades do sistema aqui...
                    // Resto do código...
                }

                // Fecha os fluxos e a conexão com o cliente
                outputStream.close();
                inputStream.close();
                clientSocket.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Tempo decorrido: " + elapsedTime + " milissegundos");
    }

    private static boolean isLate(String studentNumber, int time) {
        // Lógica para verificar se o aluno está atrasado (menos de 20 minutos)
        // Retorne true se estiver atrasado, false caso contrário
        // time < 20 : 2
        // time >= 20 && time <= 45 : 1
        // time > 45 : 0
        return false;
    }

    private static boolean isVeryLate(String studentNumber) {
        // Lógica para verificar se o aluno está muito atrasado (mais de 45 minutos)
        // Retorne true se estiver muito atrasado, false caso contrário
        return false;
    }
}
