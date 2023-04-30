#include <stdio.h>

int main () {
    float tc, tk, tf;
    printf ("Qual o valor da temperatura em graus celsius?\n");
    scanf ("%f" , &tc);
    printf("Temperatura em graus Celsius: %f\n" , tc);
    printf("Em Kelvin: %f\n" , tk = tc+273.15);
    printf("Em Fahrenheit: %f\n" , tf = tc * 9/5 +32);
    return 0;
}