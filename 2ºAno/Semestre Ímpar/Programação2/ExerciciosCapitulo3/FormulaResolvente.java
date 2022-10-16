package ExerciciosCapitulo3;
import java.util.Scanner;

public class FormulaResolvente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 𝐴𝑥2 + 𝐵𝑥 + 𝑋 = 0
        System.out.println("Digite o valor do elemento A (elemento do x^2): ");
        double elementoA = scanner.nextDouble();
        System.out.println("Digite o valor do elemento B (elemento do x): ");  
        double elementoB = scanner.nextDouble(); 
        System.out.println("Digite o valor do elemento C (número real): ");  
        double elementoC = scanner.nextDouble(); 

        scanner.close();

        // x= (−𝐵 ± sqrt(𝐵^2 − 4*𝐴*C)) / 2 * A
        double dentroDaRaizQuadrada = (elementoB * elementoB) - (4 * elementoA * elementoC);

        if(elementoA != 0){ // A != 0
            if(dentroDaRaizQuadrada > 0){  // 𝐵^2 ≥ 4 * A * C
                double solucaoX1 = (-elementoB + Math.sqrt(dentroDaRaizQuadrada) ) / (2.0 * elementoA);  
                double solucaoX2 = (-elementoB - Math.sqrt(dentroDaRaizQuadrada) ) / (2.0 * elementoA);  
    
                System.out.println("As soluções reais da equação são:" + " " + solucaoX1 + " " + "e" + " " +solucaoX2);
            } 
            else if(dentroDaRaizQuadrada == 0){  // 𝐵^2 = 4 * A * C    Vai dar 0
                double solucaoUnica = (-elementoB) / (2.0 * elementoA);

                System.out.println("A solução da equação é:" + " " + solucaoUnica);
            } else{ // 𝐵^2 < 4 * A * C    
                System.out.println("Não exite raizes quadradas de números negativos.");
            }
        } else{
            System.out.println("Não é possível realizar a fórmula resolvente, divisor nulo.");
        }
    }
}
