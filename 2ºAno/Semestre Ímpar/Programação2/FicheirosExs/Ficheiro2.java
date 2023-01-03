package FicheirosExs;
/*
Implemente um método (de classe) que guarda num ficheiro de texto as String de um array.

Na classe dada complete o método public static boolean save(String, String[]) que tem como argumentos o nome do ficheiro e um array de String. 
Este método deve:
    Antes de guardar as String, verificar que o ficheiro indicado não existe.
    Escrever cada String numa linha.
    Depois de escrever as String e fechar o ficheiro, verificar que esse ficheiro existe.
    Devolver true se as String dadas foram gravadas no ficheiro indicado e false caso contrário.
    Pode supor que o ficheiro mytext.txt existe na pasta onde o seu programa vai correr.

Sugestão: Use o exercício anterior.
 */
public class Ficheiro2 {
    public static boolean save(String fileName, String[] strings) {
        // Verificar se o ficheiro já existe
        File file = new File(fileName);
        if (file.exists()) {
          return false;
        }
    
        // Tentar escrever as strings no ficheiro
        try (PrintWriter writer = new PrintWriter(file)) {
          for (String s : strings) {
            writer.println(s);
          }
        } catch (IOException e) {
          return false;
        }
    
        // Verificar se o ficheiro existe após a escrita
        return file.exists();
      }
}
