# Programação II
## Enunciado do Trabalho Prático


### Condições Gerais

- Grupos com 2 ou 1 elemento
- Prazos
    - entrega: 14 de janeiro
    - discussão: 16-17 de janeiro
- Entrega
    - via Moodle
    - ficheiros Java
    - ficheiro Markdown com relatório (até 3 páginas)

### Descrição Geral
Pretende-se simular a operação de uma máquina distribuidora de produtos, como as que se encontram nos espaços públicos. Em termos gerais, a máquina deverá lidar com:

- o stock de produtos
- o dinheiro;

Deve ser possível gerir o stock das máquinas. De modo geral podemos dizer que o stock é uma lista de produtos. Em cada máquina, os diferentes produtos estão separados em compartimentos. 

### Descrição Específica

Cada `Product` (produto) é descrito pelo seguintes atributos `name`, `cost`. Existem dois tipos de produtos: 

- `Perishable` (perecível) 
- `NonPersihable`. 

Os produtos perecíveis têm uma data limite (`limitDate`) para consumo e implementam o interface `Freshness`

```java
public interface Freshness {

    /**
     * @return true if is outdated and false otherwise
     */
    public boolean isOutDated();

    /**
     * @return true if is from today and false otherwise
     */
    public boolean isFromToday();

}
```


Os não perecíveis, como característica adicional o `volume`. Não deve ser possível criar `Product`, somente `Perishable` e  `NonPersihable`.

Uma `VendingMaching` é composta por máquinas relacionadas com produtos e com dinheiro. 

Com o intuito de uniformizar, vamos considerar uma máquina geral, defina através de uma classe geral `ElementarMachine<T>`. Resumidamente esta máquina deverá ser uma lista de `Element<T>` definidos da seguinte forma:

```java
import java.io.Serializable;

/**
 * Generic class that defines the basic element
 * composed by a number of parameterized things
 */

public class Element<T> implements Serializable {

    // data members

    private int count;
    private T thing;

    // -----------------------------------------
    //
    // Constructors:
    //
    // -----------------------------------------

    public Element(int count, T thing) {
        this.count = count;
        this.thing = thing;
    }

    /** 
     * Returns the number of elements
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the number of elements
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the parameterized thing
     * @return T
     */
    public T getThing() {
        return thing;
    }

    /**
     * Sets the parameterized thing
     * @param thing
     */
    public void setThing(T thing) {
        this.thing = thing;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Element ["+ getThing().getClass().getSimpleName() + "=" + 
        thing + ", count = " + getCount() + "]";
    }

}
```

Cada compartimento de uma `ElementarMachine<T>` só pode ter um tipo de coisa, e cada coisa só pode existir num comparimento.
As instâncias de classe `ElementarMachine<T>`  devem implementar os métodos:

- `public void addThings(int n, T)`: adiciona `n` coisas
- `public boolean removeOneThing(T)`: remove uma coisa
- `public void listAll()`: escreve a lista de coisas

Existem dois (sub)tipos de máquinas: `ProductMachine` e `MoneyMachine`. A `ProductMachine` deve implementar os métodos:

- `public void addProduct(int n, Product)`: adiciona `n` produtos
- `protected boolean hasProduct(Product)`
- `public void listAllOrdered( )`: lista todos os produtos por ordem crescente de custo

A classe `MoneyMachine` deve implementar os métodos:

- `public float getTotalValue()`: devolve o valor total na máquina
- `public void addMoney(int n, float)`: adiciona `n` vezes o `float`

A `VendingMachine` deve ter pelo menos uma `ProductMachine` e uma `MoneyMachine` assim como os métodos de classes:

- `public static void saveMachine(VendingMachine, String)`: guarda a máquina num ficheiro com o nome dado
- `public static VendingMachine restoreMachine(String)`: devolve a máquina contida no ficheiro com o nome dado

e os métodos de instância:

- `public ProductMachine getProductMachine()`
- `public void setProductMachine(ProductMachine)`

O processo de compra produtos deve ser decidido pelo grupo.



