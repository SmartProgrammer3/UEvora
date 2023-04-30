#include <stdio.h>

int main() {
    int numero;
    int n1, n2, n3;

    printf("Escolhe um número inteiro de três algarismos:");
    scanf("%d", &numero);

    if(numero>0 && numero<1000) {
    n1= numero / 100;
    n3= numero % 10;
        if(n1==n3){
            printf("%d é capicua.", numero);
            return 0;
        }
        else{
            printf("%d não é capícua.", numero);
            return 0;
        }
    }
    else{
        printf("Escolha um número positivo com três algarismos.");
        return 0;
    }
}