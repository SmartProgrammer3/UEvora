#include <stdio.h>


void ordenarValores( int *valorA, int *valorB, int *valorC){
    int auxiliarMenor;
    int auxiliarMaior;
    int auxiliarMedio;

    if (*valorA > *valorB && *valorA > *valorC)
    {
        auxiliarMaior = *valorA;
        if (*valorB > *valorC)
        {
            auxiliarMedio = *valorB;
            auxiliarMenor = *valorC;
        } else{
            auxiliarMedio = *valorC;
            auxiliarMenor = *valorB;
        }
    }
    if (*valorB > *valorA && *valorB > *valorC)
    {
        auxiliarMaior = *valorB;
        if (*valorA > *valorC)
        {
            auxiliarMedio = *valorA;
            auxiliarMenor = *valorC;
        } else {
            auxiliarMedio = *valorC;
            auxiliarMenor = *valorA;
        }   
    }
    if (*valorC > *valorA && *valorC > *valorB)
    {
        auxiliarMaior = *valorC;
        if (*valorB > *valorA)
        {
            auxiliarMedio = *valorB;
            auxiliarMenor = *valorA;
        } else {
            auxiliarMedio = *valorA;
            auxiliarMenor = *valorB;
        }   
    }
    *valorC = auxiliarMaior;
    *valorB = auxiliarMedio;
    *valorA = auxiliarMenor;
} 


void main(){
    int valorA;
    int valorB;
    int valorC;

    printf("Digite o valor A: ");
    scanf("%d", &valorA);
    printf("Digite o valor B: ");
    scanf("%d", &valorB);
    printf("Digite o valor C: ");
    scanf("%d", &valorC);

    ordenarValores(&valorA, &valorB, &valorC);

    printf("%d\n%d\n%d\n", valorA, valorB, valorC);
}