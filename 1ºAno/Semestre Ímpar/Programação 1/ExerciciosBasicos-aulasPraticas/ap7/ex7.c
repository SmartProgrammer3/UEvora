#include <stdio.h>

int pascal(int linha, int coluna){
    if (coluna==0 || coluna==linha)
    {
        return 1;
    }
    else{                                                                           // Também posso dar valor a pascal(linha-1,coluna-1)=x
        return pascal(linha-1,coluna-1)+pascal(linha-1,coluna);                     // e pascal(linha-1,coluna)=y (declaro variaveis e dps somo x+y)
    }
    
}



int VerificarPositivo1(int linha){
    if (linha<0)
    {
        printf("Digite uma linha com o valor positivo. Tente novamente:");
        scanf("%d", &linha);
        return VerificarPositivo1(linha);
    }
    else{
        return linha;
    }
}

int VerificarPositivo2(int coluna){
    if (coluna<0)
    {
        printf("Digite uma coluna com o valor positivo. Tente novamente:");
        scanf("%d", &coluna);
        return VerificarPositivo2(coluna);
    }
    else{
        return coluna;
    }
}

int main(){
    int linha;
    int coluna;

    printf("Qual é a linha que queres?\n");
    scanf("%d", &linha);
    linha=VerificarPositivo1(linha);

    printf("Qual é a coluna que queres?\n");
    scanf("%d", &coluna);
    coluna=VerificarPositivo2(coluna);

    printf("O %d elemento da linha %d é: %d", coluna, linha, pascal(linha,coluna));
    return 0;
}