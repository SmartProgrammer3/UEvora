package ExerciciosCapitulo13;

public class Cat extends Pets{
    private String coatColor;
    public Cat(String name, String color) {
        super('c', name);
        this.coatColor = color;
    }
    public void setCoatColor(String color){
        this.coatColor = color;
    }
    public String getCoatColor(){
        return this.coatColor;
    }
}
