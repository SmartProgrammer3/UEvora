package ExerciciosCapitulo6;


public class Table {
    public static void tabulate(int start, int end, int lineLength) {
      for (int i = start; i <= end; i++) {
          System.out.print(String.format("%2d ", i));
        if (i % lineLength == lineLength - 1) {
          System.out.println();
        }
      }
    }
  }