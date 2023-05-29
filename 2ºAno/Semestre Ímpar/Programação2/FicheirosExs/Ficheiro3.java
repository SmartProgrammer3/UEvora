package FicheirosExs;

import java.io.File;
import java.util.Scanner;

/*
 Implemente um método (de classe) que lê as palavras do ficheiro de texto criado pelo Exercício 12 06.

Na classe dada complete o método public static String scan(String) que tem como argumento o nome do ficheiro. Este método deve:

Devolver uma String com a palavra mais curta (se existirem várias, a primeira), a mais comprida (idem) e o comprimento médio das palavras, 
no formato "%s|%s|%.3f".
Verificar que o ficheiro indicado é «adequado». Deve devolver ERRO se:

o ficheiro não existe.

o ficheiro existe, mas está vazio.
ocorre algum erro durante a leitura do ficheiro.
Pode supor que o ficheiro mytext.txt é «adequado».

Sugestão: Use o exercício anterior e a classe ArrayList para guardar as palavra lidas do ficheiro.
 */
public class Ficheiro3 {
    public static String scan(String Filename){
        int tamanhoMinimo = 100, tamanhoMaximo = 0, i = 0;
        float media = 0;

        String leitura = "";
        String min = "";
        String max = "";


        try{
            File file = new File(Filename);
            
            if(!(file.exists())){
                throw new Exception("ERRO");
            }
            
            Scanner s = new Scanner(file);
            
            if(!(s.hasNext())){
                throw new Exception("ERRO");
            }

            do{
                leitura = s.nextLine(); 
                
                if(leitura.equals(null)){
                    continue;
                }

                media += leitura.length();

                if(leitura.length() < tamanhoMinimo){
                    min = leitura;
                    tamanhoMinimo = min.length();
                }
                if(leitura.length() > tamanhoMaximo){
                    max = leitura;
                    tamanhoMaximo = max.length();
                }
                i++;
            } while(s.hasNext());
                media /= i;
                String res = min + "|" + max + "|" + String.format("%.3f",media);
                return res;
        } catch(Exception e){
            return e.getMessage();
        }
    }

}
