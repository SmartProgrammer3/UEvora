#include <stdio.h>

int main() {
    int a, b, multiplos=1;

    printf("Escolhe um número inteiro a:\n");
    scanf("%d", &a);
    printf("Escolhe um número inteiro b:\n");
    scanf("%d", &b);
    
    while(multiplos<=50)
    {
        if (multiplos%a == 0 && multiplos%b ==0){
            printf("%d : %d %d\n", multiplos, a, b);
        }
        else if(multiplos%a == 0){
            printf("%d : %d\n", multiplos, a);
        }
        else if(multiplos%b == 0){
            printf("%d : %d\n", multiplos, b);
        }
        multiplos++;
    }
return 0;


}