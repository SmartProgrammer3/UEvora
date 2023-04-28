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
        
        String[] line = input.readLine().split(" ");
        
        int numeroDeLinhas = Integer.parseInt(line[0]);
        int numeroDeColunas = Integer.parseInt(line[1]);
        int numerorCasosATestar = Integer.parseInt(line[2]);

        char mapa[][] = new char[numeroDeLinhas][numeroDeColunas]; // Mapa com o tamanho das linhas e colunas dadas pelo utilizador
        
        
        // Criar o mapa com os valores dados pelo utilizador
        for (int i = 0; i < numeroDeLinhas; i++) {
            String linhaMapa = input.readLine();                      // . (dot) -> Empty location
            for (int j = 0; j < numeroDeColunas; j++) {               // letra "O" -> ObstÃ¡culo
                mapa[i][j] = linhaMapa.charAt(j);                     // letra "H" -> Buraco (Objetivo onde queremos chegar)
            }
        }

        // Avaliar os testes propostos
        for (int caso = 0; caso < numerorCasosATestar; caso++){
            String[] linhaTeste = input.readLine().split(" ");
            
            // PosiÃ§Ã£o inicial da esfera em cada teste
            int coordLinha = Integer.parseInt(linhaTeste[0]); 
            int coordColuna = Integer.parseInt(linhaTeste[1]);        
            
            // resultado = move
            int resultado = bfs(mapa, coordLinha - 1, coordColuna - 1);
            
            if (resultado == -1) { // NÃ£o foi encontrado moves atÃ© ao buraco
                System.out.println("Stuck");
            } else {
                System.out.println(resultado);
            }
        }
    }

    private static int bfs(char[][] mapa, int linhaInicio, int colunaInicio) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(linhaInicio, colunaInicio, 0));

        int nLinhas = mapa.length; // Numero de linhas do mapa
        int nColunas = mapa[0].length; // Numero de colunas do mapa

        boolean[][] posicoesAvaliadas = new boolean[nLinhas][nColunas]; // Matriz para avaliar as posiÃ§Ãµes que jÃ¡ foram avaliadas do mapa
        posicoesAvaliadas[linhaInicio][colunaInicio] = true;
        int resultado = -1; 

        // Se a posiÃ§Ã£o inicial for num objeto, entÃ£o nÃ£o estÃ¡ disponivel.
        if (mapa[linhaInicio][colunaInicio] == 'O') {
            return resultado;
        }
        
    
        while (!queue.isEmpty()) {
            Point pontoEmQueEsta = queue.poll(); //Remove o ponto em que estÃ¡ na queue
            int linhaPos = pontoEmQueEsta.getRow();
            int colunaPos = pontoEmQueEsta.getCol();

            // Se a bola chegou ao buraco
            if (mapa[linhaPos][colunaPos] == 'H') {
                resultado = pontoEmQueEsta.getMoves();
                break;
            }


            // Tenta mover a bola para a direita - Avalia a direita da posiÃ§Ã£o
            int colunaDir = colunaPos + 1;
            while (colunaDir < nColunas) {

                if (mapa[linhaPos][colunaDir] == 'O') {
                    if(!posicoesAvaliadas[linhaPos][colunaDir - 1]){
                        Point pontoADireita = new Point(linhaPos, colunaDir - 1, pontoEmQueEsta.getMoves() + 1);
                        queue.add(pontoADireita);
                        posicoesAvaliadas[linhaPos][colunaDir - 1] = true;
                        break;
                    }
                    break;
                }

                // Se a bola chegou ao buraco
                else if (mapa[linhaPos][colunaDir] == 'H') {
                    posicoesAvaliadas[linhaPos][colunaDir] = true;
                    Point pontoADireita = new Point(linhaPos, colunaDir, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoADireita);
                    resultado = pontoEmQueEsta.getMoves() + 1;
                    break;
                }
                colunaDir++;
            }


            // Tenta mover a bola para baixo - Avalia em baixo da posiÃ§Ã£o
            int linhaBaixo = linhaPos + 1;
            while (linhaBaixo < nLinhas) {

                if (mapa[linhaBaixo][colunaPos] == 'O') {
                    if(!posicoesAvaliadas[linhaBaixo - 1][colunaPos]){
                        Point pontoEmBaixo = new Point(linhaBaixo - 1 , colunaPos, pontoEmQueEsta.getMoves() + 1);
                        queue.add(pontoEmBaixo);
                        posicoesAvaliadas[linhaBaixo - 1][colunaPos] = true;
                        break;
                    }
                    break;
                }

                // Se a bola chegou ao buraco
                else if (mapa[linhaBaixo][colunaPos] == 'H') {
                    posicoesAvaliadas[linhaBaixo][colunaPos] = true;
                    Point pontoEmBaixo = new Point(linhaBaixo, colunaPos, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoEmBaixo);
                    resultado = pontoEmQueEsta.getMoves() + 1;
                    break;
                }
                linhaBaixo++;
            } 


            // Tenta mover a bola para a esquerda - Avalia a esquerda da posiÃ§Ã£o
            int colunaEsq = colunaPos - 1;
            while (colunaEsq >= 0) {

                if (mapa[linhaPos][colunaEsq] == 'O') {
                    if(!posicoesAvaliadas[linhaPos][colunaEsq + 1]){
                        Point pontoAEsquerda = new Point(linhaPos, colunaEsq + 1, pontoEmQueEsta.getMoves() + 1);
                        queue.add(pontoAEsquerda);
                        posicoesAvaliadas[linhaPos][colunaEsq + 1] = true;
                        break;
                    }
                    break;
                }

                // Se a bola chegou ao buraco
                else if (mapa[linhaPos][colunaEsq] == 'H') {
                    posicoesAvaliadas[linhaPos][colunaEsq] = true;
                    Point pontoAEsquerda = new Point(linhaPos, colunaEsq, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoAEsquerda);
                    resultado = pontoEmQueEsta.getMoves() + 1;
                    break;
                }
                colunaEsq--;
            }


            // Tenta mover a bola para cima - Avalia em cima da posiÃ§Ã£o
            int linhaCima = linhaPos - 1;
            while (linhaCima >= 0) {
                if (mapa[linhaCima][colunaPos] == 'O') {
                    if(!posicoesAvaliadas[linhaCima + 1][colunaPos]){
                        Point pontoEmCima = new Point(linhaCima + 1 , colunaPos, pontoEmQueEsta.getMoves() + 1);
                        queue.add(pontoEmCima);
                        posicoesAvaliadas[linhaCima + 1][colunaPos] = true;
                        break;
                    }
                    break;
                }

                // Se a bola chegou ao buraco
                else if (mapa[linhaCima][colunaPos] == 'H') {
                    posicoesAvaliadas[linhaCima][colunaPos] = true;
                    Point pontoEmCima = new Point(linhaCima, colunaPos, pontoEmQueEsta.getMoves() + 1);
                    queue.add(pontoEmCima);
                    resultado = pontoEmQueEsta.getMoves() + 1;
                    break;
                }
                linhaCima--;
            }            
        }   
        return resultado;
    }
}
