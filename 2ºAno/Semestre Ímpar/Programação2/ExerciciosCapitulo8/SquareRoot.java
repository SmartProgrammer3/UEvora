package ExerciciosCapitulo8;

class SquareRoot {
    public double numero;
    
    
    public SquareRoot(double n){
        if(n < 0){
            throw new IllegalArgumentException(n + " < 0");
        }
        numero = n;
    }
    
    public double compute(double epsilon) throws IllegalArgumentException{
        if(epsilon <= 0){
            throw new IllegalArgumentException(epsilon + "<= 0");
        }
        double t = numero;
        while (Math.abs(t - numero/t) > epsilon*t) {
            t = (numero / t + t) / 2.0;     
        }
               
        return t;
    }
    
    
    
}