package Exercicio4Capitulo2;
import javax.swing.*;
import java.util.Scanner;


public class frameUtilizador {
    public static void main(String[ ] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a largura:");
        int largura = scanner.nextInt();
        System.out.println("Digite a altura:");
        int altura = scanner.nextInt();
        System.out.println("Digite o titulo da janela:");
        String titulo = scanner.next();
        scanner.close();

        JFrame myWindow;
        myWindow = new JFrame( );
        myWindow.setSize(largura, altura);
        myWindow.setTitle(titulo);
        myWindow.setVisible(true);
    }
}
