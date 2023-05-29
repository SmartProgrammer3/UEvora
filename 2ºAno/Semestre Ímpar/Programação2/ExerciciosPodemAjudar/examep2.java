package ExerciciosPodemAjudar;
public class examep2 {
    public class Intervalo {
        private int inicio;
        private int fim;
      
        // Construtor com dois parâmetros (os extremos do intervalo)
        public Intervalo(int inicio, int fim) {
          if (inicio > fim) {
            int temp = inicio;
            inicio = fim;
            fim = temp;
          }
          this.inicio = inicio;
          this.fim = fim;
        }
      
        // Construtor sem parâmetros (cria um intervalo de 1 a 100)
        public Intervalo() {
          this(1, 100);
        }
      
        // Devolve o cardinal do conjunto, ou seja, o número de elementos desse intervalo
        public int cardinal() {
          return fim - inicio + 1;
        }
      
        // Devolve a soma dos múltiplos do inteiro
        public int somaMultiplosDe(int n) {
          int soma = 0;
          for (int i = inicio; i <= fim; i++) {
            if (i % n == 0) {
              soma += i;
            }
          }
          return soma;
        }
      }
      
}
