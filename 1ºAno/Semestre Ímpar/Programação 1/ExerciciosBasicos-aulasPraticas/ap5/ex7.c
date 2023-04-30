#include <stdio.h>

int main() {
    int n1, d1, n2, d2, nsoma, dsoma;

    printf("Insira o numerador da fração 1:\n");
    scanf("%d", &n1);
    printf("Insira o denominador da franção1:\n");
    scanf("%d", &d1);
    printf("Insira o numerador da fração 2:\n");
    scanf("%d", &n2);
    printf("Insira o denominador da fração 2:\n");
    scanf("%d", &d2);

    if(d1 == d2){
        nsoma=n1+n2;
        dsoma=d1;
        printf("A soma das frações é: %d/%d\n", nsoma, dsoma);
    }
    else{
        nsoma=(n1*d2)+(n2*d1);
        dsoma=(d1*d2);
        printf("A soma das frações é: %d/%d\n", nsoma, dsoma);
    }
return 0;
}