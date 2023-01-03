package ExerciciosCapitulo7;
import java.util.*;


enum Meal {
    BREAKFAST,
    LUNCH,
    DINNER
}

public class MealCardEnum {
    private int points;
    static final int DEFAULT_POINTS = 100;
    
    public void MealCard(){
        this.points = DEFAULT_POINTS;
    }
    public void MealCard(int x){
        setPoints(x);
    }
    
    public int getPoints( ){
        return this.points;
    }
    
    public void setPoints(int x){
        this.points = x;
    }
    
    public static int cost(Meal meal) {
      if (meal == Meal.BREAKFAST) {
        return 50;
      } else if (meal == Meal.LUNCH) {
        return 75;
      } else if (meal == Meal.DINNER) {
        return 100;
      } 
      return 0;
    }
    
    public boolean buy(int custo){
        if(this.points > custo){
            this.points -= custo;
            return true;
        }
        return false;
    }
    
    public boolean buy(Meal meal){
        return buy(cost(meal));
    }
 
}