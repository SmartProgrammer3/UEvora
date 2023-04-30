#include <stdio.h>

int main() {
    int n1, n2, divisor, i, multiplo;

    printf("Insira um número inteiro:\n");
    scanf("%d", &n1);
    printf("Insira outro número inteiro:\n");
    scanf("%d", &n2);

    for ( i = 1; i <= n1 && i <= n2; i++) 
    {
        if (n1%i==0 && n2%i==0) 
        {
            divisor=i;
        }
    }
    multiplo=(n1*n2)/divisor;  // O minimo multiplo comum é a multiplicação dos numeros a dividir pelo maximo divisor comum.
                              //Ex: 6 e 8, divisor:2  6x8=48/2=24, é o primeiro numero multiplo entre ambos os numeros.

    printf("O minimo multiplo comum é: %d", multiplo);
return 0;
}