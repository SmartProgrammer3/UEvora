#include <stdio.h>
#include "bigInt.h"
#include "doubleLList.h"
#include <string.h>
#include <stdlib.h>
#include "fatal.h"


struct DNode{
    ElementType Elemento;
    DPosition Next;
    DPosition Previous;
};


struct DListStruct{
    int tamanhoLista;
    DPosition Header;
    DPosition Footer;
};


DList CreateDList( void ){
    DList L;

    L = malloc(sizeof(struct DListStruct));
    
    if(L == NULL){ // Se não tiver informação então está fora da memória
        FatalError("Out of memory");
    }
    
    L->Header = malloc(sizeof(struct DNode));
    L->Footer = malloc(sizeof(struct DNode));  
    L->tamanhoLista = 0;

    L->Header->Next = L->Footer;
    L->Footer->Previous = L->Header;

    return L;
}


void MakeEmptyDList( DList L){ 
    DPosition posicaoAtual = DHeader(L); 

    while (posicaoAtual != DFooter(L))
    {          
        DPosition proximaPosicao =Advance(posicaoAtual);
        DeleteElement(Retrieve(posicaoAtual), L); //O elemento da posição a ser avaliada passa para a função delete, onde o respetivo node vai levar free

        posicaoAtual = proximaPosicao; // Passa para a próxima posição
    }
    L->tamanhoLista = 0;
}


int SizeDList(DList L){ 
    return L->tamanhoLista;
}


DPosition DHeader(DList L){ 
    return L->Header; 
}


DPosition DFooter(DList L){ 
    return L->Footer; 
}


int IsEmptyDList(DList L){ // Posso também ver se está vazia se o header for nulo.
    if(L->tamanhoLista == 0)
        return 1;
    return 0;
}


void InsertDList(ElementType X, DPosition P, DList L){ // Adiciona na lista o elemento X, na posição P entre o header e o footer
    // Criar o novo node, que será à medida que é adicionado, o último node.
    DPosition novoNodeInserido = malloc(sizeof(struct DNode));
    
    if (novoNodeInserido == NULL)
    {
        FatalError("Out of memory");
    }
   
    novoNodeInserido->Elemento = X; // O novo node vai ter como elemento data, o elemento requerido.
    novoNodeInserido->Previous = P; // O previous do novo node vai ser igual à informação da memória da posição anterior.
    novoNodeInserido->Next = P->Next; // O next do novo node vai ser igual ao next da posição anterior (P), visto que este node vai ficar atrás do node que estava a seguir do node da posição anterior deste novo node.
    
    P->Next->Previous = novoNodeInserido; // O previous do próximo node vai ser igual a este novo node
    P->Next = novoNodeInserido; // O node anterior, da posição P, o next é igual a este novo node
    
    L->tamanhoLista++; // Ao inserir um node novo, é incrementado o tamanho da lista.
}


void InsertDListIth(ElementType X, int i, DList L){
    DPosition posicaoAnterior = malloc(sizeof(struct DNode));
    posicaoAnterior = Advance(DHeader(L)); 
    int contadorPosicao = 1;
    
    if (posicaoAnterior == NULL)
    {
        FatalError("Out of memory");
    }

    while (contadorPosicao < i) // Quero ir ver a posição anterior para depois acrescentar o node na posição que quer
    {
        posicaoAnterior = Advance(posicaoAnterior);
        contadorPosicao++;
    }
    
    InsertDList(X, posicaoAnterior, L);
  
}


void addDList(ElementType X, DList L){ // adiciona elementos a começar no fim da lista
    DPosition adicionarUltimaPosicao = Back(DFooter(L)); // Começa no fim da lista
    InsertDList(X, adicionarUltimaPosicao, L);  // Adiciona na última posição o elemento X
}


int FindDList(ElementType e, DList L){ // Encontrar na lista, o elemento e
    DPosition posicaoDoElemento = Advance(DHeader(L)); 
    int posicao = 1;

    while (posicaoDoElemento != DFooter(L)) // Enquanto que o elemento data for diferente que o elemento que estamos à procura
    {
        if (Retrieve(posicaoDoElemento) == e) // Se após incrementar, vamos verificar se essa nova posição o elemento data é igual ao elemento e.
        {
            return posicao; // Caso seja igual, retornamos a posição.
        }  
        posicaoDoElemento = Advance(posicaoDoElemento); // Avança de posição, passa a a ser o next em cada posição, ou seja avança de node em cada ciclo.
        posicao++;  
    }

    return 0; // Se ao percorrer a lista, não for encontrado nenhum elemento e, então retorna 0, porque não existe esse elemento na lista.
}


void DeleteElement(ElementType e, DList L){  // Esta função percorre a lista toda à procura de um elemento pretendido, ao encontrar o respetivo elemento, deleta o node completo
    DPosition nodeASerDestruido = Advance(DHeader(L));

    if (IsEmptyDList(L)) // Se a lista estiver vazia, então automaticamente não existe elemento a eliminar
    {
        printf("Não existe elemento igual ao elemento e na lista, visto que a lista está vazia.");
    } else { // Se a lista não estiver vazia, então existe elementos
        while ((nodeASerDestruido != DFooter(L)) && (Retrieve(nodeASerDestruido) != e)) // Este ciclo while vai permitir percorrer a lista à procura de elementos que sejam iguais ao elemento e, desde o next do header.
        {                                                        // até ao footer, ou seja, desde o inicio até ao fim da lista.         
            nodeASerDestruido = Advance(nodeASerDestruido);
        }
        if ((nodeASerDestruido == DFooter(L)) && (Retrieve(nodeASerDestruido) != e))
        {
            return;
        } 

        nodeASerDestruido->Previous->Next = nodeASerDestruido->Next; // O next do node anterior vai apontar para o node que estava a ser apontado no next do node a ser destruido (seguinte do mesmo)
        nodeASerDestruido->Next->Previous = nodeASerDestruido->Previous; // O previous do node seguinte vai apontar para o node que estava a ser apontado no previous do node a ser destruido (anterior do mesmo)
                                
        free(nodeASerDestruido); // Dar free ao node com o elemento e, deletá-lo completamente
        L->tamanhoLista--; // Ao deletar um node, decrementar o tamanho da lista
        
    }
}


ElementType RemoveElementAt(int i, DList L){ // Remove um elemento da posição que eu quero
    DPosition nodeASerDestruido = DHeader(L);
    int contadorPosicao = 0;


    if (IsEmptyDList(L)) // Se a lista estiver vazia, então automaticamente não existe elemento a eliminar
    {
        printf("A lista está vazia.");
    } else {
        if ((i >= 1) && (i <= SizeDList(L))) // A posição i tem que ser maior ou igual a 1, e tem que estar dentro do tamanho
        {
            if (i == 1) // Se a posição do elemento a ser removida for a primeira posição
            {
                L->Header = nodeASerDestruido->Next; //  A primeira posição é removida, o next passa para o header, porque o node seguinte que estava apontado do next deste node, passa a estar apontado no header, porque passa a ser a 1 posição.
                free(nodeASerDestruido); // Eliminar o node.
                L->tamanhoLista--;
            } else{ // Se a posição for diferente que a primeira (i!=1)
                while (contadorPosicao != i) // Continuar o ciclo até chegar à posição i na lista
                {
                    if (nodeASerDestruido->Next != NULL) // Se existir uma próxima posição, ou seja, se o next da atual não for nula, se estiver a apontar para o próximo node, então pode continuar, significa que existe mais uma posição/node.
                    {
                        nodeASerDestruido = nodeASerDestruido->Next; // O novo node é o próximo
                        contadorPosicao++; // Próxima posição
                    } else{
                        printf("Essa posição não há, porque a lista acabou antes de chegar a essa posição.");
                        return 0;
                    }
                }    
                // Ao chegar à posição i, vamos eliminar o respetivo node dessa posição.                 
                nodeASerDestruido->Previous->Next = nodeASerDestruido->Next;
                nodeASerDestruido->Next->Previous = nodeASerDestruido->Previous;
                free(nodeASerDestruido);
                L->tamanhoLista--;      
            }
        }
    }
}


DPosition Advance(DPosition P){  // A função avançar, o next aponta para o próximo node.
    return P->Next;
}


DPosition Back(DPosition P){ // A função recuar, o previous aponta para o node anterior.
    return P->Previous;
}


ElementType Retrieve(DPosition P){ 
    return P->Elemento;
}


void PrintDList(char *name, DList L){
    DPosition listaPrint = Advance(DHeader(L));
    
    printf("|");   

    while (listaPrint != DFooter(L)) // Percorrer a lista até chegar ao fim.
    {
        printf("%d", Retrieve(listaPrint));
        printf("|");
        listaPrint = Advance(listaPrint);
    }
    
    printf("\n");
}