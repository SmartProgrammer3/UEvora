import java.util.*;

public class teste2 {
    
    public static int somaPares(int m, int n) throws IllegalArgumentException{
        int soma = 0;

  
        if(m > n){
            throw new IllegalArgumentException("First argument greater than second!");
        }

        for(int i = m; i < n; i++){
            if(i%2 == 0){
                soma += i;
            }
        }


        return soma;
    }
    
    public static void main(String[] args){
        
        try{
            
            System.out.println(somaPares(15,10));
            System.out.println(somaPares(10,15));
            
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        
    }
}
