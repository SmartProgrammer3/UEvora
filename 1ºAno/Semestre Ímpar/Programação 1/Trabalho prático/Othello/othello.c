#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "othello_func.h"
#include <time.h>
#include <stdbool.h>

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Declaração das variáveis:
    tabuleiro[8][8]: Tabuleiro  8 linhas x 8 colunas. É o mapa do jogo. A cada jogada aparece com a nova peça e as respetivas flanqueadas se possível.
    cor: A cor é a variável onde é possivel aleatoriamente determinar quem é que começar e quais são as respetivas peças de cada jogador.
    jogadasPossiveis: É a variável que permite ver se há jogadas possiveis. É o que vai determinar quem joga e quando é que o jogo acaba.
    pecaUtilizador: Peças do utilizador.
    pecaComputador: Peças do computador.
    jogadorAJogar: É o jogador que está a jogar. Por exemplo, o computador começa, logo é o computador que está a jogar. Depois o utilizador, etc.
    linhaJogada e colunaJogada: É o input por parte do utilizador, posição da peça que o utilizador quer colocar.
    movimentoValidado: É a variável que permite validar a jogada. Se a posição onde o utilizador vai jogar corresponde com as regras implementadas, então o movimento é validado. (True -> 1)
    pecaJogada: Peça que foi jogada.
    jogada: String, melhor posição para ser jogada pelo computador (flanqueia mais peças).
    totaisMatrizPorJogador: Explicado no ficheiro othello_func
    numeroJogadas e numPecas: Representam as variáveis das jogadas de cada jogador e das suas respetivas peças no tabuleiro.
    linhasFicheiro e colunasFicheiro: representam as jogadas que estão inseridas no ficheiro. A linha primeiro e ao lado a coluna (Ex: 4C) É um array com 64 tamanho porque só existem 60 posições livres 
                                    no tabuleiro, logo mais que isso o jogo ja teria acabado se ultrapassar as 60.
    contadorNumeroJogada: variável de controlo para o número da jogada
    o argc: representa o valor inteiro que indica a quantidade de argumentos que foram passados ao chamar o programa. (ex: ./othello --> argc = 1)
    o argv: é um vetor de char que contém os argumentos, um para cada string passada na linha de comando. 
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int main(int argc, char *argv[]){ 
    char tabuleiro[8][8];  
    int cor;
    char enter=0;
    bool jogadasPossiveis= true;
    char pecaUtilizador;
    char pecaComputador;
    char jogadorAJogar;
    int linhaJogada;
    char colunaJogada;
    bool movimentoValidado = true;
    char pecaJogada;
    char jogada[3] = {1,'A',0};
    int totaisMatrizPorJogador[5] = {1,1,1,1,0};
    int numeroJogadasComputador = 0;
    int numeroJogadasJogador = 0;
    int numPecasTabuleiroComputador = 2;
    int numPecasTabuleiroJogador = 2;
    int linhasFicheiro[64];
    char colunasFicheiro[64];
    int contadorNumeroJogada = 0;

    for (int iii = 0; iii < 64; iii++) // Inicialização do array com valores por omissão
    {
        linhasFicheiro[iii] = 0;
        colunasFicheiro[iii] = 'W';
    }

    if (argc > 2) // Se o argc for maior que 2 então não é possivel abrir os ficheiros.
    {
        printf("Não conseguimos abrir o ficheiro do teu agrado.");
        return 1;
    } else if(argc == 2){ // Se argc = 2 vamos abrir o ficheiro das jogadas (./othello jogadas.txt) e ler as jogadas que lá estão.
        FILE *f = fopen(argv[1], "r");
        if (f == NULL)
        {
            printf("Não conseguimos abrir o teu ficheiro %s.", argv[1]);
            return 1;
        } else{
            int contadorFicheiro = 0;

            while (!(feof(f))) // Quando for o fim do ficheiro acabou. o feof retorna 1 quando o ficheiro acaba
            {          
                fscanf(f, "%d%c", &linhasFicheiro[contadorFicheiro], &colunasFicheiro[contadorFicheiro]);
                contadorFicheiro++;
            }
        }
        fclose(f); // Fecha ficheiro.
    } 

    printf("\nX------------------------------------------------------X");
    printf("\n\n\tBem-vindo ao jogo do Othello.\n\n");
    printf("X------------------------------------------------------X");
    printf("\n\nPressione a tecla Enter para começar o seu jogo.\n");
    scanf("%c", &enter);
    printf("Legenda do jogo:\n");
    printf("x ------> Peças Pretas;\n");
    printf("o ------> Peças Brancas;\n");
    printf(". ------> Posições Livres.\n\n\n");

    tabuleiroInicial(tabuleiro);

    impressaoTabuleiro(tabuleiro, numeroJogadasComputador, numeroJogadasJogador, numPecasTabuleiroComputador, numPecasTabuleiroJogador, 0, 0);

    cor = escolhaPecasAleatoriamente();

    if (cor == 0) // A cor é igual à escolhaAleatoria da função escolhaPecasAleatoriamente. Se for igual a 0 pelo random da função, então:
    {
        pecaUtilizador = 'x'; // A peça do utilizador é a preta
        pecaComputador = 'o'; // A peça do computador é a branca
        jogadorAJogar = 'j'; //[j] ==> jogador

        printf("Tu começas! Boa sorte!\n\n");

    } else{ // Se a cor for igual a 1 (escolhaAleatoria igual a 1) do random da função, então:
        pecaUtilizador = 'o'; // A peça do utilizador é a branca
        pecaComputador = 'x'; // A peça do computador é a preta
        jogadorAJogar = 'c'; //[c] ==> computador

        printf("O computador começa! Prepara-te!");
    } 

    while (jogadasPossiveis)
    {

        if (jogadorAJogar == 'j')
        {   
            if (!movimentoValidado)
            {
                printf("A sua jogada não é válida.\n Tente novamente:");
                scanf( "%d%c", &linhaJogada, &colunaJogada );
            } else{
                if (linhasFicheiro[contadorNumeroJogada] != '\0') // Se a linha que vem do ficheiro for diferente que 0, então a linha e coluna que vêm do ficheiro terão a 
                {                                                   // posição do ficheiro.
                    linhaJogada = linhasFicheiro[contadorNumeroJogada];
                    colunaJogada = colunasFicheiro[contadorNumeroJogada];
                    numeroJogadasJogador += 1;
                } else{
                    printf( "Digita as coordenadas da posição do teu agrado.\n" );
                    numeroJogadasJogador += 1;
                    scanf( "%d%c", &linhaJogada, &colunaJogada );
                }
            }
        } else {
            if (linhasFicheiro[contadorNumeroJogada] != '\0') // Se a linha que vem do ficheiro for diferente que 0, então a linha e coluna que vêm do ficheiro terão a 
            {                                                   // posição do ficheiro.
                linhaJogada = linhasFicheiro[contadorNumeroJogada];
                colunaJogada = colunasFicheiro[contadorNumeroJogada];
                numeroJogadasComputador += 1;
            } else{
                if (numeroJogadasComputador == 0 && numeroJogadasJogador == 0) // Quando é o inicio do jogo, ou seja, as jogadas de ambos os jogadores são nulas,
                {                                                              // e se for o computador a começar a jogar, então vai haver um random para as 4 posicões em que se pode começar a jogar (4C,3D,5F,6E)
                    int linhaParaRandom[4]; 
                    linhaParaRandom[0] = 3;
                    linhaParaRandom[1] = 4;
                    linhaParaRandom[2] = 5;
                    linhaParaRandom[3] = 6;
                    char colunaParaRandom[4];
                    colunaParaRandom[0] = 'D';
                    colunaParaRandom[1] = 'C';
                    colunaParaRandom[2] = 'F';
                    colunaParaRandom[3] = 'E';

                    int opcaoPrimeiraJogadaComputador = 1 + (rand() % 4); // Random entre 1,2,3,4 (1-4)
                    opcaoPrimeiraJogadaComputador = opcaoPrimeiraJogadaComputador - 1;
                    linhaJogada  = linhaParaRandom[opcaoPrimeiraJogadaComputador];   
                    colunaJogada = colunaParaRandom[opcaoPrimeiraJogadaComputador];
                } else {
                    const char* jogadaComputador = selecionaPosicaoComputador(tabuleiro, pecaUtilizador, pecaComputador, jogada);
                    linhaJogada =  atoi(&jogadaComputador[0]);
                    colunaJogada = jogadaComputador[1];
                    numeroJogadasComputador += 1;
                }            
            } 
        }
        movimentoValidado=validarMovimentos(tabuleiro, linhaJogada, colunaJogada, pecaUtilizador, pecaComputador, jogadorAJogar, true); // Vai ver se a posição pode ser aí jogada

        if (movimentoValidado)
        {
            if (jogadorAJogar == 'j')
            {
                pecaJogada = pecaUtilizador;
            } else {
                pecaJogada = pecaComputador;
            }

            int col = colunaJogada - 'A';
            tabuleiro[linhaJogada-1][col] = pecaJogada; // Se o movimento for válido, então a posição onde foi jogada fica com a sua peça do jogador que jogou.
        }

        if (jogadasPossiveis)
        {
            char jogadorQueIraJogar;
            if (jogadorAJogar == 'j')
            {                                 // Se o jogador que jogou foi o utilizador o próximo será o computador.
                jogadorQueIraJogar = 'c';       // A criação disto serve para verificar se é possivel o proximo jogador que vai jogar tem posições possiveis senão a sua vez é passada para o que acabou de jogar.
            } else {
                jogadorQueIraJogar = 'j';
            }
            
            const int* vetorTotalMatriz = validarPosicoesVazias(tabuleiro, jogadorQueIraJogar, pecaUtilizador, pecaComputador, totaisMatrizPorJogador, jogada);

            if (vetorTotalMatriz[2] > 0)
            {
                jogadasPossiveis = true;
            } else {
                jogadasPossiveis = false;
            }

            numPecasTabuleiroComputador = vetorTotalMatriz[1];
            numPecasTabuleiroJogador = vetorTotalMatriz[0];

            if (movimentoValidado)
            {
                impressaoTabuleiro(tabuleiro, numeroJogadasComputador, numeroJogadasJogador,numPecasTabuleiroComputador,numPecasTabuleiroJogador, linhaJogada, colunaJogada);
                
                if (vetorTotalMatriz[3] == -1 && vetorTotalMatriz[4] == -1) // Nao existe mais jogadas
                {
                    printf("\nO jogo terminou. Ambos os jogadores não têm mais jogadas.");
                    if (numPecasTabuleiroJogador > numPecasTabuleiroComputador)
                    {
                        printf("\n************************************");
                        printf("\n*  Parabéns! Tu ganhas-te o jogo.  *");
                        printf("\n************************************\n");
                    }
                    else if (numPecasTabuleiroJogador < numPecasTabuleiroComputador)
                    {
                        printf("\n********************************");
                        printf("\n*  Azar! Tu perdes-te o jogo.  *");
                        printf("\n********************************\n");
                    } else {
                        printf("\n**********************************************");
                        printf("\n*  Empatas-te o jogo contra o teu oponente.  *");
                        printf("\n**********************************************\n");
                    }             
                } else {    // Validar so para o que vai jogar
                    if (jogadorQueIraJogar == 'j')
                    {
                        if (vetorTotalMatriz[3] == -1)
                        {
                            if (numPecasTabuleiroJogador == 0)
                            {
                                printf("\n********************************");
                                printf("\n*  Azar! Tu perdes-te o jogo.  *");
                                printf("\n********************************\n");                               
                            } else {
                                printf("Tu não tens mais jogadas! A tua vez vai ser passada para o computador");     
                            }
                            jogadorAJogar = 'j'; // Simular que foi o jogador a jogar. (Jogada que foi suspendida devido a nao podes jogar)          
                        } 
                    } else {
                        if (vetorTotalMatriz[4] == -1)
                        {
                            if (numPecasTabuleiroComputador == 0)
                            {
                                printf("\n************************************");
                                printf("\n*  Parabéns! Tu ganhas-te o jogo.  *");
                                printf("\n************************************\n");                            
                            } else {
                                printf("O computador não pode jogar. A vez de jogar é tua.");     
                            }
                            jogadorAJogar = 'c';
                        }
                    }
                }               
                      
                if (jogadorAJogar == 'j')
                {
                    pecaJogada = pecaUtilizador;
                    jogadorAJogar = 'c';
                } else {
                    pecaJogada = pecaComputador;
                    jogadorAJogar = 'j';
                }
            } 
        }        
        contadorNumeroJogada++; // Após cada ciclo a jogada fica +1
    }
}