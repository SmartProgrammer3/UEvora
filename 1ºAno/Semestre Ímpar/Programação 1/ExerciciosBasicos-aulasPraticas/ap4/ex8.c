#include <stdio.h>

int main() {
    int numero, fatorial;
    int neutro=0;
    int sum=1;

    printf("Qual é o número que queres fazer o seu fatorial?");
    scanf("%d", &numero);

    if (numero >= 0) {
        while (numero>neutro)
        {
            sum= sum * numero;
            numero--; //numero= numero-1      ++ -> +1
            fatorial=sum;
        }
        printf("O fatorial é %d", fatorial);
    }
    else{
        printf("Escolha um número inteiro positivo.");
    }
return 0;
}