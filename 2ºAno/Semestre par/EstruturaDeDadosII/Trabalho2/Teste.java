import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Teste {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // número de testes

        for (int t = 1; t <= T; t++) {
            String[] dim = br.readLine().split("x");
            int rows = Integer.parseInt(dim[0]);
            int cols = Integer.parseInt(dim[1]);

            char[][] map = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = br.readLine();
                for (int j = 0; j < cols; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            String[] startPos = br.readLine().split(" ");
            int startRow = Integer.parseInt(startPos[0]) - 1;
            int startCol = Integer.parseInt(startPos[1]) - 1;

            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(startRow, startCol, 0));

            int result = -1; // tempo para a bola chegar ao buraco

            while (!queue.isEmpty()) {
                Point curr = queue.poll();
                int currRow = curr.getRow();
                int currCol = curr.getCol();

                if (currRow < 0 || currRow >= rows || currCol < 0 || currCol >= cols) {
                    // se a bola saiu do mapa, interrompe a busca
                    break;
                }

                if (map[currRow][currCol] == 'H') {
                    // a bola chegou ao buraco
                    result = curr.getTime();
                    break;
                }

                // tenta mover a bola para cima
                int upRow = currRow - 1;
                if (upRow >= 0 && map[upRow][currCol] != 'O') {
                    Point up = new Point(upRow, currCol, curr.getTime() + 1);
                    queue.add(up);
                }

                // tenta mover a bola para baixo
                int downRow = currRow + 1;
                if (downRow < rows && map[downRow][currCol] != 'O') {
                    Point down = new Point(downRow, currCol, curr.getTime() + 1);
                    queue.add(down);
                }

                // tenta mover a bola para a esquerda
                int leftCol = currCol - 1;
                if (leftCol >= 0 && map[currRow][leftCol] != 'O') {
                    Point left = new Point(currRow, leftCol, curr.getTime() + 1);
                    queue.add(left);
                }

                // tenta mover a bola para a direita
                int rightCol = currCol + 1;
                if (rightCol < cols && map[currRow][rightCol] != 'O') {
                    Point right = new Point(currRow, rightCol, curr.getTime() + 1);
                    queue.add(right);
                }
            }

            System.out.println("Teste " + t);
            if (result == -1) {
                System.out.println("impossivel");
            } else {
                System.out.println(result);
            }
            System.out.println();
        }
    }
}

class Point {
    private int row;
    private int col;
    private int time;

    public Point(int roww, int coll, int timee) {
        this.row = roww;
        this.col = coll;
        this.time = timee;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public int getTime() {
        return time;
    }
}


