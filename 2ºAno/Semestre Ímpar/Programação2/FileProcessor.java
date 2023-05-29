import java.io.*;
import java.util.*;

public class FileProcessor {
  public static String scan(String s){
      ArrayList<String> words = new ArrayList<String>();
      try{
          Scanner scanner = new Scanner(new File(s));
          while(scanner.hasNext()){
              words.add(scanner.next());
          }
          scanner.close();
      }
      catch(FileNotFoundException e){
          return "ERRO";
      }
      if(words.isEmpty()){
          return "ERRO";
      }
      String shorty = words.get(0);
      String longy = words.get(0);
      double avg = 0;
      for(String word:words){
          if(word.length() > longy.length()){
              longy = word;
          }
          else if(word.length() < shorty.length()){
              shorty = word;
          }
          avg += word.length();
      }
      avg /= words.size();
      return String.format("%s|%s|%.3f", shorty, longy, avg);
  }
}
