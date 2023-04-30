#include <stdio.h>

int main() {
    float litros, valor;
    float gasolina95=1.798;
    float gasoleo=1.711;
    float gasolina98=1.838; 
    int opcao, dia;
     
    printf("Quantos litros queres colocar?\n");
    scanf("%f", &litros);
    printf("Qual é o combustivel que queres colocar? 1-gasolina95, 2-gasoleo, 3-gasolina98\n");
    scanf("%d" , &opcao);
    printf("Qual o dia do abastecimento?\n");
    scanf("%d" , &dia);
    
    if(dia>0 && dia<31) {

        if(opcao==1 && dia < 15) {
            valor=gasolina95 * litros;
            printf("O custo é %f", valor);
        }
        if(opcao==1 && dia>=15) {
            gasolina95=1.798+0.021;
            valor=gasolina95 * litros;
            printf("O custo é %f", valor);
        }
        if(opcao==2 && dia<15) {
            valor=gasoleo * litros;
            printf("O custo é %f", valor);
        }
        if(opcao==2 && dia>=15) {
            gasoleo=1.711+0.023;
            valor=gasoleo * litros;
            printf("O custo é %f", valor);
        }
        if(opcao==3 && dia>=15) {
            valor=gasolina98 * litros;
            printf("O custo é %f", valor);
        }    
    }
    else{
        printf("Insira uma data válida.");

    }
    
return 0;
}