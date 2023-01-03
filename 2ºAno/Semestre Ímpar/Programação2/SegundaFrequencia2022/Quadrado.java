package SegundaFrequencia2022;

public class Quadrado {
    /*
     * Defina a classe quadrado assim como:
     * * Uma constante de classe(NLados) do tipo inteiro que especifica o número
     * de lados de um quadrado. Esta constante deve ser acessivel a partir de outras classes.
     * * dois "construtores"
     *  1. Com um parametro do tipo double para especificar a dimensão do lado do quadrado;
     *  2. Sem parâmetros, cria um quadrado de lado igual a 1.
     * * Um método de instância double perimetro() que como o nome indica devolve
     *  o perimetro do quadrado (Nlados vezes a dimensão do lado).
     */
    private static final int  NLADOS = 4;
    private double cumpLados;

    public Quadrado(){
        cumpLados = 1;
    }

    public Quadrado(double newcumpLados){
        cumpLados = newcumpLados;
    }

    public double perimetro(){
        return NLADOS * cumpLados;
    }
}
