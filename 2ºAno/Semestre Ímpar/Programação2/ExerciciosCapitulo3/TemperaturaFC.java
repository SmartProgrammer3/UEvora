// grausFahrenheit para grausCelsius
package ExerciciosCapitulo3;
import java.util.Scanner;

public class TemperaturaFC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int grausFahrenheit = scanner.nextInt();
        double grausCelsius = (5.0 / 9.0) * (grausFahrenheit - 32);
        
        scanner.close();

        System.out.println(grausCelsius);
    }
}