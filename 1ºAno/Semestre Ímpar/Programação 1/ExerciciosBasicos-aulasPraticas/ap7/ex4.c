#include <stdio.h>

int multiplo(int numero, int iesimo){
    if (iesimo==0)
    {
        return 0;
    }
    else if (iesimo==1)
    {
        return numero;
    }
    else{
        return numero+multiplo(iesimo-1, numero);
    }
}





int main(){
    int numero;
    int iesimo;

    printf("Escolha um número inteiro:");
    scanf("%d", &numero);
    printf("Escolha o i-ésimo múltiplo do número:");
    scanf("%d", &iesimo);

    printf("O i-ésimo múltiplo de n é:%d", multiplo(iesimo, numero));
    return 0;
}