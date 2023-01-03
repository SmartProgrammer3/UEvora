package ExerciciosCapitulo13;


public abstract class Pets{
    protected String name;
    protected char species;

    public Pets(char s, String n){
        this.species = s;
        this.name = n;
    }
    public String getName() {
        return name;
    }


    public char getSpecies() {
        return species;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setSpecies(char species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return  species + " " + name;
    }
}