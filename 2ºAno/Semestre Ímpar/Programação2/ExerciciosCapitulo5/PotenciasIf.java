package ExerciciosCapitulo5;
import java.util.Scanner;
/* Com switch case:

public class PotenciasIf {
    static void verificarPotencias(int potencia) {

       switch(potencia){
            case 6: System.out.print ("O termo é: Million" );
                break;
            case 9: System.out.print ("O termo é: Billion" );
                break;
            case 12: System.out.print ("O termo é: Trillion" );
                break;
            case 15: System.out.print ("O termo é: Quadrillion" );
                break;
            case 18: System.out.print ("O termo é: Quintillion" );
                break;
            case 21: System.out.print ("O termo é: Sextillion" );
                break;
            case 30: System.out.print ("O termo é: Nonillion" );
                break;
            case 100: System.out.print ("O termo é: Googol" );
                break;
            default: System.out.print ("Coloque o input com casos: 6,9,12,15,18,21,30,100" );
                break;
       }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a potência de 10 que pretende saber o respetivo termo:");
        int potencia = scanner.nextInt();
        scanner.close();

        verificarPotencias(potencia);
    }
}

*/

public class PotenciasIf {
    static void verificarPotencias(int potencia) {
        if (potencia >= 100)
            System.out.print ("O termo é: Googol" );
        else if (potencia >= 30)
            System.out.print ("O termo é: Nonillion" );
        else if (potencia >= 21)
            System.out.print ("O termo é: Sextillion" );
        else if (potencia >= 18)
            System.out.print ("O termo é: Quintillion" );
        else if (potencia >= 15)
            System.out.print ("O termo é: Quadrillion" );
        else if (potencia >= 12)
            System.out.print ("O termo é: Trillion" );
        else if (potencia >= 9)
            System.out.print ("O termo é: Billion" );
        else if (potencia >= 6)
            System.out.print ("O termo é: Million" );
        else
            System.out.println("no match");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a potência de 10 que pretende saber o respetivo termo:");
        int potencia = scanner.nextInt();
        scanner.close();

        verificarPotencias(potencia);
    }
}
