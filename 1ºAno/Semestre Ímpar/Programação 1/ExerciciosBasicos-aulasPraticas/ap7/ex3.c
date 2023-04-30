#include <stdio.h>

int soma(int numero){
    if (numero==0)
    {
        return 0;
    }
    else if (numero ==1)
    {
        return 1;
    }
    else{
        return numero+soma(numero-1);
    }
    
    


}

int main(){
    int numero;

    printf("Digite um número inteiro positivo:");
    scanf("%d", &numero);

    printf("A soma é %d", soma(numero));

    return 0;
}