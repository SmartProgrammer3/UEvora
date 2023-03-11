import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class ConveyorBelts {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Indique o número de casos a testar: ");
        int numCases = Integer.parseInt(input.readLine());  // número de casos para testar (0 < C < 5)
        

        for (int caseNum = 1; caseNum <= numCases; caseNum++) { // Vai testar caso a caso.

            System.out.println("Caso a testar: nº " + caseNum);
            System.out.println(" ");
            System.out.print("Indique o número de produtos para o Conveyor belt 1: ");

            int numProducts1 = Integer.parseInt(input.readLine()); // Quantidade de produtos que está no conveyor belt 1 (0 ≤ L ≤ 2000)
            List<Product> products1 = new ArrayList<>(); // ArrayList que contém os produtos do conveyor belt 1

            for (int i = 0; i < numProducts1; i++) { // Este ciclo percorre todos os produtos, um a um, até à quantidade de produtos que o conveyor belt 1 tem. 
                System.out.println("Indique o produto nº" + (i+1) + ": nomeProduto tipoProduto valorProduto" );

                String[] line = input.readLine().split(" ");
                String name = line[0]; // Lê o nome do produto (o comprimento do nome do produto está entre 0 < |S| ≤ 32)
                char type = line[1].charAt(0); // Lê o tipo do produto
                int value = Integer.parseInt(line[2]); // Lê o valor do produto (o valor do produto está entre 0 ≤ V ≤ 100 000)
                products1.add(new Product(name, type, value)); // É criado o produto na classe Product e é adicionado na lista de produtos do conveyor belt 1.
            }

            System.out.println(" ");
            System.out.print("Indique o número de produtos para o Conveyor belt 2: ");

            int numProducts2 = Integer.parseInt(input.readLine()); // Quantidade de produtos que está no conveyor belt 2 (0 ≤ L ≤ 2000)
            List<Product> products2 = new ArrayList<>(); // ArrayList que contém os produtos do conveyor belt 2
        
            for (int i = 0; i < numProducts2; i++) { // Este ciclo percorre todos os produtos, um a um, até à quantidade de produtos que o conveyor belt 2 tem. 
                System.out.println("Indique o produto nº" + (i+1) + ": nomeProduto tipoProduto valorProduto" );

                String[] line = input.readLine().split(" ");
                String name = line[0]; // Lê o nome do produto (o comprimento do nome do produto está entre 0 < |S| ≤ 32)
                char type = line[1].charAt(0); // Lê o tipo do produto
                int value = Integer.parseInt(line[2]); // Lê o valor do produto (o valor do produto está entre 0 ≤ V ≤ 100 000)
                products2.add(new Product(name, type, value)); // É criado o produto na classe Product e é adicionado na lista de produtos do conveyor belt 2.
            }


            int maxTotalValue = getMaxTotalValue(products1, products2);
            System.out.println(maxTotalValue);
        }
    }

    /*
     * Este método tem como objetivo retornar o máximo valor possível.
     * Recebe como argumentos as listas dos produtos (os conveyor belts)
     */
    private static int getMaxTotalValue(List<Product> products1, List<Product> products2) {
        int maxTotalValue = 0; // Valor máximo inicializado a zero.
        List<Product> ListaProductsQueFalta1 = new ArrayList<>();
        List<Product> ListaProductsQueFalta2 = new ArrayList<>();

        for (int i = 0; i < products1.size(); i++) { // Percorre a lista de produtos que o conveyor belt 1 tem.
            for (int j = 0; j < products2.size(); j++) { // Percorre a lista de produtos que o conveyor belt 2 tem.
                if (products1.get(i).type == products2.get(j).type) { // Se o tipo dos produtos for igual.
                    int pairValue = products1.get(i).value + products2.get(j).value; // O valor da soma dos dois produtos do mesmo tipo.

                    ListaProductsQueFalta1 = products1.subList(i + 1, products1.size());
                    ListaProductsQueFalta2 = products2.subList(j + 1, products2.size());
                    int remainingValue = getMaxTotalValue(ListaProductsQueFalta1, ListaProductsQueFalta2);

                    maxTotalValue = Math.max(maxTotalValue, pairValue + remainingValue);
                }
            }
        }
        return maxTotalValue;
    }

    private static int getMinNumPairs(List<Product> products1, List<Product> products2) {
        int[][] dp = new int[products1.size() + 1][products2.size() + 1];

        for (int i = products1.size() - 1; i >= 0; i--) {
            for (int j = products2.size() - 1; j >= 0; j--) {
                if (products1.get(i).type == products2.get(j).type) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i + 1][j], dp[i][j + 1]));
            }
        }
        return dp[0][0];
    }

}