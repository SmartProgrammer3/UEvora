#include <stdio.h>


int matriz_utilizador(int tamanhomatriz, int matrizCliente[tamanhomatriz][tamanhomatriz]){
    int linha, coluna;
    int matrizIdentidade[tamanhomatriz][tamanhomatriz];
    int matrizesiguais=1;

    for ( linha = 0; linha < tamanhomatriz; linha++)
    {
 //       printf("Informe os elementos da %daª linha:\n", (linha+1)); 
        for ( coluna = 0; coluna < tamanhomatriz; coluna++)
        {
        printf ("\nEntre com os elementos da linha[%d] e coluna[%d]= ",linha+1,coluna+1);
        scanf ("%d",&matrizCliente[linha][coluna]);
        }
    }

    for ( linha = 0; linha < tamanhomatriz; linha++)
    {
        for ( coluna = 0; coluna < tamanhomatriz; coluna++)
        {
            if (linha==coluna)
            {
                matrizIdentidade[linha][coluna]=1;
            }   else{
                matrizIdentidade[linha][coluna]=0;
            }  
        //   printf("%d", matrizIdentidade[linha][coluna]);
        }   
    }

    for ( linha = 0; linha < tamanhomatriz; linha++)
    {                                                           //Tem que percorrer todas as linhas e colunas para verificar todas as posições
        for ( coluna = 0; coluna < tamanhomatriz; coluna++)
        {
            if (matrizCliente[linha][coluna] != matrizIdentidade[linha][coluna])
            {
                matrizesiguais=0;
            }      
        }
    }
    if (matrizesiguais==0)
    {
        printf("A matriz introduzida não é uma matriz identidade.");
    }  else {
        printf("A matriz introduzida é uma matriz identidade.");
    }    
    
}    

void main () {
    int tamanhomatriz; 

    printf("Qual é o tamanho pretendido para a matriz? ");
    scanf("%d", &tamanhomatriz);   
    int m[tamanhomatriz][tamanhomatriz];

    matriz_utilizador(tamanhomatriz, m);
}
