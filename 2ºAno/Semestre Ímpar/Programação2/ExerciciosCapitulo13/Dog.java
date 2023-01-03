package ExerciciosCapitulo13;


public class Dog extends Pets {
    public int peso;

    public Dog(char newEspecie, String newNome, int newPeso){
        super(newEspecie, newNome);
        setWeight(newPeso);
    }

    public int getWeight(){
        return this.peso;
    }

    public void setWeight(int newPeso){
        this.peso = newPeso;
    }

    public String toString(){
        return especie + " " + nome + " " + peso; // ou então como o método toString já está definido, podemos fazer return super.toString() + " " + peso;
    }

}
