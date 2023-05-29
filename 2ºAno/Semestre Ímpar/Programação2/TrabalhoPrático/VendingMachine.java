import java.io.*;
import java.util.*;

public class VendingMachine implements Serializable {
    ProductMachine pm;
    MoneyMachine mm;
    static final float Limite = 0.000001f;
  

    /*
     * Construtor da classe VendingMachine.
     * Ojetivo: Criar uma máquina ProductMachine e uma MoneyMachine.
     */
    public VendingMachine(ProductMachine pm, MoneyMachine mm) {
        setProductMachine(pm);
        setMoneyMachine(mm);
    }

    public ProductMachine getProductMachine() { // Este método retorna a productMachine
        return pm;
    }

    public void setProductMachine(ProductMachine pm) { // Este método insere na variável pm, a productMachine dada pelo
                                                       // utilizador.
        this.pm = pm;
    }

    public MoneyMachine getMoneyMachine() { // Este método retorna a MoneyMachine
        return mm;
    }

    public void setMoneyMachine(MoneyMachine mm) { // Este método insere na variável pm, a MoneyMachine dada pelo
                                                   // utilizador.
        this.mm = mm;
    }

    /*
     * Método saveMachine
     * Este método permite guardar uma VendingMachine num ficheiro pretendido.
     */
    public static void saveMachine(VendingMachine vm, String ficheiro) throws IOException {

        FileOutputStream foutStream = new FileOutputStream(ficheiro); // permite escrever dados binarios para um ficheiro.
        ObjectOutputStream outputStream = new ObjectOutputStream(foutStream); // Permite escrever no ficheiro um arraylist

        try {
            outputStream.writeObject(vm.getProductMachine().getListaElements()); // Escreve a lista de produtos
            outputStream.writeObject(vm.getMoneyMachine().getListaElements()); //  Escreve a lista de tipo de moedas
            outputStream.close();
            foutStream.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     * Método restoreMachine
     * Este método permite ler uma VendingMachine do ficheiro pretendido.
     */
    @SuppressWarnings("unchecked")
    public static VendingMachine restoreMachine(String ficheiro) throws IOException, ClassNotFoundException {

        File f = new File(ficheiro);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream objStream = new ObjectInputStream(fis);

        ProductMachine prod = new ProductMachine();
        MoneyMachine mon = new MoneyMachine();


        prod.setListaElements((ArrayList<Element<Product>>) objStream.readObject());
        mon.setListaElements((ArrayList<Element<Float>>) objStream.readObject());

        VendingMachine vm = new VendingMachine(prod, mon);

        fis.close();
        objStream.close();
        return vm;

    }


    /*
     * Método buy
     * Este método permite comprar o produto pretendido pelo utilizador.
     * recebe os argumentos 
     * Vending vm - a maquina de vendas que se está a utilizar
     * String prod - o nome do produto a comprar
     * float totalvalue - o valor total que o ultizador inseriu na máquina
     */
    public static float buy(VendingMachine vm, String prod, float totalvalue) throws Exception{
        
        double custo = 0; // Custo produto
        float troco=0; // Troco 

        
        // Percorre a lista à procura do produto pretendido do utilizador para realizar a compra.
        for (int i = 0; i < vm.getProductMachine().getListaElements().size(); i++) {    
            if (vm.getProductMachine().getListaElements().get(i).getThing().getName().equalsIgnoreCase(prod)) { // Se encontrou.
                
                custo = vm.getProductMachine().getListaElements().get(i).getThing().getCost(); // Custo do produto
                
                if (custo > totalvalue) {   // Se o custo é maior que a carteira do utilizador
                    
                    throw new Exception("Crédito insuficiente, adicione mais dinheiro.");
                } else { // Se o utilizador tem crédito suficiente

                    troco = (float) (totalvalue-custo); // O troco é a subtração do crédito do utilizador com o custo.
                    vm.getProductMachine().removeOneThing(vm.getProductMachine().getListaElements().get(i).getThing()); // Como foi comprado, então retira-se o elemento da ArrayList 
                    return troco;/// Retorna o troco                 }
                }
            }
        }
        throw new Exception("Produto não existe");// Caso não tenha sido encontrado o produto pretendido.
    }


    /*
     * Método calcTroco
     * Este método permite calcular o troco e as respetivas moedas.
     * recebe os argumentos 
     * float troco - o valor do troco da compra
     */
    public MoneyMachine calcTroco(float troco) {

        MoneyMachine carteiraTroco = new MoneyMachine(); // Cria uma carteira para adicionar as moedas do troco.

        int i = 0;
        
        // Ordena a lista dos tipos das moedas por ordem crescente do valor do tipo de moeda.      
        this.getMoneyMachine().getListaElements().sort((element1, element2) -> Double.compare(element1.getThing() ,(element2.getThing())));


        // Percorre a lista da Money Machine        
        while ( i < this.getMoneyMachine().getListaElements().size()) {
            
            // Se a subtração do troco com a respetiva moeda for "zero", então o valor da moeda é igual ao valor do troco
            if (Math.abs(troco - this.getMoneyMachine().getListaElements().get(i).getThing())< Limite){
                
                carteiraTroco.addMoney(1, this.getMoneyMachine().getListaElements().get(i).getThing());// Adiciona essa moeda à carteira do utilizador.
                this.getMoneyMachine().removeOneThing(this.getMoneyMachine().getListaElements().get(i).getThing());// Remove essa meoda da carteira da máquina.
                    
                break;

            }else if(((troco-this.getMoneyMachine().getListaElements().get(i).getThing())<0)&&(i>0)){ // se a razão do troco pela moeda da posição i for menor que 1 significa q a moeda i é maior que o troco logo vai buscar a moeda abaixo
                
                carteiraTroco.addMoney(1, this.getMoneyMachine().getListaElements().get(i-1).getThing()); 
                
                troco -=(this.getMoneyMachine().getListaElements().get(i-1).getThing()); // O troco passa a ser o resto entre o troco e a moeda anterior
                this.getMoneyMachine().removeOneThing(this.getMoneyMachine().getListaElements().get(i-1).getThing()); // Remove-se da lista a moeda anterior à moeda avaliada.
                
                i=0;
                continue;// e volta ao início do array de moedas da money machine
            }

            i++;
        }

        return carteiraTroco;
    }
}
