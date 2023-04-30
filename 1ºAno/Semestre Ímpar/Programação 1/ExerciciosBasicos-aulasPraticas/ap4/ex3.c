#include <stdio.h>

int main() {
    int limite;
    int x=0;

    printf("Escolha um limite da contagem crescente e decresente.");
    scanf("%d", &limite);

    while(x !=limite) {
        x=x+1;
        printf("%d\n", x);
    }
    while(x != 0) {
        x=x-1;
        printf("%d\n", x);
    }
return 0;
}