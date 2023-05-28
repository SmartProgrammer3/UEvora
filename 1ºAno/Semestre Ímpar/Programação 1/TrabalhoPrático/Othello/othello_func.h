#include <string.h>
#include <time.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função tabuleiroInicial permite inicializar o tabuleiro de jogo. As peças iniciais e os pontos nas outras posições.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
void tabuleiroInicial(char tabuleiro[8][8]);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função impressaoTabuleiro permite imprimir o tabuleiro a cada movimento com as novas peças e as peças flanquedas, também permite mostrar o número de cada jogada do computador e utilizador,
as suas respetivas peças e as posições jogadas de cada um. Em suma, esta função imprime o mapa em cada jogada.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
void impressaoTabuleiro (char tabuleiro[8][8], int numeroJogadasComputador, int numeroJogadasJogador, int numPecasTabuleiroComputador, int numPecasTabuleiroJogador, int linhaJogada, char colunaJogada);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função escolhaPecasAleatoriamente permite através da função random decidir quem é que começa o jogo. Tanto pode começar o utilizador como o computador.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int escolhaPecasAleatoriamente();
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função validarMovimentos permite  analisar se a jogada em questão do utilizador para ver se é possivel jogar ou não de acordo com as regras implementadas.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
bool validarMovimentos(char tabuleiro[8][8], int linhaJogada, char colunaJogada, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função verificarSePodeVirar vai verificar se nas oito direções da posição jogada pelo utilizador existe uma ou mais peças do oponente para serem substituidas.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
bool VerificarSePodeVirar(char tabuleiro[8][8], int linhaJogada, int col, int deltaLinha, int deltaColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função verificaSePodeFlanquear permite verificar se é possível flanquear numa determinada direção. Ou seja, se a função anterior for verificada, esta função vai continuar a percorrer a direção 
até encontrar novamente uma igual à sua.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
bool verificaSePodeFlanquear(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função calculaPecasAFlanquearJogadaComputador, permite calcular para cada jogada quantas peças pode flanquear. 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int calculaPecasAFlanquearJogadaComputador(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função casasPossiveisDeFlanquear é chamada na função selecionaPosicaoComputador e permite encontrar a jogada onde de acordo com o incremento da linha e coluna é possível flanquear mais peças do 
inimigo.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int casasPossiveisDeFlanquear(char tabuleiro[8][8], int linhaAJogar, int colunaAJogar, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador);
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função selecionaPosicaoComputador procura uma posição válida para o computador jogar, de acordo com as regras implementadas. A função verifica qual é a melhor jogada de acordo com o númerode peças
que pode flanquear do utilizador.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
const char* selecionaPosicaoComputador(char tabuleiro[8][8], char pecaUtilizador, char pecaComputador, char *jogada );
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função validarPosicoesVazias para verificar quando é que acaba o jogo, ou seja, verifica se existem posições livres (pontos), senão existirem mais pontos então o jogo acaba. Permite verificar se
o utilizador tem jogadas disponiveis, se tiver, continua, senão tiver passa a vez para o computador e vice-versa. Se ambos não tiverem mais jogadas disponíveis, então o jogo acaba.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
const int* validarPosicoesVazias(char tabuleiro[8][8], char jogadorAJogar, char pecaUtilizador, char pecaComputador, int *totaisMatrizPorJogador, char *jogada );
