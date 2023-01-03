package ExercicioArrays;
/*
O cálculo do desvio padrão é
A variável x é a média dos N números reais.

Na classe dada, complete o método public double stdev(double[] xs) que recebe um array de double e calcula o desvio padrão.

Assuma que o parâmetro xs tem sempre pelo menos um elemento.
 */
public class Array2 {
    public double stdev(double[] xs) {
        double soma = 0;
        double media;
        double superSoma = 0;
        for(int i = 0; i < xs.length; i++){
            soma += xs[i];
        }
        media = soma / xs.length;
        
        for(int i = 0; i < xs.length; i++){
            superSoma +=  Math.pow(xs[i] - media, 2) / xs.length;
        }
    
        return Math.sqrt(superSoma);
    }
}
