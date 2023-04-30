#include <stdio.h>

int main()  {
    
    int num1, num2, num3;

    printf("Número 1?");
    scanf("%d" , &num1);
    printf("Número 2?");
    scanf("%d" , &num2);
    printf("Número 3?");
    scanf("%d" , &num3);

    if (num1>num2 && num1<num3 || num1>num3 && num1<num2) {
        printf("É o número: %d\n", num1);
        return 0;
    }
    else if(num2>num3 && num2<num1 || num2>num1 && num2<num3){
        printf("É o número: %d\n", num2);
        return 0;   
    }
    else if(num3>num1 && num3<num2 || num3>num2 && num3<num1){
        printf("É o número: %d\n", num3);
        return 0;
    }
}