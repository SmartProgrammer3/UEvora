import java.io.Serializable;

public abstract class Product implements Serializable{
    protected String name; // Variável name: Guarda o nome do produto.
    protected double cost; // Variável cost: Guarda o preço do produto.
        
    /*
    * Construtor da classe abstrata Product.
    * A classe é abstrata para não ser possível criar uma instância desta classe.
    *
    * Objetivo: Criar o produto.
    * Tem como argumentos, o nome do produto e o respetivo custo.
    */
    protected Product(String name, double cost) {
        setName(name); 
        setCost(cost);
    }
    // Este método retorna o nome do produto.
    public String getName() { 
        return name;
    }

    // Este método insere na variável name, o nome do produto dado pelo utilizador.
    public void setName(String name) { 
        this.name = name; 
    }

    // Este método retorna o custo do produto.
    public double getCost() { 
        return cost;
    }

    // Este método insere na variável cost, o custo do produto dado pelo utilizador.
    public void setCost(double cost) { 
        this.cost = cost; 
    }

    //override do metodo equals para quando precisarmos de comparar product para que seja o nome que é comparado
    @Override
    public boolean equals(Object obj){
        if(this.getName().equals(((Product) obj).getName())){
            return true;
        }
        return false;
    }

}

