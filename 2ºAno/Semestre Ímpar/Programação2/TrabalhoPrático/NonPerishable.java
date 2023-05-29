public class NonPerishable extends Product{
    
    private double volume; // Esta variável guarda o valor do volume do produto.

    /*
    * Construtor da classe NonPerishable. É uma subclasse da classe Produto.
    * Objetivo: Criar um produto 'NonPerishable'.
    * Tem como argumentos, o nome do produto, o custo e o volume do produto.
    */
    public NonPerishable(String name, double cost,double volume) {
        
        super(name, cost);
        setVolume(volume);
    }

    // Este método retorna o valor do volume do produto.
    public double getVolume() {
        return volume;
    }

    // Este método insere na variável volume, o volume fornecido.
    public void setVolume(double volume) {
        this.volume = volume;
    }
}