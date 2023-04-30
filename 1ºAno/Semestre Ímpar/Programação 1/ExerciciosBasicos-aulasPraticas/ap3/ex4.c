#include <stdio.h>
#include <math.h>

int main()  {
    int numero;
    float raiz;

    printf("Qual o número inteiro?\n");
    scanf("%d" , &numero);
    if (numero >= 0) {
        raiz = sqrt(numero);
        printf("Qual o número? %d\n" , numero);
        printf("A raiz quadrada é: %f\n" , raiz);
    }
    
    else {
        printf("O numero inserido é negativo.");
    }
    

return 0;
}
