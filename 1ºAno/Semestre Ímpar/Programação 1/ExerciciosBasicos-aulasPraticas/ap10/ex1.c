#include <stdio.h>

int *valorMaior( int *valor1, int *valor2 ){
    if (*valor1 > *valor2)
    {
        return valor1;
    } else {
        return valor2;
    }
}



void main(){
    int valor1;
    int valor2;


    printf("Digite o valor 1:");
    scanf("%d", &valor1);
    printf("Digite o valor 2:");
    scanf("%d", &valor2);

    printf("\n O endereço de maior valor é: %p",valorMaior(&valor1, &valor2));
}