#include <stdio.h>

int main() {
    float altura;

    printf("Qual é a altura da pessoa?\n");
    scanf("%f" , &altura);

    if(altura<1.30){
        printf("Essa pessoa é baixissima");
        return 0;
    }
    else if(altura>=1.30 && altura<1.60){
        printf("Essa pessoa é baixa.");
        return 0;
    }
    else if(altura>=1.60 && altura<1.75){
        printf("Essa pessoa é mediana.");
        return 0;
    }
    else if(altura>=1.75 && altura<1.90){
        printf("Essa pessoa é alta.");
        return 0;
    }
    else if(altura>=1.90){
        printf("Essa pessoa é altíssima");
        return 0;
    }
}