package ExerciciosCapitulo4;

// Escreva um programa para mostrar o peso recomendado (em Kg), dada a idade e altura (em cm). A fórmula para calcular o peso recomendado é
// peso = (altura - 100 + idade/10) * 0.90
// Defina uma classe Altura e inclua um método double pesoRecomendado(int idade) para se obter o peso recomendado para uma determinada altura.
import java.util.Locale;
import java.util.Scanner;
import java.text.NumberFormat;

class Altura {
    double alturaa;
    double pesorecom;

    public Altura(double x){
        alturaa = x;
    }

    double pesoRecomendado(double idade){
        pesorecom = (alturaa - 100 + idade/10) * 0.90;
        return pesorecom;
    }
}

public class Ex_04_12 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Indique a sua altura:");
        double altura = s.nextDouble();
        System.out.println("Indique a sua idade:");
        int idade = s.nextInt();
        s.close();
        

        Altura a = new Altura(altura);
        double p = a.pesoRecomendado(idade);


        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        System.out.println("O seu peso recomendado é:" + nf.format(p));
    }
}
