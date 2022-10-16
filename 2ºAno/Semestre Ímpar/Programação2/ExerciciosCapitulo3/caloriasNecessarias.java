package ExerciciosCapitulo3;
import java.util.Scanner;

public class caloriasNecessarias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique o seu peso (Em kg):");
        int peso = scanner.nextInt();

        scanner.close();

        double caloriasPrecisa = 8.6182556 * peso;

        System.out.println("Voce necessita de consumir estas calorias: " + caloriasPrecisa);
    }
}
