#include <stdio.h>

int main() {
    int a=1;
    int b=0;

    while (a != 0)
    {
        printf("Insira um número inteiro:\n");
        scanf("%d", &a);
        if(a>b){
            b=a;
        }
    }
    printf("O máximo é: %d", b);
return 0;

}