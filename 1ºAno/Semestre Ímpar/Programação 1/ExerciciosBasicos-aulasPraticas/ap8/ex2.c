#include <stdio.h>

int contaelementos(int vetor1[], int vetor2[], int tamanho1, int tamanho2){ //tamanho1=5 tamanho2=2
    int elementoscomum=0;
    int x;                          // x=elementos vetor 1/ ou seja x é 1,2,3,4,5 (No caso de b,c e b,d)
    int y;                          //y=elementos vetor 2/ ou seja y é 2,4 em b,c e 7,8  em b,d
    for ( x = 0; x < tamanho1 ; x++) // Começamos com o elemento x=0, vai verificar se é igual a y=0, por ex: se no caso de b,c, 1=2
    {                                   // Se for, elementos em comum +1, senao for passa para a proxima verificação
        for ( y = 0; y < tamanho2 ; y++)
        {
            if (vetor1[x]==vetor2[y])   //Nos exemplos para verificar, seja em b,c: se x=2 e y=1 vetor1(b)[2]=2 && vetor2(c)[1]=2
            {                           // Ou seja tereá um elemento em comum, sendo assim, elementoscomum +1.
                elementoscomum++;
            }
        }       
    }
    return elementoscomum;
}
int main(){
    int b[5]={1,2,3,4,5};
    int c[2]={2,4};
    int d[2]={7,8};

    printf("Os elementos em comum do 1º exemplo é: %d\n", contaelementos(b,c,5,2));
    printf("Os elementos em comum do 2º exemplo é: %d\n", contaelementos(b,d,5,2));

    return 0;
}