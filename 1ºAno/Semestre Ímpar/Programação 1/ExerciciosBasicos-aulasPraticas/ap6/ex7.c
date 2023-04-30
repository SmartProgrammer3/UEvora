#include <stdio.h>

float tempoDecorrido(float distancia, float velocidade){
    float tempo;
    return tempo=distancia/velocidade; 
}

int main() {
    float distancia;
    float velocidade;
    printf("Insira a distância em km:\n");
    scanf("%f", &distancia);
    printf("Insira uma velocidade em km/h:\n");
    scanf("%f", &velocidade);

    printf("A custo de encomenda é: %.2f",tempoDecorrido(distancia, velocidade));

    return 0;
}