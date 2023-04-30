#include <stdio.h>

int main() {
    int num;
    int soma=0;

    do
    {
        printf("Escolha um número:");
        scanf("%d", &num);
        soma = soma + num;
    } while (num!=0);

    printf("A soma é: %d" , soma);
    return 0;
}