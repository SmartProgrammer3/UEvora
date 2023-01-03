package ExercicioStrings;

/*
    Na classe dada, complete o método int countUpper(String) que conta o número de letras maiúsculas na String dada. Use o método isUpperCase da classe Character, que devolve true se o parâmetro de tipo char passado for uma letra maiúscula.
    Vai precisar de explorar, autonomamente, a classe Character do package java.lang.
 */
public class String2 {
    public int conta = 0;
    
    public int countUpper(String text) {
        for(int i = 0; i <= text.length() - 1; i++){
            if(Character.isUpperCase(text.charAt(i))){
                conta++;
            }
        }
        return conta;
    }
}
