#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "othello_func.h"
#include <time.h>
#include <stdbool.h>

void tabuleiroInicial (char tabuleiro[8][8]){
    int contadorColuna;
    int contadorLinha;

    for ( contadorLinha = 0; contadorLinha < 8; contadorLinha++ )
    {
        for ( contadorColuna = 0; contadorColuna < 8; contadorColuna++ )
        {
            tabuleiro[contadorLinha][contadorColuna] = '.';
        }
    }
    
    tabuleiro[3][3] = 'o';
    tabuleiro[3][4] = 'x';
    tabuleiro[4][3] = 'x';
    tabuleiro[4][4] = 'o';
}

void impressaoTabuleiro (char tabuleiro[8][8]){
    int linha;
    int coluna;

    printf("\n\t  A B C D E F G H\n"); 

    for ( linha = 0; linha < 8; linha++)
    {
        printf("\t%d " , linha + 1 );
        for ( coluna = 0; coluna < 8; coluna++)
        {
            printf("%c ", tabuleiro[linha][coluna]);
        }
        printf("\n");
    }
}

int escolhaPecasAleatoriamente (char peca){
	int escolhaAleatoria;
// srand time0 vai buscar os segundos de 0 a 59 e depois o resto de 2 pode ser 0 ou 1
    srand(time(0));
	escolhaAleatoria = rand()%2;

        if(escolhaAleatoria == 0){
            peca = 'x';
        } else{
            peca = 'o';
        }

    printf("\n\nAs tuas peças de jogo são: %c\n", peca);

	return escolhaAleatoria;
}

void funcaoJogadorAComecar (char tabuleiro[8][8], int pecaUtilizador){
    int linhaUtilizador; int deltaLinha; char deltaColuna;    
    char colunaUtilizador; bool movimentoValidado;
    char utilizador;    
    char computador;

    if ( pecaUtilizador == 0 )
    {
        utilizador = 'x';
        computador = 'o';

        printf("Tu começas! Boa sorte!\n\n");
        printf( "Digita as coordenadas da posição do teu agrado.\n" );
        scanf( "%d%c", &linhaUtilizador, &colunaUtilizador );

    } else{
        utilizador = 'o';
        computador = 'x';

        printf("O computador começa! Prepara-te!");
    }   

    movimentoValidado=validarMovimentos( tabuleiro,  linhaUtilizador,  colunaUtilizador,  deltaLinha,  deltaColuna,  utilizador,  computador );
    printf("%d\n", movimentoValidado);

}

bool verificarSePodeVirar(char tabuleiro[8][8], int linhaUtilizador, char colunaUtilizador, int deltaLinha, char deltaColuna, char pecaUtilizador, char pecaComputador ){
}




bool validarMovimentos(char tabuleiro[8][8], int linhaUtilizador, char colunaUtilizador, int deltaLinha, int deltaColuna, char utilizador, char computador ){
    bool jogadaValida = true;
    int col = colunaUtilizador - 'A';

    impressaoTabuleiro (tabuleiro);

    if ( linhaUtilizador < 1 || linhaUtilizador > 8)
    {
        printf("Posição inválida. Tente novamente:");
        jogadaValida = false;
    }  
    if ( col < 0 || col > 7)
    {
        printf("Posição inválida. Tente novamente:");
        jogadaValida = false;
    }
    if (jogadaValida)   // Verificar se a posicao esta preenchida
    {
        impressaoTabuleiro (tabuleiro);
        if (tabuleiro[linhaUtilizador-1][col] != '.') // -1 --> A matriz começa sempre no zero
        {
            jogadaValida = false;
        }
    }
    // Validar se e possivel flanquear


    return jogadaValida;
}





// Para verificar se existem posições livres
bool validarPosicoesVazias( char tabuleiro[8][8] ){
    int contadorLinha;
    int contadorColuna;
    bool retorno = false;

    for ( contadorLinha = 0; contadorLinha < 8; contadorLinha++)
    {
        for ( contadorColuna = 0; contadorColuna < 8; contadorColuna++)
        {
            if (tabuleiro[contadorLinha][contadorColuna] == '.'){
                retorno = true;
                break;
            }        
        }       
    }    
    return retorno;
}