#include <stdio.h>

int pascal(int linha, int coluna){
    if (coluna==0 || coluna==linha)
    {
        return 1;
    }
    else{                                                                          
        return pascal(linha-1,coluna-1)+pascal(linha-1,coluna);                  
    }
}

int triangulopascal( int n){
    int linha=0;
    while (linha<n)
    {
        int coluna=0;
        float space;

        space=((n-linha))-1;

        while (space>0)
        {
            printf(" ");
            space --;
        }
        while (coluna<=linha)
        {
            printf("%d ", pascal(linha,coluna));
            coluna++;
        }
        printf("\n");
        linha++;
    }
    
}



/*int VerificarPositivo1(int linha){
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
*/
int VerificarPositivo3(int n){
    if (n<0)
    {
        printf("Digite uma linha com o valor positivo. Tente novamente:");
        scanf("%d", &n);
        return VerificarPositivo3(n);
    }
    else{
        return n;
    }
}

int main(){
    /*int linha;
    int coluna;*/
    int linhasutilizador;

   /* printf("Qual é a linha que queres?\n");
    scanf("%d", &linha);
    linha=VerificarPositivo1(linha);

    printf("Qual é a coluna que queres?\n");
    scanf("%d", &coluna);
    coluna=VerificarPositivo2(coluna);*/

    printf("Quantas linhas queres do triangulo de pascal?\n");
    scanf("%d", &linhasutilizador);
    linhasutilizador=VerificarPositivo3(linhasutilizador);

    triangulopascal(linhasutilizador);
    return 0;
}