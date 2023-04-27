import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Point {
        private int linha;
        private int coluna;
        private int movimentos;
    
        public Point(int linha, int coluna, int movimentos) {
            this.linha = linha;
            this.coluna = coluna;
            this.movimentos = movimentos;
        }
    
        public int getRow() {
            return this.linha;
        }
    
        public int getCol() {
            return this.coluna;
        }
    
        public int getMoves() {
            return this.movimentos;
        }
    
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Indique o número de linhas / colunas / casos a testar: ");
        String[] line = input.readLine().split(" ");
        
        int numeroDeLinhas = Integer.parseInt(line[0]);
        int numeroDeColunas = Integer.parseInt(line[1]);
        int numerorCasosATestar = Integer.parseInt(line[2]);

        char mapa[][] = new char[numeroDeLinhas][numeroDeColunas]; // Mapa com o tamanho das linhas e colunas dadas pelo utilizador
        
        
        // Criar o mapa com os valores dados pelo utilizador
        for (int i = 0; i < numeroDeLinhas; i++) {
            String linhaMapa = input.readLine();                      // . (dot) -> Empty location
            for (int j = 0; j < numeroDeColunas; j++) {               // letra "O" -> Obstáculo
                mapa[i][j] = linhaMapa.charAt(j);                     // letra "H" -> Buraco (Objetivo onde queremos chegar)
            }
        }

        // Avaliar os testes propostos
        for (int caso = 0; caso < numerorCasosATestar; caso++){
            String[] linhaTeste = input.readLine().split(" ");

            // Posição inicial da esfera em cada teste
            int coordLinha = Integer.parseInt(linhaTeste[0]); 
            int coordColuna = Integer.parseInt(linhaTeste[1]);        
            
            int result = bfs(mapa, coordLinha - 1, coordColuna - 1);

            System.out.println("Teste " + (caso + 1));
            
            if (result == -1) {
                System.out.println("Stuck");
            } else {
                System.out.println(result);
            }
            System.out.println();
        }
    }

    private static int bfs(char[][] mapa, int linhaInicio, int colunaInicio) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(linhaInicio, colunaInicio, 0));

        int nLinhas = mapa.length;
        int nColunas = mapa[0].length;

        boolean[][] visited = new boolean[nLinhas][nColunas]; // Matriz para avaliar as posições que já foram visitadas/avaliadas do mapa
        visited[linhaInicio][colunaInicio] = true; // A posição inicial é automaticamente avaliada
        int result = -1; 
    
        while (!queue.isEmpty()) {
            Point pontoEmQueEsta = queue.poll(); //Remove o ponto em que está na queue
            int linhaPos = pontoEmQueEsta.getRow();
            int colunaPos = pontoEmQueEsta.getCol();

            // Se a bola chegou ao buraco
            if (mapa[linhaPos][colunaPos] == 'H') {
                result = pontoEmQueEsta.getMoves();
                break;
            }

            // Tenta mover a bola para cima - Avalia em cima da posição
            int linhaCima = linhaPos - 1;
            while (linhaCima >= 0 && mapa[linhaCima][colunaPos] != 'O') {
                if (!visited[linhaCima][colunaPos]) {
                    Point pontoEmCima = new Point(linhaCima, colunaPos, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoEmCima);
                    visited[linhaCima][colunaPos] = true;
                }
                linhaCima--;
            }
    
            // Tenta mover a bola para baixo - Avalia em baixo da posição
            int linhaBaixo = linhaPos + 1;
            while (linhaBaixo < nLinhas && mapa[linhaBaixo][colunaPos] != 'O') {
                if (!visited[linhaBaixo][colunaPos]) {
                    Point pontoEmBaixo = new Point(linhaBaixo, colunaPos, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoEmBaixo);
                    visited[linhaBaixo][colunaPos] = true;
                }
                linhaBaixo++;
            }
    
            // Tenta mover a bola para a esquerda - Avalia a esquerda da posição
            int colunaEsq = colunaPos - 1;
            while (colunaEsq >= 0 && mapa[linhaPos][colunaEsq] != 'O') {
                if (!visited[linhaPos][colunaEsq]) {
                    Point pontoAEsquerda = new Point(linhaPos, colunaEsq, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoAEsquerda);
                    visited[linhaPos][colunaEsq] = true;
                }
                colunaEsq--;
            }
    
            // Tenta mover a bola para a direita - Avalia a direita da posição
            int colunaDir = colunaPos + 1;
            while (colunaDir < nColunas && mapa[linhaPos][colunaDir] != 'O') {
                if (!visited[linhaPos][colunaDir]) {
                    Point pontoADireita = new Point(linhaPos, colunaDir, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoADireita);
                    visited[linhaPos][colunaDir] = true;
                }
                colunaDir++;
            }
        }   
        return result;
    }
}







