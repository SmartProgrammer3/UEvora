// grausCelsius para grausFahrenheit
package ExerciciosCapitulo3;
import java.util.Scanner;

public class TemperaturaCF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double grausCelsius = scanner.nextDouble();
        scanner.close();

        double grausFahrenheit = (1.8 * grausCelsius) + 32;
        System.out.println(grausFahrenheit);
    }
}
