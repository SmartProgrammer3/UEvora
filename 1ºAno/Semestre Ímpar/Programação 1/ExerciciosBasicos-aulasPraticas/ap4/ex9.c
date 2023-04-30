#include <stdio.h>

int main() {
    int a, b, multiplos=1;

    printf("Escolha o número inteiro a:\n");
    scanf("%d", &a);
    printf("Escolha o número inteiro b:\n");
    scanf("%d", &b);

    while (multiplos<=50)
    {
        if(multiplos%a == 0 || multiplos%b == 0){
            printf("%d\n" , multiplos);
        }
    multiplos++; //multiplos ++, +1
    }
return 0;
}