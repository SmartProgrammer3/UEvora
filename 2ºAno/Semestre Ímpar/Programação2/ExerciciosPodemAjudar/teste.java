package ExerciciosPodemAjudar;
import java.util.*;

public class teste {
    static List<Integer>  positionsCharIgnoreCase(String s, char C){
        List<Integer> lista = new ArrayList<Integer>();
        s = s.toLowerCase();

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == C){
                lista.add(i);
            }
        }
        return lista;
    }

    public static void main(String[] args){
        System.out.println(positionsCharIgnoreCase("HelHloh", 'h').toString());
    }
}
