#include <stdio.h>
#include <math.h>

int main() {
    int x1, x2, y1, y2;
    float distancia;

    printf("Dê as coordenadas do ponto 1:\n");
    printf("x:\n");
    scanf("%d", &x1);
    printf("y:\n");
    scanf("%d", &y1);

    printf("Dê as coordenadas do ponto 2:\n");
    printf("x:\n");
    scanf("%d", &x2);
    printf("y:\n");
    scanf("%d", &y2);

    distancia = sqrt(((x2-x1)*(x2-x1))+(y2-y1)*(y2-y1));
    printf("A distância entre os 2 pontos é: %f", distancia);
    return 0;
}
// ap3/ex11