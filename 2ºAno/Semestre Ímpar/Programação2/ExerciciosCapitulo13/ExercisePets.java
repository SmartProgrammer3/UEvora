package ExerciciosCapitulo13;

import java.util.*;
public class ExercisePets {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Pets> animais = new ArrayList<Pets>();
        String line;
        String cor_pelo;
        int peso;
        try{
            while(true){
                line = s.nextLine();
                if(format(line)){
                    System.out.println("Ok");
                    if(line.charAt(0) == 'd'){
                        peso = s.nextInt();
                        animais.add(new Dog(line.substring(2), peso));
                    }
                    else if(line.charAt(0) == 'c'){
                        cor_pelo = s.nextLine();
                        animais.add(new Cat(line.substring(2), cor_pelo));
                    }
                }
                else{
                    System.out.println("ERR");
                }
            }
        }
        catch(NoSuchElementException e){
            s.close();
            for(Pets p: animais){
                System.out.println(p);
            }
        }
        finally{

        }

    }
    public static boolean format(String line){
        if(line.length()>2 && (line.charAt(0) == 'd' || line.charAt(0) == 'c') && line.charAt(1) == ' '){
            return true;
        }
        return false;
    }
}