#include <stdio.h>

// Esta função serve para devolver o total do indice zero até o indice indicado pelo utilizador
int acumulado(int tamanho, int valoresVetores[tamanho], int index) { 
    int soma=0;
    int contador=0;

    for ( contador = 0; contador < index; contador++)
    {
        soma += valoresVetores[contador]; //soma=soma+valoresVetores[contador]
    }
    return soma;
}


// Esta função serve para o utilizador construir o vetor com os seus dados
int listaDeValoresVetor(int tamanho, int valoresVetores[tamanho]){
    int x;   //x-->1º valor do array
 
    for ( x = 0; x < tamanho; x++ )
    {
        printf ("\nIntroduza o valor do elemento[%d] : ", x+1);
        scanf ("%d",&valoresVetores[x]);
    }
    return valoresVetores[tamanho];
}


void main(){
    int tamanho;
    int index;

    printf("\nDigite o tamanho do seu vetor: ");
    scanf("%d", &tamanho);

    int vV[tamanho];                // Guardo a função numa variável porque vou precisar da variável para outra função
    int valoresRetorno[tamanho];
    valoresRetorno[tamanho]=listaDeValoresVetor(tamanho, vV);

    printf("\nDigite qual é o índice que quer para fazer a soma: ");
    scanf("%d", &index);

    printf("\nA soma dos elementos do índice 0 até ao índice %d = %d\n", index, acumulado(tamanho, valoresRetorno, index));
}