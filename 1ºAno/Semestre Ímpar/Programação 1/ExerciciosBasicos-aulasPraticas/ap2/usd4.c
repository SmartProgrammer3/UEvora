#include <stdio.h>

int main () {
    float eur;
    float usd;
    printf ("Qual o valor em EUR?\n");
    scanf( "%f" , &usd);
    eur = usd * 1.16;
    printf("%f" , eur);
    return 0;
}