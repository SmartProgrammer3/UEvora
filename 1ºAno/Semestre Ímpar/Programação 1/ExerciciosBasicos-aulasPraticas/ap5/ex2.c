#include <stdio.h>
#include <math.h>

int main () {
    float a, b, c, raiz1, raiz2;

    printf("Qual é o termo de segundo grau?\n");
    scanf("%f", &a);
    printf("Qual é o termo de primeiro grau?\n");
    scanf("%f", &b);
    printf("Qual é o termo independente?\n");
    scanf("%f", &c);

    raiz1 = ((-b)+sqrt(pow(b,2)-4*a*c))/2*a;
    raiz2 = ((-b)-sqrt(pow(b,2)+4*a*c))/2*a;

    if (pow(b,2)-4*a*c<0)
    {
        printf("O resultado da raiz quadrada não é real.");
    }
    else if (raiz1 == raiz2) {
        printf("A raiz do polinómio é: %.2f", raiz1);
    }
    else if(raiz1 != raiz2) {
        printf("As raizes do polinómio são: %.2f %.2f", raiz1, raiz2);
    }
return 0;
}