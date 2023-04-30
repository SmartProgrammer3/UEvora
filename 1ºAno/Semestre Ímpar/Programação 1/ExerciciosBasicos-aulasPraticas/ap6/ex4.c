#include <stdio.h>

float posicaoCarro(float pi, float vi, float a, float t) {
    float posicao;
    return posicao=pi+(vi*t)+(a*t*t)/2;    
}

int main() {
    float pi, vi, a, t;
    printf("Insira a posição inicial:\n");
    scanf("%f", &pi);
    printf("Insira a velocidade inicial:\n");
    scanf("%f", &vi);
    printf("Insira a aceleração:\n");
    scanf("%f", &a);
    printf("Insira o tempo:\n");
    scanf("%f", &t);

    printf("A posição é: %.2f",posicaoCarro(pi, vi, a, t));

    return 0;
}