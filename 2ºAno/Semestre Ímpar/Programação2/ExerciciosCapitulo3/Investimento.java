package ExerciciosCapitulo3;
import java.util.Scanner;

public class Investimento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique a quantia a investir:");
        double quantia = scanner.nextInt();
        System.out.println("Indique a taxa composta");
        double juros = scanner.nextInt();
        System.out.println("Indique a duração em anos após o investimento inicial:");
        double anos = scanner.nextInt();

        scanner.close();
        
        double MontanteGanho = quantia * Math.pow((1 + juros) / 100, anos);
        
        System.out.println(MontanteGanho);     
    }
}