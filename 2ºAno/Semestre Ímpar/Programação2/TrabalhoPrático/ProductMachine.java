
public class ProductMachine extends ElementarMachine<Product> {
    // Quando é criar a ProductMachine, cria a ElementarMachine associada.
    public ProductMachine() {
       super();
    }

    
    // Este método insere o produto à lista.
    public void addProduct(int n, Product p) {
        this.addThings(n, p);
    }


    // Este método verifica se a lista tem o produto pretendido através do respetivo nome.
    public boolean hasProduct(Product p) {
        for (int i = 0; i < this.getListaElements().size(); i++) {   // Percorre a lista
            
            // Se encontrar algum elemento com o mesmo nome do produto que se quer.
            if (this.getListaElements().get(i).getThing().getName().equals(p.getName())) {
                return true; // Então a máquina tem o produto.
            }
        }
        return false;
    }


    // Este método ordena a lista dos produtos por ordem crescente do custo, ou seja, o produto mais caro fica no fim da arrayList.
    public void listAllOrdered() {
        // O método sort() ordena os elementos com base na comparação de dois elementos pelo custo.
        this.getListaElements().sort((element1, element2) -> Double.compare(element1.getThing().getCost() ,(element2.getThing().getCost())));

        // Dá print à lista dos produtos.
        this.listAll();
    }

    
    @Override
    public void listAll(){
        for (int i = 0; i < this.getListaElements().size(); i++) {
            System.out.println("Element ["+ this.getListaElements().get(i).getThing().getClass().getSimpleName() + "=Product [name=" + 
                this.getListaElements().get(i).getThing().getName()+ ", cost=" +this.getListaElements().get(i).getThing().getCost() + "], count = " + this.getListaElements().get(i).getCount() + "]");
        }
    }
}