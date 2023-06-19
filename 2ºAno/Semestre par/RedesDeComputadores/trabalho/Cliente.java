import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Cliente{
    final static String path = "C:/Users/gonca/OneDrive/Ambiente de Trabalho/Uevora-CloneGithub/UEvora/2ºAno/Semestre Par/RedesDeComputadores/trabalho/Ficheiro";
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Connectado\n");

            InputStream reader = socket.getInputStream();
            OutputStream writer =socket.getOutputStream();
            Scanner s = new Scanner(System.in);
            byte[] dddd = new byte[1024]; 
            String inputUtilizador;
            int numerobytes;
            

            numerobytes = reader.read(dddd);
            String resposta = new String(dddd, 0, numerobytes);

            boolean continuar = true;
            System.out.println(resposta);

            while (continuar) {
                inputUtilizador = s.nextLine();

                dddd = inputUtilizador.getBytes();

                writer.write(dddd, 0, dddd.length); 
                writer.flush();

                if(inputUtilizador.equalsIgnoreCase("EXIT")){
                    System.out.println("A fechar a conexão com o servidor.");
                    continuar = false;
                } 


                String respostaDoServidor;
                numerobytes = reader.read(dddd);
                respostaDoServidor = new String(dddd, 0, numerobytes);

                if(respostaDoServidor.endsWith("FIM")){
                    System.out.println(respostaDoServidor.substring(0, respostaDoServidor.length() - 4));
                    continue;
                }
                while(!respostaDoServidor.endsWith("FIM") ) {
                    
                    System.out.println(respostaDoServidor );
                    
                    numerobytes = reader.read(dddd);
                    respostaDoServidor = new String(dddd, 0, numerobytes);
                }









            }
            writer.close();
            reader.close();
            s.close();
            socket.close();
         
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}