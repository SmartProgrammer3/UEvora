#include <stdio.h>

int main(){

    float velocidade, distancia;

    printf("Qual a velocidade?\n");
    scanf("%f" , &velocidade);

    distancia = (velocidade/10)*(velocidade/10)/2;

    printf("A distancia de travagem é: %f" , distancia);

return 0;
}