#include <stdio.h>

int verificar_ordem(int vetor[], int tamanho){
    if (tamanho<=1)
    {                                           // vetor[o]=N existe vetor[1]=1 Logo é ordenada, caso base.
        return 1;
    }
    else if (vetor[tamanho-1]>=vetor[tamanho-2] && vetor[tamanho-2]>=vetor[tamanho-3])
    {
        return 1;
    }
    else{
        return 0;
    }
    
}

int main(){
    int a[3]={1,2,2};
    printf("Valor: %d\n", verificar_ordem(a,3));
    return 0;  
}