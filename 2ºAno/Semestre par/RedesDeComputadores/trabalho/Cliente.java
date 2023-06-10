import java.io.*;
import java.net.*;


public class Cliente{
    // initialize socket and input output streams
    private Socket socket = null;
    BufferedReader reader;
    PrintWriter writer;
    BufferedReader input;

    // constructor to put ip address and port
    public Cliente(String adress, int porta)
    {

        // establish a connection
        try {
            socket = new Socket(adress, porta);
            System.out.println("Connected\n");
            
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(System.in));

            String inputUtilizador = input.readLine();
 
            while (inputUtilizador != null) {
                writer.println(inputUtilizador); // send command to server

                String serverResponse = reader.readLine(); // receive response from server
                System.out.println("Server response: " + serverResponse);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", 5555);
    }


}

