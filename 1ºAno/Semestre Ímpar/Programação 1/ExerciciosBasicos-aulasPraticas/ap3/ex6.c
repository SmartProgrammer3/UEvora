#include <stdio.h>

int main() {
    int i1, i2, i3, i4;
    int numero;
    i1=0;
    i2=20;
    i3=40;
    i4=60;

    printf("Qual o número que queres?\n");
    scanf("%d" , &numero);

    if(numero>i1 && numero<i2 || numero>i3 && numero<i4) {
        printf("O numero escolhido encontra-se no intervalo");
        return 0;
    }
    else{
        printf("O número escolhido não se encontra no intervalo");
        return 0;
    }

}