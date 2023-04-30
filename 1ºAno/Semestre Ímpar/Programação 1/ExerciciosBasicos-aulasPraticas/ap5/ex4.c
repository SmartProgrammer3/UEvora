#include <stdio.h>

int main() {
    int numeros, maximo, contagem, posicao;
    numeros=1;
    maximo=0;
    contagem=1;

    while (numeros !=0)
    {
        printf("Insira um número inteiro:\n");
        scanf("%d", &numeros);
        if(numeros>maximo){
            maximo=numeros;
            posicao=contagem;
        }
    contagem++; //contagem +1
    }
    printf("O máximo é: %d, %dº posição\n", maximo, posicao);
return 0;
}