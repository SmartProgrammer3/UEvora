#include <stdio.h>

int main () {
    float num2;
    char caracter;
    int num1;

    printf("Qual o valor do número real?\n");
    scanf("%f" , &num2);
    printf("Qual o caracter?\n");
    scanf(" %c" , &caracter);
    printf("Qual o valor do número inteiro?\n");
    scanf("%d" , &num1);
    printf("real: %f\n" , num2);
    printf("caracter: %c\n" , caracter);
    printf("inteiro: %d\n" , num1);
    printf("%d" , num1);
    printf("%c" , caracter);
    printf("%f" , num2);
    
    return 0;
} 