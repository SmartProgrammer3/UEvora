#include <stdio.h>

int main()  {

    int segundos, dias, horas, minutos, segundossobrantes;

    printf("Quantos segundos?\n");
    scanf("%d" , &segundos);

    dias = segundos/86400;
    horas = (segundos%86400)/3600;
    minutos = (segundos%86400)%3600/60;
    segundossobrantes = (segundos%86400)%3600%60;

    printf("Quantos segundos: %d\n" , segundos);
    printf("dias: %d\n" , dias);
    printf("horas: %d\n" , horas);
    printf("minutos: %d\n" , minutos);
    printf("segundos: %d\n" , segundossobrantes);

return 0;
}