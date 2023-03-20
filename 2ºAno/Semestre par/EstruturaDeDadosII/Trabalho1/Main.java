package Trabalho1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main  {

    static class Product {
        String name;
        char type;
        int value;

        public Product(String name, char type, int value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(input.readLine());  // número de casos para testar (0 < C < 5)
        
        for (int caseNum = 1; caseNum <= numCases; caseNum++) { // Vai testar caso a caso.

            int numProducts1 = Integer.parseInt(input.readLine()); // Quantidade de produtos que está no conveyor belt 1 (0 ≤ L ≤ 2000)
            List<Product> products1 = new ArrayList<>(); // ArrayList que contém os produtos do conveyor belt 1

            for (int i = 0; i < numProducts1; i++) { // Este ciclo percorre todos os produtos, um a um, até à quantidade de produtos que o conveyor belt 1 tem. 

                String[] line = input.readLine().split(" ");
                String name = line[0]; // Lê o nome do produto (o comprimento do nome do produto está entre 0 < |S| ≤ 32)
                char type = line[1].charAt(0); // Lê o tipo do produto
                int value = Integer.parseInt(line[2]); // Lê o valor do produto (o valor do produto está entre 0 ≤ V ≤ 100 000)
                products1.add(new Product(name, type, value)); // É criado o produto na classe Product e é adicionado na lista de produtos do conveyor belt 1.
            }


            int numProducts2 = Integer.parseInt(input.readLine()); // Quantidade de produtos que está no conveyor belt 2 (0 ≤ L ≤ 2000)
            List<Product> products2 = new ArrayList<>(); // ArrayList que contém os produtos do conveyor belt 2
        
            for (int i = 0; i < numProducts2; i++) { // Este ciclo percorre todos os produtos, um a um, até à quantidade de produtos que o conveyor belt 2 tem. 

                String[] line = input.readLine().split(" ");
                String name = line[0]; // Lê o nome do produto (o comprimento do nome do produto está entre 0 < |S| ≤ 32)
                char type = line[1].charAt(0); // Lê o tipo do produto
                int value = Integer.parseInt(line[2]); // Lê o valor do produto (o valor do produto está entre 0 ≤ V ≤ 100 000)
                products2.add(new Product(name, type, value)); // É criado o produto na classe Product e é adicionado na lista de produtos do conveyor belt 2.
            }


            int[][] maxValue = new int[numProducts1 + 1][numProducts2 + 1]; // Matriz para guardar os valores máximos 
            int[][] minPairs = new int[numProducts1 + 1][numProducts2 + 1]; // Matriz que guarda os respetivos pares (número minimo) para cada valor máximo

            // Estes dois loops vão avaliar cada produto do conveyor belt 1 como cada produto do conveyor belt 2
            for (int i = 1; i <= numProducts1; i++) { // Conveyor belt 1 

                for (int j = 1; j <= numProducts2; j++) { // Conveyor belt 2
                    int value = 0; // Valor máximo, inicializado a 0.

                    if (products1.get(i-1).type == products2.get(j-1).type) { // Se for encontrado um produto do conveyor belt 1 com o mesmo tipo do produto do conveyor belt 2
                        value = products1.get(i-1).value + products2.get(j-1).value; 
                    }

                    /*
                    *  O valor máximo para a posição (i, j) da matriz maxValue é o maior valor, entre o valor da 
                    *  posição anterior na mesma coluna (i-1, j), ou o maior valor entre a posição anterior na mesma 
                    *  linha (i, j-1) e o valor da posição diagonal anterior (i-1, j-1) somado com o valor 
                    *  "value" que foi atualizado anteriormente se houve produtos do mesmo tipo nos 
                    *   dois conveyor belts.
                    */
                    maxValue[i][j] = Math.max(maxValue[i - 1][j], Math.max(maxValue[i][j - 1], maxValue[i - 1][j - 1] + value));
                     
                    /*
                     * Se o valor máximo for igual é o mesmo valor na posição acima na mesma coluna,
                     * então o número de pares é igual.
                     */
                    if (maxValue[i][j] == maxValue[i - 1][j]) {
                        minPairs[i][j] = minPairs[i - 1][j];
                    }
                    /*
                     * Se o valor máximo for igual na coluna anterior na mesma linha,
                     * então o número de pares é igual.
                     */
                    else if (maxValue[i][j] == maxValue[i][j - 1]) {
                        minPairs[i][j] = minPairs[i][j - 1];
                    } else {  // Caso o valor máximo seja diferente.
                        if (value > 0) { // Se o valor for maior que zero, significa que os produtos são do mesmo tipo.
                            minPairs[i][j] = minPairs[i - 1][j - 1] + 1; // Aumenta o número de pares.
                        } else {
                            minPairs[i][j] = minPairs[i - 1][j - 1];
                        }
                    }   
                }
            }
            System.out.println(maxValue[numProducts1][numProducts2] + " " + minPairs[numProducts1][numProducts2]);
        }
    }
}