#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>
void tabuleiroInicial(char tabuleiro[8][8]);
// A função seguinte vai mostrar o tabuleiro com as peças brancas e pretas iniciais
void impressaoTabuleiro (char tabuleiro[8][8], int numeroJogadasComputador, int numeroJogadasJogador, int numPecasTabuleiroComputador, int numPecasTabuleiroJogador, int linhaJogada, char colunaJogada);
int escolhaPecasAleatoriamente();

bool validarMovimentos(char tabuleiro[8][8], int linhaJogada, char colunaJogada, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear);
const int* validarPosicoesVazias(char tabuleiro[8][8], char jogadorAJogar, char pecaUtilizador, char pecaComputador, int *totaisMatrizPorJogador, char *jogada );
bool VerificarSePodeVirar(char tabuleiro[8][8], int linhaJogada, int col, int deltaLinha, int deltaColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar);
bool verificaSePodeFlanquear(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear);
const char* selecionaPosicaoComputador(char tabuleiro[8][8], char pecaUtilizador, char pecaComputador, char *jogada );
int calculaPecasAFlanquearJogadaComputador(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar);

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
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int main(int argc, char *argv[]){
    char tabuleiro[8][8];  
    int cor = 0; //++
    char enter = 0;
    bool jogadasPossiveis= true;
    char pecaUtilizador;
    char pecaComputador;
    char jogadorAJogar;
    int linhaJogada = 0; //++
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

    for (int iii = 0; iii < 64; iii++)
    {
        linhasFicheiro[iii] = 0;
        colunasFicheiro[iii] = 'W';
    }
    
    if (argc > 2)
    {
        printf("Não conseguimos abrir o teu ficheiro.");
        return 1;
    } else if(argc == 2){
        FILE *f = fopen(argv[1], "r");
        if (f == NULL)
        {
            printf("Não conseguimos abrir o teu ficheiro %s.", argv[1]);
            return 1;
        } else{
            int contadorFicheiro = 0;

            while (!(feof(f)))
            {          
                fscanf(f, "%d%c", &linhasFicheiro[contadorFicheiro], &colunasFicheiro[contadorFicheiro]);
                contadorFicheiro++;
            }
        }
        fclose(f);
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

    if (cor == 0) // Utilizador
    {
        pecaUtilizador = 'x';
        pecaComputador = 'o';
        jogadorAJogar = 'j'; //[j] ==> jogador

        printf("Tu começas! Boa sorte!\n\n");

    } else{ // Computador
        pecaUtilizador = 'o';
        pecaComputador = 'x';
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
                if (colunasFicheiro[contadorNumeroJogada] != 'W') // Se a linha que vem do ficheiro for diferente que 0, então a linha e coluna que vêm do ficheiro terão a 
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
            if (colunasFicheiro[contadorNumeroJogada] != 'W') // Se a linha que vem do ficheiro for diferente que 0, então a linha e coluna que vêm do ficheiro terão a 
            {                                                   // posição do ficheiro.
                linhaJogada = linhasFicheiro[contadorNumeroJogada];
                colunaJogada = colunasFicheiro[contadorNumeroJogada];
                numeroJogadasComputador += 1;
            } else{
/*                if (numeroJogadasComputador == 0 && numeroJogadasJogador == 0) // Quando é o inicio do jogo, ou seja, as jogadas de ambos os jogadores são nulas,
                {                                                            // e se for o computador a começar a jogar, então vai haver um random para as 4 posicões em que se pode começar a jogar (4C,3D,5F,6E)
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

                    int opcaoPrimeiraJogadaComputador = 1 + (rand() % 4); // Random entre 1,2,3,4
                    opcaoPrimeiraJogadaComputador = opcaoPrimeiraJogadaComputador - 1;
                    linhaJogada  = linhaParaRandom[opcaoPrimeiraJogadaComputador];   
                    colunaJogada = colunaParaRandom[opcaoPrimeiraJogadaComputador];
                } else {*/
                    const char* jogadaComputador = selecionaPosicaoComputador(tabuleiro, pecaUtilizador, pecaComputador, jogada);
                    linhaJogada =  atoi(&jogadaComputador[0]);
                    colunaJogada = jogadaComputador[1];
                    numeroJogadasComputador += 1;
                    /*
                    if (jogadaComputador[1] == 'Z')
                    {                                           // Z vai ser para retirar. Quando tirar o Z, a contabilização da jogada fica.
                        jogadasPossiveis = false;
                    } else {
                        numeroJogadasComputador += 1;
                    }
                    */
                //}            
            } 
        }


        movimentoValidado=validarMovimentos(tabuleiro, linhaJogada, colunaJogada, pecaUtilizador, pecaComputador, jogadorAJogar, true);

        if (movimentoValidado)
        {
            if (jogadorAJogar == 'j')
            {
                pecaJogada = pecaUtilizador;
            } else {
                pecaJogada = pecaComputador;
            }

            int col = colunaJogada - 'A';
            tabuleiro[linhaJogada-1][col] = pecaJogada;
        }

        if (jogadasPossiveis)
        {
            char jogadorQueIraJogar;
            if (jogadorAJogar == 'j')
            {
                jogadorQueIraJogar = 'c';
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
        contadorNumeroJogada++;
    }
}


void tabuleiroInicial (char tabuleiro[8][8]){
    int contadorColuna = 0;
    int contadorLinha = 0;

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


void impressaoTabuleiro (char tabuleiro[8][8], int numeroJogadasComputador, int numeroJogadasJogador, int numPecasTabuleiroComputador, int numPecasTabuleiroJogador, int linhaJogada, char colunaJogada){
    int linha = 0;
    int coluna = 0;
    int numJogada = numeroJogadasComputador + numeroJogadasJogador;
    
    printf("\n\nJogada número     : %d", numJogada);
    printf("\nJogadas computador: %d", numeroJogadasComputador);
    printf("\nJogadas jogador   : %d", numeroJogadasJogador);
    printf("\n\n\n\t  A B C D E F G H\n"); 

    for ( linha = 0; linha < 8; linha++)
    {
        printf("\t%d " , linha + 1 );
        for ( coluna = 0; coluna < 8; coluna++)
        {
            printf("%c ", tabuleiro[linha][coluna]);
        }
        printf("\n");
    }

    printf("\n\nPosicao jogada     : %d%c", linhaJogada, colunaJogada);
    printf("\nPeças do computador: %d", numPecasTabuleiroComputador);
    printf("\nPeças do jogador   : %d\n", numPecasTabuleiroJogador);
    printf("\nX------------------------------------------------------X\n");
}


int escolhaPecasAleatoriamente (){
    char peca;
	int escolhaAleatoria = 0;
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

// jogadorAJogar -> [c]==>computador; [u]==>utilizador
bool validarMovimentos(char tabuleiro[8][8], int linhaJogada, char colunaJogada, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear){
    bool jogadaValida = true;
    bool pecasAoLado = false;
    
    int col = colunaJogada - 'A';

    if ( linhaJogada < 1 || linhaJogada > 8)
    {
        jogadaValida = false;
    }  
    else if ( col < 0 || col > 7)
    {
        jogadaValida = false;
    }
    if (jogadaValida)   // Verificar se a posicao esta preenchida
    {
        if (tabuleiro[linhaJogada-1][col] != '.') // -1 --> A matriz começa sempre no zero
        {
            jogadaValida = false;
        }
    }
    
    if (jogadaValida)
    {
        // Validar pecas ao lado para as pecas do jogador
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 0, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca direita
        {
                if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 0, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
                {
                    pecasAoLado = true;
                }        
        }
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 0, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca esquerda
        {    
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 0, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, 0, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca superior
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, 0, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, 0, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca inferior
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, 0, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca inferior esquerda
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca inferior direita
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca superior esqueda
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Peca superior direita
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
    }

    if (pecasAoLado)
    {
        jogadaValida = true;
    }   else {
        jogadaValida = false;
    }
    return jogadaValida;
}


bool VerificarSePodeVirar(char tabuleiro[8][8], int linhaJogada, int col, int deltaLinha, int deltaColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar){
    int linhaPosterior = 0; 
    int colunaPosterior = 0;
    bool validacaoCorreta=false;
    char pecaAValidarOponente;

    if (jogadorAJogar == 'j')
    {
        pecaAValidarOponente = pecaComputador;
    } else {
        pecaAValidarOponente = pecaUtilizador;
    }
    
    linhaPosterior = linhaJogada + deltaLinha;
    colunaPosterior = col + deltaColuna;
        
    if ((linhaPosterior >= 0 && linhaPosterior < 8) && (colunaPosterior >= 0 && colunaPosterior <= 7) )
    {
        if (tabuleiro[linhaPosterior][colunaPosterior] == '.')
        {
            validacaoCorreta=false;
        }
        if(tabuleiro[linhaPosterior][colunaPosterior] == pecaAValidarOponente)
        {
            validacaoCorreta=true;
        }           
    }
    return validacaoCorreta;
}


// linha Origem e coluna Origem é a posição onde o jogador jogou
// incrementoLinha e incrementoColuna vão indicar as posições a validar para determinas se p0demos ou não flanquear
bool verificaSePodeFlanquear(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear){
    bool continuar = true;
    int linhaPesquisa = linhaJogada;
    int colunaPesquisa = col;
    int totalPecasAFlanquear = 0;
    bool jogadaValida = false;
    char pecaAFlanquearOponente;

    if (jogadorAJogar == 'j')
    {
        pecaAFlanquearOponente = pecaUtilizador;
    } else {
        pecaAFlanquearOponente = pecaComputador;
    }

    while (continuar)
    {
        linhaPesquisa = linhaPesquisa + incrementoLinha;
        colunaPesquisa = colunaPesquisa + incrementoColuna;

        if ((linhaPesquisa >= 0 && linhaPesquisa < 8) && (colunaPesquisa >= 0 && colunaPesquisa < 8) )
        {
            if (tabuleiro[linhaPesquisa][colunaPesquisa] == pecaAFlanquearOponente)
            {
                continuar = false;
            }
            else if (tabuleiro[linhaPesquisa][colunaPesquisa] == '.') 
            {
                totalPecasAFlanquear = 0;
                continuar = false;
            } else {
                totalPecasAFlanquear += 1;
            } 
        } else {
            if (continuar)  // Nao foi encontrado nenhuma peca a finalizar a direcao da jogada, do jogador que esta a jogar 
            {
                totalPecasAFlanquear = 0;
                continuar = false;
            }
        }
    }

    if (totalPecasAFlanquear > 0) // Encontrou uma peca na direcao da jogada
    {
        jogadaValida = true;
        continuar = true;
        linhaPesquisa = linhaJogada;
        colunaPesquisa = col;
        while (continuar)
        {
            linhaPesquisa = linhaPesquisa + incrementoLinha;
            colunaPesquisa = colunaPesquisa + incrementoColuna;

            if ((linhaPesquisa >= 0 && linhaPesquisa < 8) && (colunaPesquisa >= 0 && colunaPesquisa < 8) )
            {
                if (tabuleiro[linhaPesquisa][colunaPesquisa] == pecaAFlanquearOponente)
                {
                    continuar = false;
                } else {
                    if (Flanquear) // Como serve de teste e de flanqueio, logo e necessario saber se e para flanquear ou nao
                    {
                        tabuleiro[linhaPesquisa][colunaPesquisa] = pecaAFlanquearOponente;
                    }
                }
            }
        }
    }
    return jogadaValida;
}


// Funcao temporaria So e chamada quando e detetada uma peca do adversario
int calculaPecasAFlanquearJogadaComputador(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar){
    bool continuar = true;
    int linhaPesquisa = linhaJogada;
    int colunaPesquisa = col;
    int totalPecasAFlanquear = 1; // é a peca do oponente que foi encontrada na funcao que chama esta
    char pecaAFlanquearOponente;

    if (jogadorAJogar == 'j')
    {
        pecaAFlanquearOponente = pecaUtilizador;
    } else {
        pecaAFlanquearOponente = pecaComputador;
    }
    while (continuar)
    {
        linhaPesquisa = linhaPesquisa + incrementoLinha;
        colunaPesquisa = colunaPesquisa + incrementoColuna;

        if ((linhaPesquisa >= 0 && linhaPesquisa < 8) && (colunaPesquisa >= 0 && colunaPesquisa < 8) )
        {
            if (tabuleiro[linhaPesquisa][colunaPesquisa] == pecaAFlanquearOponente)
            {
                continuar = false;
            } 
            else if (tabuleiro[linhaPesquisa][colunaPesquisa] == '.') 
            {
                totalPecasAFlanquear = 0;
                continuar = false;
            } else {
                totalPecasAFlanquear += 1;
            }
        } else {
            if (continuar)  // Nao foi encontrado nenhuma peca a finalizar a direcao da jogada, do jogador que esta a jogar 
            {
                totalPecasAFlanquear = 0;
                continuar = false;
            }
        }
    }
    return totalPecasAFlanquear;
}


int casasPossiveisDeFlanquear(char tabuleiro[8][8], int linhaAJogar, int colunaAJogar, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador){
    int totalPecasAFlanquear = 0;
    bool continuar = true;
    int linhaAAnalisar = linhaAJogar;
    int colunaAAnalisar = colunaAJogar;
    int numeroPecasFlanquear = 0;
    while (continuar)
    {
        linhaAAnalisar = linhaAAnalisar + incrementoLinha;
        colunaAAnalisar = colunaAAnalisar + incrementoColuna;
        
        if ((linhaAAnalisar >= 0 && linhaAAnalisar < 8) && (colunaAAnalisar >= 0 && colunaAAnalisar < 8) )
        {
            if (tabuleiro[linhaAAnalisar][colunaAAnalisar] == pecaUtilizador)
            {
                continuar = true;
                numeroPecasFlanquear = calculaPecasAFlanquearJogadaComputador( tabuleiro, linhaAAnalisar, colunaAAnalisar, incrementoLinha, incrementoColuna, pecaUtilizador, pecaComputador, 'c');                
            } else {
                if (tabuleiro[linhaAAnalisar][colunaAAnalisar] == '.' || tabuleiro[linhaAAnalisar][colunaAAnalisar] == pecaComputador){   
                    continuar = false;
                }
            }
        } else {
            if (continuar)  // Nao foi encontrado nenhuma peca a finalizar a direcao da jogada, do jogador que esta a jogar 
            {
                continuar = false;
            }
        }
        if (totalPecasAFlanquear < numeroPecasFlanquear)
        {
            totalPecasAFlanquear = numeroPecasFlanquear;
        }
        
    }
    return totalPecasAFlanquear;
}


// Procurar posicoes vazias no array (Ler o array)
// for ==> Percorrer tabuleiro para encontrar as posicoes livres
// A posicao livre tem que ter um ponto
const char* selecionaPosicaoComputador(char tabuleiro[8][8], char pecaUtilizador, char pecaComputador, char *jogada ){
    int linhaTab = 0;
    int colunaTab = 0;
    char posicaoLinhaAnalisada;
    char posicaoColunaAnalisada = 'Z';
    int totalPecasAVirar = 0;
    int totalRetornado = 0;
    char colunaTitulos[8] = {'A','B','C','D','E','F','G','H'};

    for ( linhaTab = 0; linhaTab < 8; linhaTab++ )
    {
        for ( colunaTab = 0; colunaTab < 8; colunaTab++ )
        {   
            if (linhaTab == 2 && colunaTab==5){
                if (colunaTab==5){
                totalRetornado = totalRetornado;
                }
            }
            if (tabuleiro[linhaTab][colunaTab] == '.')
            {
                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 0, 1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 0, -1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, 0, pecaUtilizador, pecaComputador); 
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }
               
                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, 0, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, 1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, -1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, 1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, -1, pecaUtilizador, pecaComputador);
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }
            }
        }
    }
    
    jogada[0] = posicaoLinhaAnalisada + '0';
    jogada[1] = posicaoColunaAnalisada;
    return jogada;
}




// Para verificar se existem posições livres
const int* validarPosicoesVazias(char tabuleiro[8][8], char jogadorAJogar, char pecaUtilizador, char pecaComputador, int *totaisMatrizPorJogador, char *jogada){ // Jogador pode ser 'x' ou 'o'
    int contadorLinha = 0;
    int contadorColuna = 0;
    int totPecasJogador = 0;
    int totPecasComputador = 0;
    int totPecasLivres = 0;
    bool ePossivelJogadorJogar = false;
    int ePossivelJogadorJogar2 = -1;
    int ePossivelComputadorJogar = -1;
    char colunaTitulos[8] = {'A','B','C','D','E','F','G','H'};

    for ( contadorLinha = 0; contadorLinha < 8; contadorLinha++)
    {
        for ( contadorColuna = 0; contadorColuna < 8; contadorColuna++)
        {
            if (tabuleiro[contadorLinha][contadorColuna] == '.'){
                totPecasLivres += 1;

                if (!ePossivelJogadorJogar)
                {                                                       // contadorLinha
                    ePossivelJogadorJogar = validarMovimentos(tabuleiro, contadorLinha+1, colunaTitulos[contadorColuna], pecaUtilizador, pecaComputador, 'j', false);
                }
                if (ePossivelComputadorJogar == -1)
                {
                    const char* jogadaPossivelParaComputador = selecionaPosicaoComputador(tabuleiro, pecaUtilizador, pecaComputador, jogada);
                    ePossivelComputadorJogar = atoi(&jogadaPossivelParaComputador[0]);
                }
            }    
            if (tabuleiro[contadorLinha][contadorColuna] == pecaUtilizador){
                totPecasJogador += 1;
            }    
            if (tabuleiro[contadorLinha][contadorColuna] == pecaComputador){
                totPecasComputador += 1;
            }        
        }       
    }  

    if (ePossivelJogadorJogar)
    {
        ePossivelJogadorJogar2 = 1;
    }
    
    totaisMatrizPorJogador[0] = totPecasJogador;
    totaisMatrizPorJogador[1] = totPecasComputador;
    totaisMatrizPorJogador[2] = totPecasLivres;
    totaisMatrizPorJogador[3] = ePossivelJogadorJogar2;
    totaisMatrizPorJogador[4] = ePossivelComputadorJogar;

    return totaisMatrizPorJogador;
}