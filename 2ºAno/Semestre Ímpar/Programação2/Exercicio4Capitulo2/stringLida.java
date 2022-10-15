package Exercicio4Capitulo2;
import java.util.Scanner;

public class stringLida {
    public static void main(String[ ] args) {
        int numeroCaracteres;
        Scanner scanner = new Scanner(System.in);
        String stringAvaliada = scanner.nextLine();
        scanner.close();
        char caracterInicial;
        char caracterFinal;

        numeroCaracteres = stringAvaliada.length();
        caracterInicial = stringAvaliada.charAt(0);
        caracterFinal = stringAvaliada.charAt(numeroCaracteres-1);

        System.out.println(numeroCaracteres);
        System.out.println(caracterInicial);
        System.out.println(caracterFinal);
    }
}
