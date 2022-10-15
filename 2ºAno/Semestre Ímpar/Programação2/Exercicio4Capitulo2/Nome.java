package Exercicio4Capitulo2;
import java.util.Scanner;

public class Nome {
    public static void main(String[ ] args) {
        Scanner scanner = new Scanner(System.in);
        String nomeProprio = scanner.nextLine();
        String nomeMeio = scanner.nextLine();
        String apelido = scanner.nextLine();
        char meio;

        meio = nomeMeio.charAt(0);
        System.out.println(nomeProprio + " " + meio + ". " + apelido);
    }
}
