#include <stdio.h>
#include <math.h>

int main() {
    int x;
    int y=10;
    float soma=0, potencia=1;

    printf("Dá um valor ao x:\n");
    scanf("%d", &x);

    while (y!=0)
    {
        if (y%2==0)          // se o termo for par        
        {
            soma=soma+(1/potencia)*pow(x,potencia);   // Se a potência for par
        }
        else
        {
            soma=soma-(1/potencia)*pow(x,potencia);   // Se a potência for ímpar
        }
    potencia=potencia+2;
    y=y-1; // y--
    }
    printf("A soma dos primeiros dez termos é: %f\n", soma);
return 0;
}