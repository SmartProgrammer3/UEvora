package ExercicioStrings;
/*
 Na classe dada, complete o método boolean isPalindrome(String) que determina se uma palavra dada é um palíndrome (palavra, 
 número ou frase que é igual quando lida de trás para diante). Por exemplo, «osso», «Atai a gaiola, saloia gaiata!» ou «Anotaram a data da maratona». 
 Deve ignorar os símbolos de pontuação, espaços e a diferença entre maiúsculas e minúsculas (i.e. "a" = "A").
 */
public class String3 {
    String newWord;
    
    public boolean isPalindrome(String word) {
        newWord = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        for(int i = 0; i <newWord.length(); i++){
            if(newWord.charAt(i) != newWord.charAt(newWord.length() - 1 -i)){
                return false;
            }
        }
        return true;
    }
}
