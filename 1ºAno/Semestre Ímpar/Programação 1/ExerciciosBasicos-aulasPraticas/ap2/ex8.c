#include <stdio.h>

int main(){

    int numero, result[3];

    printf("Escolhe um número inteiro de três algarismos\n");
    scanf("%d" , &numero);

    result[1] = numero/100;
    result[2] = numero/10%10;
    result[3] = numero%10;

    printf("Digito das centenas: %d\n", result[1]);
    printf("Digito das dezenas: %d\n" , result[2]);
    printf("Digito das unidades: %d\n", result[3]);

return 0;
}