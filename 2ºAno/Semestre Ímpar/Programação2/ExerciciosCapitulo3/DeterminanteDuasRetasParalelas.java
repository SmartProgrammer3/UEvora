package ExerciciosCapitulo3;
import java.util.Scanner;

public class DeterminanteDuasRetasParalelas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 𝐴𝑥2 + 𝐵𝑥 + 𝑋 = 0
        System.out.println("Digite o valor do elemento A1 (elemento do x da reta 1): ");
        double elementoA1 = scanner.nextDouble();
        System.out.println("Digite o valor do elemento B1 (elemento do y da reta 1): ");  
        double elementoB1 = scanner.nextDouble(); 
        System.out.println("Digite o valor do elemento A2 (elemento do x da reta 2): ");  
        double elementoA2 = scanner.nextDouble(); 
        System.out.println("Digite o valor do elemento B2 (elemento do y da reta 2): ");  
        double elementoB2 = scanner.nextDouble(); 

        scanner.close();

        double determinante = (elementoA1 * elementoB2) - (elementoB1 * elementoA2);

        System.out.println(determinante);
    }
}
