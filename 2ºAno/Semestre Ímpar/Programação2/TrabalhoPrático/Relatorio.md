# Relatório do Trabalho de Programação II

## Introdução
Este trabalho foi realizado pelo alunos **António Maria Carvalho** e **Luís Gonçalo Carvalho**, no âmbito da disciplina **Programação II** e tem como objetivo, simular a operação de uma máquina de distribuição de produtos. Como aquelas que encontramos na Universidade. 
- Este trabalho está divido em quatro partes. Em cada parte devemos implementar as seguintes Classes, os respetivos atributos e métodos.
    - Parte 1:
        - Classe `Product`: atributos `name` (nome do Produto) e `cost` (custo do Produto). Tem duas subclasses (`Perishable` e `NonPersihable`):
            - Classe `Perishable`: tem atributo extra `limitDate` (data limite para os produtos perecíveis).
                - Esta classe implementa a interface `Freshness`.
            - Classe `NonPersihable`: tem atributo extra `volume` (valor do volume do produto).
    - Parte 2:
        - Classe `ElementarMachine<T>`: Tem como atributo a **ArrayList** `listaElements` (Guarda os valores dos elementos). 
        - Tem duas subclasses (`ProductMachine` e `MoneyMachine`).
    - Parte 3:
        - Classe `ProductMachine`: Ao criar a ProductMachine, cria a ElementarMachine associada, onde o `tipo T` é `Product`.
        - Classe `MoneyMachine`: Ao criar a MoneyMachine, cria a ElementarMachine associada, onde o `tipo T` é `Float`.
        - Classe `VendingMachine`: Tem como atributos: `pm`, uma ProductMachine. `mm`, uma MoneyMachine.
    - Parte 4:
        - É onde se realiza a mecânica da compra do produto. Criámos o método **public boolean buy(String prod, MoneyMachine carteira)** na classe `VendingMachine`.

## Implementações
Parte  1:
- A classe `Product` é abstrata para não ser possível criar uma instância desta classe. O seu construtor **protected Product(String name, double cost)** cria o produto.
    - Os métodos **public String getName()** / **public double getCost()**, retornam, respetivamente, o nome do produto e o seu custo.
    - Os métodos **public void setName(String name)** / **public void setCost(double cost)**, insere, respetivamente, no atributo `name` o nome do produto e no atributo `cost` o custo do produto dados pelo utilizador.
    - De notar, que damos `@Override` do método **.equals()** de modo a facilitar se dois produtos são iguais.
- A subclasse `Perishable`. O seu construtor faz referência ao construtor da classe pai `Product` e como é um produto com validade, vai ter a instância limitDate.
    - O método **public Date getLimitDate()**, retorna a respetiva limitDate e o método **public void setLimitDate(Date limitDate)**, insere, no atributo `limitDate` a data limite para o consumo do produto, dada pelo utilizador.
    - É utilizada a interface dada pelo professor `Freshness`, onde com o método **public boolean isOutDated()** retorna *true*, se a data limite já passou, e *false*, caso contrário. O método **public boolean isFromToday()**, retorna *true* se a data limite do produto é de hoje, e *false*, caso contrário.
- A subclasse `NonPerishable`. O seu construtor faz referência ao construtor da classe pai `Product`, mas têm a característica adicional, como atributo `volume`.
    - O método **public double getVolume()**, retorna o respetivo volume e o método **public void setVolume(double volume)**, insere, no atributo `volume`, o volume do produto.

Parte 2:
- Para inicializar a classe `ElementarMachine<T>`, temos como instância uma **ArrayList** `listaElements`, onde armazena os valores dos elementos.
    - O construtor da classe **public ElementarMachine()** cria a **ArrayList** dos elementos.
    - O método **public ArrayList<Element<T>> getListaElements()** retorna a **ArrayList** dos elementos. E o método **public void setListaElements(ArrayList<Element<T>> listaElements)** insere na **ArrayList**, os valores dos elementos dados pelo utilizador.
    - O método **public void addThings(int n, T coisa)** adiciona a coisa **T** e a sua respetiva quantidade à **ArrayList**. Para tal, percorre a respetiva lista e verifica se a coisa a adicionar já existe ou não, com o auxílio do método (.equals()). De notar, que modificámos na classe `Product` o método (.equals()) de modo a comparar se a coisa for um produto se têm o mesmo nome de um elemento ou não.
    - Em ambos os casos, quer seja produto ou não, se não for encontrado a coisa na **ArrayList**, então é necessário criar um elemento novo (com o auxílio do construtor da classe `Element<T>`), com a respetiva quantidade e a coisa, para adicionar à **ArrayList**.

    - O método **public boolean removeOneThing(T coisa)** percorre a **ArrayList** com o ciclo "for".
        - O método é parecido ao métoodo a cima apresentado. Só que queremos remover uma coisa. Com o método (.equals()) verificamos se a coisa pretendida a remover equivale a um elemento presente na **ArrayList**. Caso a quantidade da coisa seja menor que dois, então o elemento é removido da **ArrayList**. Caso seja igual ou maior que dois, então apenas subtrai-se a quantidade por um.
    - O método **public void listAll()** dá print de todos os elementos da lista sem ser um produto (pois o método encontra-se na classe `ProductMachine`).

Parte 3:
- Classe `ProductMachine`:
    - O construtor faz refência ao construtor da classe `ElementarMachine<T>`. A coisa do `tipo T` torna-se do tipo `Product`.
    - O método **public void addProduct(int n, Product p)** adiciona à **ArrayList** `listaElements` a quantidade e o nome do produto.
    - O método **public boolean hasProduct(Product p)**  verifica se a lista tem o produto pretendido através do respetivo nome.
    - O método **public void listAllOrdered()** utilizámos o método `.sort()` para ordenar a **ArrayList** com base na comparação do custo dos produtos. O produto mais caro vai para o fim do array. E utilizamos o método .listAll() que demos `@Override`, de modo a imprimir a lista de produtos.
- Classe `MoneyMachine`:
    - O construtor faz refência ao construtor da classe `ElementarMachine<T>`. A coisa do `tipo T` torna-se do tipo `Float`.
    - O método **public float getTotalValue()**, devolve o valor total de dinheiro na máquina, para tal, percorremos a **ArrayList** e faz-se a soma constantemente do produto da quantidade da moeda (Pelo método **getCount()** da classe `Element<T>`) com o respetivo tipo da moeda (Pelo método **getThing()** da classe `Element<T>`).
    - O método **public void addMoney(int n, float money)** insere dinheiro na máquina, ou seja, adiciona a quantidade (n) e a quantia de cada moeda (em float) na **ArrayList**.
    - O método **public static boolean verifyInput(MoneyMachine cart, float coin)** permite verificar se a moeda que o utilizador coloca na máquina se é uma moeda com o valor existente (que pertence ao array **float[] moedas = { 0.1f, 0.2f, 0.5f, 1f, 2f, 5f, 10f, 20f }**). Se a moeda pertence a esse array, então é adicionada uma de cada vez à carteira.
- Classe `VendingMachine`:
    - O construtor cria uma máquina ProductMachine e uma MoneyMachine. Ou seja é uma simulação de uma máquina de distribuição de produtos.
    - Os métodos **public ProductMachine getProductMachine()** e **public MoneyMachine getMoneyMachine()**, retornam, respetivamente, a ProductMachine e a MoneyMachine.
    public void setMoneyMachine(MoneyMachine mm) { // Este método insere na variável pm, a MoneyMachine dada pelo
    - Os métodos **public void setProductMachine(ProductMachine pm)** e **public void setMoneyMachine(MoneyMachine mm)**, inserem, respetivamente, na instância `pm`, a ProductMachine, e em `mm`, a MoneyMachine, dadas pelo utilizador.
    - O método **public static void saveMachine(VendingMachine vm, String ficheiro)** permite guardar a VendingMachine no ficheiro pretendido. Utiliza-se a classe `FileOutputStream` e `ObjectOutputStream` para conseguir escrever no respetivo ficheiro.
    - O método **public static VendingMachine restoreMachine(String ficheiro)** permite ler uma ProductMachine e uma MoneyMachine escritas num ficheiro, com o auxílio das classes `FileInputStream` e `ObjectInputStream` e com isso, criar a respetiva VendingMachine.

Parte 4:
- O nosso método de compra de produtos é o seguinte:
    - Primeiro, o utilizador coloca a quantia de dinheiro pretendida na máquina. Uma moeda de cada vez e verificada pelo método **public static boolean verifyInput(MoneyMachine cart, float coin)**.
    - De seguida, é necessário escolher o produto pretendido. 
    - Com o método **public static double buy(VendingMachine vm, String prod, MoneyMachine carteira)** que está na classe `VendingMachine`, o programa correr a **ArrayList** à procura do produto pretendido pelo utilizador. Caso o produto seja encontrado, e o seu custo for maior que a carteira do utilizador, então ocorre uma excepção, com a mensagem de "valor insuficiente". Caso contrário, é realizado a operação do troco, cujo valor é a subtração da carteira com o custo, o produto escolhido é removido e é retornado o respetivo troco. 
    - O método que fizemos para o utilizador receber o troco é **public MoneyMachine calcTroco(float troco)**. Criámos uma variável constante `Limite`, para eliminar erro de cálculos. Na primeira condição, utilizamos o valor absoluto para verificar se a subtração do troco pela respetiva moeda é menor que o `Limite`, por causa do erro da igualdade de floats. Caso se verifique essa condição, então a respetiva moeda é devolvida como troco, é removida da MoneyMachine. Na segunda condição é para caso o troco e a moeda não se correspondam, ou seja, avalia-se cada moeda com o troco, se a divisão de ambas for menor que um, então significa que a moeda anterior à moeda avaliada "cabe" no troco, então essa moeda anterior é dada como troco, o troco passa a ser o resto da divisão do troco com a moeda anterior, e a moeda anterior é removida da MoneyMachine.

## Apreciação Crítica
Na nossa opinião, conseguimos implementar tudo o que era pedido, apesar do trabalho ter sido trabalhoso, mas que desenvolveu, certamente, a nossa capacidade de raciocínio e de programação na linguagem Java.