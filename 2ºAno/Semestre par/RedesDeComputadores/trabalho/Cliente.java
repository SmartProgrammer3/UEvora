import java.io.*;
import java.net.*;


public class Cliente{
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Connectado\n");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            boolean continuar = true;
            System.out.println(reader.readLine());

            while (continuar) {
                String inputUtilizador = input.readLine();

                writer.println(inputUtilizador); 

                if(inputUtilizador.equalsIgnoreCase("EXIT")){
                    System.out.println("Closing connection...");
                    continuar = false;
                }
                
             
                String respostaDoServidor = null;
                while ((respostaDoServidor = reader.readLine()) != null && !respostaDoServidor.equals("END") ) {

				    System.out.println(respostaDoServidor);
                }
            }
            writer.close();
            reader.close();
            input.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
   









