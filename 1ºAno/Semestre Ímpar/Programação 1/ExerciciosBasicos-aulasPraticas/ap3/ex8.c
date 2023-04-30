#include <stdio.h>

int main() {
    int num1, num2;

    printf("Qual é o número 1?\n");
    scanf("%d" , &num1);
    printf("Qual é o número 2?\n");
    scanf("%d" , &num2);

    if(num1%num2==0) {
        printf("O primeiro número é múltiplo do segundo."); // Posso fazer como em baixo %d É multiplo de %d, num1, num2..
        return 0;
    }  
    else{
        printf("%d Não é multiplo de %d.", num1, num2);
        return 0;
    }

}