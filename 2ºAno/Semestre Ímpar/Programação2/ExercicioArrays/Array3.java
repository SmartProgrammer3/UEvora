package ExercicioArrays;
/*Suponha que as temperaturas diárias ao longo de um certo número de dias são guardadas num array de double.
 Por exemplo, se durante cinco dias as temperaturas registadas foram 23.5, 22.7, 22.3, 21.6, 22.1 o array é {23.5, 22.7, 22.3, 21.6, 22.1}
 Na classe dada, complete o método public double average(double[] temps) que recebe um array de double e calcula a temperatura média.
 Assuma que o parâmetro temp tem sempre pelo menos um elemento. */



public class Array3 {
    public double average(double[] temps) {
        double soma = 0;
        
        for(int i = 0; i < temps.length; i++){
            soma += temps[i];
        }
        return soma / temps.length;
    }
}
