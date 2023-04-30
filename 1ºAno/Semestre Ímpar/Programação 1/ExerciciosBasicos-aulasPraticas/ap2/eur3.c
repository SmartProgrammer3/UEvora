#include <stdio.h>

int main () {
    float usd;
    float eur;
    printf ( "Qual o valor em usd?\n" );
    scanf ( "%f", &usd);
    eur = usd * 0.85;
    printf ("%f" , eur);
    return 0;
}