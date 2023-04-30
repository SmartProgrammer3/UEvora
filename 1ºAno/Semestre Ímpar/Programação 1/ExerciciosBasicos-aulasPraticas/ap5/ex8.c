#include <stdio.h>
#include <math.h>

int main() {
    int numero, n1, n2, n3;

    printf("Digite um número inteiro de três algarismos:\n");
    scanf("%d", &numero);

    n1=numero/100;
    n2=(numero/10)%10;
    n3=numero%10;

    if(numero==pow(n1,3)+pow(n2,3)+pow(n3,3)) {
        printf("O número inteiro é igual à soma do cubo dos algarismos que o constitui.\n");
    }
    else{
        printf("O número inteiro não é igual à soma do cubo dos algarismos que o constitui.\n");
    }
return 0;
}