package ExerciciosCapitulo4;
import java.util.Locale;
import java.util.Scanner;
import java.text.NumberFormat;


class Circle {
    // Data member
    double areaCirculo;
    double perimetroCirculo;


    public Circle(double r){ //Construtor (Initialzes the data member)
        setRadius(r);
    }

    public double getArea(){ // Retorna a área do respetivo circulo

        return areaCirculo;
    }
    
    public double getPerimeter(){ // Retorna o perímetro do respetivo circulo

        return perimetroCirculo;
    }
    
    public void setRadius(double r) { // Atribui os valores 
        areaCirculo = Math.PI * r * r; // Formula da area
        perimetroCirculo  = Math.PI * r *2; // Formula do perimetro
    } 

}

public class Ex_04_09 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o raio do círculo maior:");
        double r1 = s.nextDouble();
        System.out.println("Digite o raio do círculo menor:");
        double r2 = s.nextDouble();
        s.close();


        Circle c1 = new Circle(r1); // circle(r1) é o contrutor da classe pq inicializa uma instancia
        Circle c2 = new Circle(r2);
        c2.setRadius(r2);

        double area = c1.getArea() - c2.getArea();  // A área é a área do círculo maior menos a do círculo menor.
        double p1 = c1.getPerimeter();
        double p2 = c2.getPerimeter();

        NumberFormat nf = NumberFormat.getInstance(Locale.US); // este format é para vir com . nos números
        nf.setMinimumFractionDigits(2); // 2 casas decimais.
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(area) + " " + nf.format(p1) + " " + nf.format(p2));
    }
}
