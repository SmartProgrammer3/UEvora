package ExerciciosCapitulo7;

public class MealCard {
    private int points;
    static final int DEFAULT_POINTS = 100;
    
    public MealCard(){
        this.points = DEFAULT_POINTS;
    }
    public MealCard(int x){
        setPoints(x);
    }
    
    public int getPoints( ){
        return this.points;
    }
    
    public void setPoints(int x){
        this.points = x;
    }
    
}