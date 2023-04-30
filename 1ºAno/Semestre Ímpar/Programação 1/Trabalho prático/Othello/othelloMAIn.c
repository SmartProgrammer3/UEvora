#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "othello_func.h"
#include <time.h>
#include <stdbool.h>

int main(){
    /*---------------------------------------------------------------------------------------
    Declaração das variáveis:
    tabuleiro[8][8]: Tabuleiro inicial. 8 linhas x 8 colunas
    peca: Peças pretas ou brancas. 
    pecaUtilizador: Peças do utilizador.
    ---------------------------------------------------------------------------------------*/
    char tabuleiro[8][8];  
    char peca;
    int pecaUtilizador;
    int pecaComputador;
    char enter=0;
    int linhaUtilizador;
    char colunaUtilizador;
    bool posicoesLivres= true;
    int deltaLinha;
    char deltaColuna;

    printf("\nBem-vindo ao jogo do Othello.\n\n");
    printf("\nPressione a tecla Enter para começar o seu jogo.\n");
    scanf("%c", &enter);
    printf("Legenda do jogo:\n");
    printf("x ------> Peças Pretas;\n");
    printf("o ------> Peças Brancas;\n");
    printf(". ------> Posições Livres.\n\n\n");

    tabuleiroInicial(tabuleiro);

    impressaoTabuleiro(tabuleiro);

    pecaUtilizador = escolhaPecasAleatoriamente(pecaUtilizador);

    while (posicoesLivres)
    {
        posicoesLivres = validarPosicoesVazias(tabuleiro);
        // printf("%d", posicoesLivres);
        posicoesLivres = false; // RETIRAR
    }

    funcaoJogadorAComecar (tabuleiro, pecaUtilizador);
     
    //validarPosicoesVazias(tabuleiro);
}