#include <stdio.h>

int main(){
    int num1, num2, soma, diferenca, produto, divisao, resto;
    printf("Qual o valor do número 1?\n");
    scanf("%d" , &num1);
    printf("Qual o valor do número 2?\n");
    scanf("%d" , &num2);
    printf ("numero1= %d\n",num1);
    printf ("numero2= %d\n", num2);
    printf ("soma= %d\n" , soma = num1+num2);
    printf ("diferenca= %d\n" , diferenca = num1-num2);
    printf ("produto= %d\n" , produto = num1*num2);
    printf ("divisao= %d\n" , divisao = num1/num2);
    printf ("resto= %d\n" , resto = num1%num2);
    return 0;

}