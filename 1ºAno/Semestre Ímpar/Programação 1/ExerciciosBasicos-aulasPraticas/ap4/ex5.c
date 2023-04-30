#include <stdio.h>

int main () {
    int numero; 
    int contagem=0;
    int soma=0; 
    float media;
    
    do
    {
        printf("Qual é o valor?\n");
        scanf("%d", &numero);
        soma= soma + numero;               
        contagem= contagem + 1;         // cada volta/ciclo/loop, aumenta 1 valor nos valores introduzidos contagem++
    } while (numero != 0);

    media= soma/contagem;
    printf("Valores introduzidos: %d\n", contagem);
    printf("Média: %f", media);
return 0;
}