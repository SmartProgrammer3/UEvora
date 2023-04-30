#include <stdio.h>

int main() {
    int num;

    printf("Escolha um numero inteiro:");
    scanf("%d", &num);

    if (num%2 == 1)
    {
        if (num%3 == 0)
        {
            printf("O número é ímpar e múltiplo de 3.");
        }
        else if (num%3 != 0) 
        {
            printf("O número é apenas ímpar."); 
        }
    }
    else 
    {
        if (num%3 == 0)
        {
            printf("O número é apenas múltiplo de 3.");
        }
        else if (num%3 != 0) 
        {
            printf("O número não é múltiplo de 3 nem ímpar."); 
        }
    }
return 0;
}