package ExerciciosCapitulo3;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class VolumeEsfera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Indique o raio da esfera: ");
        double raioEsfera = scanner.nextDouble();

        scanner.close();
        
        double volumeDaEsfera = (4.0 * Math.PI * Math.pow(raioEsfera, 3)) / 3.0; // Formula volume da esfera

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(volumeDaEsfera));
    }
}
