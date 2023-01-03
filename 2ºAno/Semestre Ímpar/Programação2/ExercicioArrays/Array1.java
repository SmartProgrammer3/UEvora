package ExercicioArrays;

/*
Suponha que as temperaturas diárias ao longo de um certo número de dias são guardadas num array de double.
 Por exemplo, se durante cinco dias as temperaturas registadas foram 23.5, 22.7, 22.3, 21.6, 22.1 o array é {23.5, 22.7, 22.3, 21.6, 22.1}
 Na classe dada, complete o método public double amplitude(double[] temps) que recebe um array de double e calcula a diferença de temperatura 
 entre o dia mais quente e o dia mais frio.
 Assuma que o parâmetro temp tem sempre pelo menos um elemento.
 */
public class Array1 {
    public double amplitude(double[] temps) {
        double maximo = temps[0];
        double minimo = temps[0];
        
        for(int i = 1; i < temps.length ; i++){
            if(temps[i] > maximo){
                maximo = temps[i];
            }
            if(temps[i] < minimo){
                minimo = temps[i];
            }
        }
        return maximo - minimo;  
    }
}
