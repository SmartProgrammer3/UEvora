package ExerciciosCapitulo13;



public class Dog extends Pets {
    private int weight;

    public Dog(String name, int w){
        super('d', name);
        this.weight = w;
    }
    public int getWeight(){
        return this.weight;
    }
    public void setWeight(int w){
        this.weight = w;
    }
}
