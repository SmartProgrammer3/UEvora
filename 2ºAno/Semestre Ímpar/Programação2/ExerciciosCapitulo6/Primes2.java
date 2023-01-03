package ExerciciosCapitulo6;

import java.util.Date;

public class Primes2 {
  private static long startTime;

  public static boolean isPrime(int x) {
    if (x <= 1) return false;
    for (int i = 2; i <= x/2; i++) {
      if (x % i == 0) return false;
    }
    return true;
  }

  public static int countPrimes(int n) {
    int count = 0;
    for (int i = 2; i <= n; i++) {
      if (isPrime(i)) count++;
    }
    return count;
  }

  public static void resetTimer() {
    startTime = new Date().getTime();
  }

  public static long getElapsed() {
    return new Date().getTime() - startTime;
  }
}