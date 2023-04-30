#include <stdio.h>

float velocidadeCarro(float vi, float a, float t) {
    float velocidade;
    return velocidade=vi+a*t;    
}

int main() {
    float vi, a, t;
    printf("Insira a velocidade inicial:\n");
    scanf("%f", &vi);
    printf("Insira a aceleração:\n");
    scanf("%f", &a);
    printf("Insira o tempo:\n");
    scanf("%f", &t);

    printf("A velocidade é: %.2f",velocidadeCarro(vi, a, t));

    return 0;
}