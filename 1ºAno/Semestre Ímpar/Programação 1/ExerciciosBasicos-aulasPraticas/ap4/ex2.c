#include <stdio.h>
#include <math.h>

int main() {    
    int cateto1, cateto2;
    float hipotenusa;

    printf("Qual o comprimento do cateto 1?");
    scanf("%d", &cateto1);
    printf("Qual o comprimento do cateto 2?");
    scanf("%d", &cateto2);
    
    hipotenusa = sqrt(pow(cateto1,2)+pow(cateto2,2));

    printf("A hipotenusa é: %f", hipotenusa);
return 0; 
}