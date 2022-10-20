package PrimeiroMiniTestePratico;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int peso = scanner.nextInt();
        scanner.close();

        float pesaEmMercurio = peso * 0.4f;
        System.out.println(pesaEmMercurio);
    }
}
