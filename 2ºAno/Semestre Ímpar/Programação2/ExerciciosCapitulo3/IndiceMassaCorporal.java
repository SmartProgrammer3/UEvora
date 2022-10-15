package ExerciciosCapitulo3;
import java.util.Scanner;

public class IndiceMassaCorporal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique o peso (Em kg):");
        int peso = scanner.nextInt();
        System.out.println("Indique a altura (Em cm):");
        int altura = scanner.nextInt();
        scanner.close();

        double divisor = Math.pow(altura / 100.0, 2); //Divisor da formula, altura/100 ao quadrado
        System.out.println(divisor);

        double BMI = peso / divisor;

        System.out.println(BMI);

        if(BMI > 20 && BMI < 25){
            System.out.println("O seu BMI é considerado normal");            
        } else {
            System.out.println("O seu BMI não é considerado normal");                
        }
    }
}
