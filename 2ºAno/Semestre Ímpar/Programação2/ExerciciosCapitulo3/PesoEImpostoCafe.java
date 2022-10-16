package ExerciciosCapitulo3;
import java.util.Scanner;

public class PesoEImpostoCafe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Indique o número de sacos vendidos:");
        double numeroDeSacosVendidos = scanner.nextInt();
        System.out.println("Indique o peso unitário de um saco de café (em kilogramas)");
        double pesoUnitarioDeUmSaco = scanner.nextInt();

        scanner.close();

        double precoPorKg = 5.99;
        double imposto = 0.0725;
        
        double precoSemImposto = pesoUnitarioDeUmSaco * precoPorKg * numeroDeSacosVendidos;
        double precoFinal = precoSemImposto + (precoSemImposto * imposto); // Preço com imposto, é acresentar o imposto ao preço anterior


        System.out.println("Número de sacos vendidos:" + " " + numeroDeSacosVendidos);
        System.out.println("Peso por saco:" + " " + pesoUnitarioDeUmSaco);
        System.out.println("Preço por Kg:" + " " + precoPorKg);
        System.out.println("Imposto" + " " + imposto);
        System.out.println("Preço Final" + " " + precoFinal);
    }
}
