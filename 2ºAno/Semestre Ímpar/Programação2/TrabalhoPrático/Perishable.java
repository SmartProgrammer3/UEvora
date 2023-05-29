import java.util.Date;

public class Perishable extends Product implements Freshness {

    private Date limitDate; // Esta variável guarda o valor da data limite para o consumo do produto.

    /*
    * Construtor da classe Perishable. É uma subclasse da classe Produto.
    * Objetivo: Criar um produto 'Perishable'.
    * Tem como argumentos, o nome do produto, o custo e a data limite.
    */
    public Perishable(String name, double cost,Date limitDate) {
        super(name, cost);
        setLimitDate(limitDate);
    }
    
    // Este método retorna a data limite para o consumo do produto.
    public Date getLimitDate() { 
        return limitDate;
    }

    // Este método insere na variável limitDate, a data limite para o consumo do produto.
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /* Implementa o método definido na interface 'Freshness' que 
    * calcula se a data limite ja passou(antes de hoje)
    */
    @Override
    public boolean isOutDated() {
        Date today = new Date(System.currentTimeMillis());
        return this.limitDate.before(today);
    }

    /* Implementa o método definido na interface 'Freshness' que 
    * verifica se o produto acaba hoje
    */
    @Override
    public boolean isFromToday() {
        Date today = new Date(System.currentTimeMillis());
        return this.limitDate.equals(today);   
    }
}
