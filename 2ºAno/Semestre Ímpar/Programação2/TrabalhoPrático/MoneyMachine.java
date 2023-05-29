
public class MoneyMachine extends ElementarMachine<Float> {
    // Quando é criar a MoneyMachine, cria a ElementarMachine associada.
    public MoneyMachine() {
        super();
    }

    // Este método retorna o total do valor presente na máquina.
    public float getTotalValue() {
        int amount = 0;
        float res = 0, coin = 0;

        // Percorre a lista retornada pelo método getListaElements().
        for (int i = 0; i < this.getListaElements().size(); i++) {
            amount = this.getListaElements().get(i).getCount(); // Quantidade das moedas que estão na máquina.
            coin = this.getListaElements().get(i).getThing(); // O respetivo tipo da moeda.
            res += (amount * coin); // Multiplica as moedas pelo valor da moeda.
        }
        return res; // total do valor presente na máquina.
    }

    // Este método insere dinheiro, a quantidade e a quantia de cada moeda.
    public void addMoney(int n, float money) {
        this.addThings(n, money);
    }

    /*
     * método verifyInput - permite verificar que o utilizador inseriu um valor de
     * moeda válido,
     * lança uma exceção caso o que o utilizador tenha inserido não seja o caracter
     * de paragem de inserção de moedas
     * ou uma moeda válida
     * argumentos:
     * MoneyMachine carteira - a carteira do utilizador onde será colocado o
     * dinheiro
     * String input - o que o utilizador inseriu que será avaliado se é moeda
     * válida, caso de saída do loop ou inválido
     */
    public static boolean verifyInput(MoneyMachine carteira, String input) throws Exception {
        float[] moedas = { 0.1f, 0.2f, 0.5f, 1f, 2f, 5f, 10f, 20f };

        if (input.equalsIgnoreCase("s")) {// caracter de paregem de inserção de moedas - 's'
            return false;
        } else {
            float coin = Float.parseFloat(input);

            for (int i = 0; i < moedas.length; i++) {// verifica se a o valor inserido é uma moeda válida

                if (coin == moedas[i]) {
                    carteira.addMoney(1, coin);
                    return true;
                }
            }
        }
        throw new Exception("Input Inválido");// se não satisfazer nenhum destes caso lança exceção
    }

}
