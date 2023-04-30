#include <stdio.h>
#include <stdlib.h>


int main(){
    int x=0;
    int saldo;


while (x != 4)
{
    printf(" ATM \n");
    printf("\n");
    printf(" Conta\n");
    printf("\n");
    printf("1 - Depositar");
    printf("\n");
    printf("2- Levantar.");
    printf("\n");
    printf("3- Ver saldo.");
    printf("\n");
    printf("4- Sair.");
    printf("\n");
    printf("");
    scanf("%d", &x);

    switch (x)
    {
    case 1:
        saldo= depositar(saldo);
        break;
    case 2:
        saldo= levantar(saldo);
        break;
    case 3:
        saldo=versaldo(saldo);
        break;
    default:
        break;
    }
int depositar(int saldo) {


}    
    

}








}