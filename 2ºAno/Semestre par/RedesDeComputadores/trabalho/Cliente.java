    import java.io.*;
    import java.net.*;
    import java.util.Scanner;

    public class Cliente {
        final static String path = "C:/Users/gonca/OneDrive/Ambiente de Trabalho/Uevora-CloneGithub/UEvora/2ºAno/Semestre Par/RedesDeComputadores/trabalho";
        //final static String path = "";

        public static void main(String[] args) {
            try {
                Socket socket = new Socket("localhost", 5555);
                System.out.println("Conectado\n");

                InputStream reader = socket.getInputStream();
                OutputStream writer = socket.getOutputStream();
                Scanner s = new Scanner(System.in);
                byte[] dddd = new byte[1024];
                String inputUtilizador;
                int numerobytes;
                int bytesLidos = 0;

                numerobytes = reader.read(dddd);
                String resposta = new String(dddd, 0, numerobytes);
                System.out.println(resposta);

                boolean continuar = true;

                while (continuar) {
                    inputUtilizador = s.nextLine();
                
                    dddd = inputUtilizador.getBytes();
                  
                    writer.write(dddd, 0, dddd.length);
                   

                    if (inputUtilizador.equalsIgnoreCase("EXIT")) {
                        System.out.println("A fechar a conexão com o servidor.");
                        continuar = false;
                    } else if (inputUtilizador.startsWith("PUTFILE ")) {
                        String[] partes = inputUtilizador.split(" ");
                        String nomeFicheiro = partes[1]; //nome ficheiro depois do "PUTFILE"

                        File ficheiro = new File(path +  nomeFicheiro);
                        int tamanhoFicheiro = Integer.parseInt(partes[2]); // Bytes ficheiro

                        if(!ficheiro.exists()){
                            System.out.println("Não existe esse ficheiro." );
                        
                        }else{
                            byte[] conteudoDoFicheiro = new byte[tamanhoFicheiro];
                            InputStream dd = new FileInputStream(ficheiro);

                            
                            int bytes = dd.read(conteudoDoFicheiro);

                            while(bytes >0){
                                writer.write(conteudoDoFicheiro, 0, bytes);
                            }  
                            dd.close();
                        }
                    } else if (inputUtilizador.startsWith("GETFILE ")) {             
                        numerobytes = reader.read(dddd);

                        String str = new String(dddd, 0, numerobytes);
                        
                        System.out.println(str);
                        System.out.flush();
                        
                        dddd = "READY".getBytes();
                        writer.write(dddd, 0, dddd.length);

                        if(str.startsWith("FILE")){ // Se começar com o FILE
                            
                            String[] partes= str.split(" ");
                            String nomeFicheiro = partes[2].substring(1);
                            int numeroBytes = Integer.parseInt((partes[3]).trim());

                            
                            File ficheiro = new File( path + nomeFicheiro);
                            FileOutputStream fos = new FileOutputStream(ficheiro);

                            
                            byte[] conteudo = new byte[numeroBytes]; // Ler e guardar o conteúdo do ficheiro recebido
                            
                            
                    
                            bytesLidos = reader.read(conteudo);
                            fos.write(conteudo, 0, bytesLidos);
                            
                            
                            System.out.println(conteudo.toString());
                            fos.close();
                        }else{
                            System.out.println(" ERRO");
                        }
                    }

                    StringBuilder respostaDoServidor = new StringBuilder();
                    numerobytes = reader.read(dddd);
                    respostaDoServidor.append(new String(dddd, 0, numerobytes));

                    while (!respostaDoServidor.toString().endsWith("FIM")) {
                        numerobytes = reader.read(dddd);
                        respostaDoServidor.append(new String(dddd, 0, numerobytes));
                    }

                    System.out.println(respostaDoServidor.toString().substring(0, respostaDoServidor.length() - 4));


                }
                writer.close();
                reader.close();
                s.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
