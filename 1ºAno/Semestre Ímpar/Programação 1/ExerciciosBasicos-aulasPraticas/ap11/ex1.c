#include <stdio.h>
#include "ex1.h"
#include <math.h>
#include <stdlib.h>
#include <string.h>


typedef struct numerosComplexos //typedef permite dar um novo nome a um tipo
{
    float parteReal;
    float parteImaginaria;
} numerosComplexos;

numerosComplexos somaNumerosComplexos(numerosComplexos n1, numerosComplexos n2){
    numerosComplexos soma;
    soma.parteReal = n1.parteReal + n2.parteReal;
    soma.parteImaginaria = n1.parteImaginaria + n2.parteImaginaria;
    return soma;
}

float moduloDosComplexosUm(numerosComplexos n1){
    numerosComplexos produtoCoordenadaN1;
    double moduloN1;

    produtoCoordenadaN1.parteReal = n1.parteReal * n1.parteReal;
    produtoCoordenadaN1.parteImaginaria = n1.parteImaginaria *n1.parteImaginaria;


    moduloN1 = sqrt(produtoCoordenadaN1.parteReal + produtoCoordenadaN1.parteImaginaria);

    return moduloN1;
}

float moduloDosComplexosDois(numerosComplexos n2){
    numerosComplexos produtoCoordenadaN2;
    double moduloN2;

    produtoCoordenadaN2.parteReal = n2.parteReal * n2.parteReal;
    produtoCoordenadaN2.parteImaginaria = n2.parteImaginaria * n2.parteImaginaria;

    moduloN2 = sqrt(produtoCoordenadaN2.parteReal + produtoCoordenadaN2.parteImaginaria);

    return moduloN2;
}

float argumentoAngularUm(numerosComplexos n1, double moduloN1){
    float argumentoAngular;
    float seno;

    seno = sin(n1.parteReal);

}



void main() {
    numerosComplexos n1, n2, resultadoSoma;
    int opcao1, opcao2;
    float resultadoModulo;
    float resultadoArgumentoAngular;

    printf("Para o numero complexo 1 (n1) \n");
    printf("Digita a parte real e parte imaginaria do n1, formato: X Y \n");
    scanf("%f %f", &n1.parteReal, &n1.parteImaginaria);
    printf("Para o numero complexo 2 (n2) \n");
    printf("Digita a parte real e parte imaginaria do n2, formato: X Y \n");
    scanf("%f %f", &n2.parteReal, &n2.parteImaginaria);

    resultadoSoma = somaNumerosComplexos(n1, n2);

    printf("A soma dos pontos complexos são = %.1f + %.1fi\n", resultadoSoma.parteReal, resultadoSoma.parteImaginaria);

    printf("Qual é o número que queres para calcular o modulo? 1 ou 2?\n");
    scanf("%d", &opcao1);

    if (opcao1 == 1)
    {
        resultadoModulo = moduloDosComplexosUm(n1);
    } else {
        resultadoModulo = moduloDosComplexosDois(n2);
    }

    printf("O resultado do módulo do numero escolhido é: %.1f\n", resultadoModulo);

    printf("Qual é o número em que queres estudar o argumento ângular? 1 ou 2?\n");
    scanf("%d", &opcao2);
}

