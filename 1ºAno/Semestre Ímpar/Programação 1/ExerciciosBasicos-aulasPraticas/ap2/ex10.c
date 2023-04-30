#include <stdio.h>

int main()  {

    float numero , x;
    int y;

    printf("Qual o número real?\n");
    scanf("%f" , &numero);

    x = numero * 1000;      // 1.2345 --> 1234.5
    y = (int) x;            // 1234.5 --> 1234
    x = y/1000.0;           // 1234 --> 1.234

    printf("%f" , x);
    
return 0;

}