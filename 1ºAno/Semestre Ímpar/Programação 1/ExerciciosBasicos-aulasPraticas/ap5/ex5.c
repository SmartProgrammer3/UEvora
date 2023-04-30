#include <stdio.h>

int main() {
    int n1, n2, divisor, i;

    printf("Insira um número inteiro:\n");
    scanf("%d", &n1);
    printf("Insira outro número inteiro:\n");
    scanf("%d", &n2);

    for ( i = 1; i <= n1 && i <= n2; i++) // i=0 (Um ciclo é 1, a cada ciclo add 1), para continuar o ciclo tem que ser menor ou igualque 
                                        // os números, para poder ser divisor, o ultimo nº será o maior divisor.
    {
        if (n1%i==0 && n2%i==0) //Para ser divisor, o resto tem que ser 0.
        {
            divisor=i;
        }
    }
    printf("O máximo divisor é: %d", divisor);
return 0;
}