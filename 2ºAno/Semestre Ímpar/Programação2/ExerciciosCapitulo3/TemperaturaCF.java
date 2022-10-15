// grausCelsius para grausFahrenheit
package ExerciciosCapitulo3;
import java.util.Scanner;

public class TemperaturaCF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int grausCelsius = scanner.nextInt();
        double grausFahrenheit = (1.8 * grausCelsius) + 32;
        
        scanner.close();

        System.out.println(grausFahrenheit);
    }
}
