package ExerciciosCapitulo5;

import java.util.Scanner;

public class AnoBissexto {
    static boolean verificarSeEAnoBissexto(int year) {
        if( (year % 100 == 0) && (year % 400 != 0) ){
            return false;
        }
        else if( (year % 4 == 0) || (year % 400 == 0)) {
            return true;
        }
        if((year % 4 != 0) || (year % 400 != 0)){
            return false;
        } 
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ano que quer avaliar:");
        int year = scanner.nextInt();
        scanner.close();

        System.out.println(verificarSeEAnoBissexto(year));
    }
}