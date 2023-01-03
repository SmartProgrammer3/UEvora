package SegundaFrequencia2022;
import java.util.*;

public class StringBuilder {
/*
 * Defina o método de classe (estatico) StringBuilder foo(String s1,String s2),
 * que devolve a StringBuilder constituída pelo chars da String do primeiro 
 * argumento que também existem no segundo argumento(pela mesma ordem.
*/
    public static StringBuilder foo(String s1, String s2){    
        
        StringBuilder strConstruida = new StringBuilder();
   w

        for(int i = 0; i < s1.length(); i++){
            char caracterAvaliado =  s1.charAt(i);

            for(int j = 0; j < s2.length(); j++){
                if(caracterAvaliado == s2.charAt(j)){
                    strConstruida = strConstruida + caracterAvaliado;
                }
            }
        }
        return strConstruida;
    }
}
