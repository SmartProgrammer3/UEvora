package ExerciciosCapitulo13;

public class Pets {
    protected String nome;
    protected char especie;

    protected Pets(char newEspecie, String newNome){
        setEspecie(newEspecie);
        setNome(newNome);
    }

    public String getNome(){
        return nome;
    }

    public char getEspecie(){
        return especie;
    }

    public void setNome(String newNome){
        nome = newNome;
    }

    public void setEspecie(char newEspecie){
        especie = newEspecie;
    }

    public String toString() { //Só dar print 
        return especie + " " + nome;
    }
}

