package FicheirosExs;
import java.io.File;
import java.util.*;
/*
Implemente um método (estático) boolean que testa se um ficheiro existe.
Na classe dada complete o método public static boolean exists(String) que tem como argumento o nome do ficheiro e devolve true ou false 
conforme o ficheiro indicado existe ou não.
 */
public class Ficheiro1 {
    public static class FileProcessor {
  
        public static boolean exists(String fileName) {
            File file = new File(fileName);
            return file.exists();
        }
    
    }
}
