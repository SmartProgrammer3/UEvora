package ExerciciosCapitulo4;
// O programa escreve o resultador após a iniciaização de uma conta com incrementos etc...

public class Q2Main {
    public static void main(String[] args) {
        QuestionTwo q2;

        q2 = new QuestionTwo( );
        q2.init();
        q2.increment();
        q2.increment();
        q2.increment();
        q2.decrement();
        q2.decrement();
        q2.multiply();
        q2.multiply();
        q2.increment();        
        System.out.println(q2.getCount());
    }
}

class QuestionTwo {
    private int count;

    public void init( ) {
        count = 1;
    }

    public void increment( ) {
        count = count + 1; //count += 1;
    }
    public void decrement(){
        count -= 1; //count = count - 1; 
    }

    public void multiply(){
        count *= 2;
    }

    public int getCount( ) {
        return count;
    }
}
    