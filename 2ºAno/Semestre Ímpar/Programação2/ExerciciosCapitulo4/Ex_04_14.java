package ExerciciosCapitulo4;

import java.util.Locale;
import java.util.Scanner;
import java.text.NumberFormat;

class Converter {
    double gravidade;
    double pesoNoAstro;

    public Converter(double x){
        gravidade = x;
    }

    double convert(double x){
        pesoNoAstro = gravidade * x;
        return  pesoNoAstro;
    }
    

}

public class Ex_04_14 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Indique a gravidade do astro relativamente à Terra:");
        double gravidade = s.nextDouble();
        System.out.println("Indique o peso na Terra (Em Kg):");
        double PesoNaTerra = s.nextDouble();
        s.close();


        Converter astro = new Converter(gravidade);
        double pesoNoAstroo = astro.convert(PesoNaTerra);


        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setMinimumFractionDigits(4);
        nf.setMaximumFractionDigits(4);
        System.out.println(nf.format(pesoNoAstroo));
    }
}
