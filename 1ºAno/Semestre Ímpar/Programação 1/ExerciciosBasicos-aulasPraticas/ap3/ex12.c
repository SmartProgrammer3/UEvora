#include <stdio.h>

int main(){
    int ano, bissexto;

    printf("Qual é o ano que quer verificar se é bissexto?\n");
    scanf("%d", &ano);

    if(ano%4 == 0) {
        printf("O ano é bissexto.");
        return 0;
    }
    else if(ano%400 == 0) {
        printf("O ano é bissexto.");
        return 0;
    }
    else if(ano%100 == 0) {
        printf("O ano não é bissexto.");
        return 0;
    }
    else{
        printf("O ano não é bissexto.");
        return 0;
    }
}