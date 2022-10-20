package ExerciciosCapitulo4;

import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;


class Temperature{
    double celsius;
    double fahrenheit;

    public Temperature(double x){
        celsius = x; // O valor inicial de uma instância está em graus celsius
    }
    
    void setCelsius(double x){
    }

    void setFahrenheit(double x){
        fahrenheit = x;
    }
    
    double getCelsius(){
        double newCelsius;
        newCelsius = (5.0 / 9.0) * (fahrenheit - 32);
        return newCelsius; // Retorna output celsius 
    }
    
    double getFahrenheit(){
        double newFahrenheit;
        newFahrenheit =  (1.8 * celsius) + 32;
        return newFahrenheit; // Retorna output fahrenheit 
    }
}



public class Convert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("Digite a temperatura em graus Celsius:");
        double celsius_in = scanner.nextDouble();
        System.out.println("Digite a temperatura em graus Fahrenheit:");
        double fahrenheit_in = scanner.nextDouble();
        scanner.close();
        

        Temperature temperature = new Temperature(celsius_in); // A temperatura é celsius x = celsius
        double fahrenheit_out = temperature.getFahrenheit(); // A partir desses celsius, usamos o método getFahrenheit e tiramos a conversão par a graus fahrenheit (o que vai aparecer no output)
        temperature.setFahrenheit(fahrenheit_in); // Usamos o método set fahrenheit para assinalar à temperatura o valor dos graus fahrenheit do input
        double celsius_out = temperature.getCelsius(); // Usamos o metodo getCelsius para converter o input fahrenheit e criar em celsius o seu output


        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(fahrenheit_out));
        System.out.println(nf.format(celsius_out));           
  } 
}

