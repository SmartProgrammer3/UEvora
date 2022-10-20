package PrimeiroMiniTestePratico;
import java.util.Scanner;

public class ConversaoUnidades {
    public static double convert(double x){
        return 1.6387 * x;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        scanner.close();

        double y = convert(x);
        System.out.println(y);
    }
}
