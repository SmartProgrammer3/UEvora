#include <stdio.h>

float horaChegada(float hp, float mp, float d1, float d2, float d3, float v1, float v2, float v3){
    float tempo, t1, t2, t3;

        t1=d1/v1;
        t2=d2/v2;
        t3=d3/v3;
    
    return tempo= hp + (mp/60) + t1 + t2 + t3; 
}

int main() {
    float hp;
    float mp;
    float d1,d2,d3;
    float v1,v2,v3;
    
    printf("Qual é a hora do início da corrida?:\n");
    scanf("%f", &hp);
    printf("Qual é o minuto do início da corrida?:\n");
    scanf("%f", &mp);
    
    printf("Distância 1:\n Distância 2:\n Distância 3:\n");
    scanf("%f", &d1);
    scanf("%f", &d2);
    scanf("%f", &d3);
    
    printf("Velocidade 1:\n Velocidade 2:\n Velocidade 3:\n");
    scanf("%f", &v1);
    scanf("%f", &v2);
    scanf("%f", &v3);

    printf("A hora de chegada é: %.2f", horaChegada(hp,mp,d1,d2,d3,v1,v2,v3));

    return 0;
}