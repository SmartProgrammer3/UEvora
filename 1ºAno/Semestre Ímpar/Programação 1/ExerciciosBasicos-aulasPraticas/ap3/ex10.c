#include <stdio.h>

int main() {
    int x, y;

    printf("Indique a coordenada x:\n");
    scanf("%d" , &x);
    printf("Indique a coordenada y:\n");
    scanf("%d" , &y);

    if(x>0 && y>0) {
        printf("O ponto encontra-se no quadrante 1.");
        return 0;
    }
    else if(x<0 && y>0) {
        printf("O ponto encontra-se no quadrante 2.");
        return 0;
    }
    else if(x<0 && y<0){
        printf("O ponto encontra-se no quadrante 3.");
        return 0;
    }
    else if(x>0 && y>0){
        printf("O ponto encontra-se no quadrante 4.");
        return 0;
    }
}