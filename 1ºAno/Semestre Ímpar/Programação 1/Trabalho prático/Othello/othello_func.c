#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "othello_func.h"
#include <time.h>
#include <stdbool.h>

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função tabuleiroInicial permite inicializar o tabuleiro de jogo. As peças iniciais e os pontos nas outras posições.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
void tabuleiroInicial (char tabuleiro[8][8]){
    int contadorColuna = 0;
    int contadorLinha = 0;

    for ( contadorLinha = 0; contadorLinha < 8; contadorLinha++ )
    {
        for ( contadorColuna = 0; contadorColuna < 8; contadorColuna++ )
        {
            tabuleiro[contadorLinha][contadorColuna] = '.'; // Pontos nas 60 posições onde não tem as 4 peças iniciais
        }
    }
    
    tabuleiro[3][3] = 'o';
    tabuleiro[3][4] = 'x';  // Posições/Peças iniciais no tabuleiro
    tabuleiro[4][3] = 'x';
    tabuleiro[4][4] = 'o';
}

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função impressaoTabuleiro permite imprimir o tabuleiro a cada movimento com as novas peças e as peças flanquedas, também permite mostrar o número de cada jogada do computador e utilizador,
as suas respetivas peças e as posições jogadas de cada um. Em suma, esta função imprime o mapa em cada jogada.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
void impressaoTabuleiro (char tabuleiro[8][8], int numeroJogadasComputador, int numeroJogadasJogador, int numPecasTabuleiroComputador, int numPecasTabuleiroJogador, int linhaJogada, char colunaJogada){
    int linha = 0;
    int coluna = 0;
    int numJogada = numeroJogadasComputador + numeroJogadasJogador; // Número total da jogada é a soma das jogadas do computador e utilizador.
    
    printf("\n\nJogada número     : %d", numJogada);
    printf("\nJogadas computador: %d", numeroJogadasComputador);
    printf("\nJogadas jogador   : %d", numeroJogadasJogador);
    printf("\n\n\n\t  A B C D E F G H\n"); 

    for (linha = 0; linha < 8; linha++)
    {
        printf("\t%d " , linha + 1 );
        for (coluna = 0; coluna < 8; coluna++)
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

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função escolhaPecasAleatoriamente permite através da função random decidir quem é que começa o jogo. Tanto pode começar o utilizador como o computador.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int escolhaPecasAleatoriamente (){
    char peca;
	int escolhaAleatoria = 0;
    srand(time(0)); // srand time0 vai buscar os segundos de 0 a 59 e depois o resto de 2 pode ser 0 ou 1.
	escolhaAleatoria = rand()%2;

        if(escolhaAleatoria == 0){
            peca = 'x';
        } else{
            peca = 'o';
        }

    printf("\n\nAs tuas peças de jogo são: %c\n", peca);

	return escolhaAleatoria;
}

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função validarMovimentos permite  analisar se a jogada em questão do utilizador para ver se é possivel jogar ou não de acordo com as regras implementadas.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
// jogadorAJogar -> [c]==>computador; [j]==>utilizador
// A variável bool Flanquear serve como "controlo", ou seja, se flanquear for true então as peças ao redor podem ser flanqueadas, caso contrário se for false, não vai entrar no ciclo while para 
// flanquear as mesmas. O objetivo desta variável é caso seja para analisar se há jogadas possíveis para o utilizador e como nós não queremos que ao analisar se há jogadas possiveis, o computador flanqueie
// essas posições então nós criámos esta variável.
bool validarMovimentos(char tabuleiro[8][8], int linhaJogada, char colunaJogada, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear){
    bool jogadaValida = true; // Inicializada a true e caso não se enquadre nas regras estipuladas fica com o valor false.
    bool pecasAoLado = false; // Inicializada a false, e permite validar se existe uma peça do oponente ao lado.
    int col = colunaJogada - 'A'; // Como a coluna vem em caracter, subtraimos o caracter passado pelo caracter A e obtemos a coluna jogada. (Exemplo A tem valor 65 ASCII. A-A = 65-65 -> col = 0)

    if ( linhaJogada < 1 || linhaJogada > 8) // Verifica se a linha está dentro do tabuleiro.
    {
        jogadaValida = false;
    }  
    else if ( col < 0 || col > 7) // Verifica se a coluna está dentro do tabuleiro.
    {
        jogadaValida = false;
    }
    if (jogadaValida)   // Verificar se a posicao esta preenchida, ou seja, se tem um ponto na posição onde vai jogar, caso contrário a jogada é inválida
    {
        if (tabuleiro[linhaJogada-1][col] != '.') // -1 --> A matriz começa sempre no zero
        {
            jogadaValida = false;
        }
    }
    
    if (jogadaValida) // Se a jogadaValida atras mantiver true então:
    {
        // Validar as pecas ao lado para as pecas do jogador (8 direções)
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 0, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção direita
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 0, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }        
        }
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 0, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção esquerda
        {    
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 0, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, 0, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção superior
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, 0, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, 0, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção inferior
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, 0, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção inferior esquerda
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, 1, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção inferior direita
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, 1, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, 1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção superior direita
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, 1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
        if (VerificarSePodeVirar(tabuleiro, linhaJogada-1, col, -1, -1, pecaUtilizador, pecaComputador, jogadorAJogar)) // Direção superior esquerda
        {
            if (verificaSePodeFlanquear(tabuleiro, linhaJogada-1, col, -1, -1, pecaUtilizador, pecaComputador, jogadorAJogar, Flanquear))
            {
                pecasAoLado = true;
            }   
        }    
    }

    if (pecasAoLado) // Se todas as validações atrás forem verdadeiras então a jogada é válida.
    {
        jogadaValida = true;
    }   else {
        jogadaValida = false;
    }
    return jogadaValida;
}

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função verificarSePodeVirar vai verificar se nas oito direções da posição jogada pelo utilizador existe uma ou mais peças do oponente para serem substituidas.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
bool VerificarSePodeVirar(char tabuleiro[8][8], int linhaJogada, int col, int deltaLinha, int deltaColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar){
    int linhaPosterior = 0; 
    int colunaPosterior = 0;
    bool validacaoCorreta=false; // Esta variável vai retornar true se houver pelo menos uma peça do oponente em alguma direção.
    char pecaAValidarOponente;

    if (jogadorAJogar == 'j') // Se for o jogador a jogar.
    {
        pecaAValidarOponente = pecaComputador;
    } else {
        pecaAValidarOponente = pecaUtilizador;
    }

    linhaPosterior = linhaJogada + deltaLinha; // Linha posterior é a linha jogada com o incremento.
    colunaPosterior = col + deltaColuna; // Coluna posterior é a coluna jogada com o incremento.
        
    if ((linhaPosterior >= 0 && linhaPosterior < 8) && (colunaPosterior >= 0 && colunaPosterior <= 7) )
    {
        if (tabuleiro[linhaPosterior][colunaPosterior] == '.') 
        {
            validacaoCorreta=false; // Se tiver ao lado um ponto, então mantém-se false a validação pois não é possivel flanquear ao lado de um ponto.
        }
        if(tabuleiro[linhaPosterior][colunaPosterior] == pecaAValidarOponente)
        {
            validacaoCorreta=true; // Se tiver ao lado de uma peça do oponente então pode vir a ser possível flanquear.
        }           
    }
    return validacaoCorreta;
}

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função verificaSePodeFlanquear permite verificar se é possível flanquear numa determinada direção. Ou seja, se a função anterior for verificada, esta função vai continuar a percorrer a direção 
até encontrar novamente uma igual à sua.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
// incrementoLinha e incrementoColuna vão indicar as posições a validar para determinas se podemos ou não flanquear.
bool verificaSePodeFlanquear(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar, bool Flanquear){
    bool continuar = true; // Variavel para verificar se pode continuar a percorrer essa direção. E muda de estado quando não corresponde a uma regra.
    int linhaPesquisa = linhaJogada;
    int colunaPesquisa = col;
    int totalPecasAFlanquear = 0;
    bool jogadaValida = false;
    char pecaAFlanquearOponente;

    if (jogadorAJogar == 'j') // Se for o jogador a jogar
    {
        pecaAFlanquearOponente = pecaUtilizador; // A sua peça para procurar se pode flanquear o inimigo é igual á sua (utilizador/jogador).
    } else {
        pecaAFlanquearOponente = pecaComputador; 
    }

    while (continuar)
    {
        linhaPesquisa = linhaPesquisa + incrementoLinha; // linhaPesquisa --> Direção da linha que queremos procurar.
        colunaPesquisa = colunaPesquisa + incrementoColuna;// colunaPesquisa --> Direção da coluna que queremos procurar.

        if ((linhaPesquisa >= 0 && linhaPesquisa < 8) && (colunaPesquisa >= 0 && colunaPesquisa < 8) )
        {
            if (tabuleiro[linhaPesquisa][colunaPesquisa] == pecaAFlanquearOponente)
            {
                continuar = false; // Se for igual à sua peça então pode sair.
            }
            else if (tabuleiro[linhaPesquisa][colunaPesquisa] == '.') 
            {
                totalPecasAFlanquear = 0; // Se for igual a um ponto, então sai e não flanqueia uma peça.
                continuar = false;
            } else {
                totalPecasAFlanquear += 1; // Se for igual a uma peça do oponente então soma mais um às peças disponiveis a flanquear.
            } 
        } else {
            if (continuar)  // Nao foi encontrado nenhuma peca a finalizar a direcao da jogada, do jogador que esta a jogar.
            {
                totalPecasAFlanquear = 0; // Sai e retorna 0 peças flanqueadas.
                continuar = false;
            }
        }
    }

    if (totalPecasAFlanquear > 0) // Encontrou uma peca na direcao da jogada. Para flanquear as peças.
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
                    if (Flanquear) // Como serve de teste e de flanqueio, logo e necessario saber se e para flanquear ou nao, ou seja serve para ver se há jogadas disponiveis ou para efetuar uma jogada. Se por para efetuar tem que dar flank, ou seja, tem que ser true.
                    {
                        tabuleiro[linhaPesquisa][colunaPesquisa] = pecaAFlanquearOponente; // Se for para flanquear então as posições avaliadas pelas regras implementadas em cima, passam a ser as peças de quem jogou (são flanqueadas).
                    }
                }
            }
        }
    }
    return jogadaValida;
}


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função calculaPecasAFlanquearJogadaComputador, permite calcular para cada jogada quantas peças pode flanquear. 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int calculaPecasAFlanquearJogadaComputador(char tabuleiro[8][8], int linhaJogada, int col, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador, char jogadorAJogar){
    bool continuar = true;
    int linhaPesquisa = linhaJogada;
    int colunaPesquisa = col;
    int totalPecasAFlanquear = 1; // é a peca do oponente que foi encontrada na funcao que chama esta.
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
                continuar = false; // Se encontrar uma peça sua, então sai e fica só com uma peça que foi flanqueada na função que chama esta.
            } 
            else if (tabuleiro[linhaPesquisa][colunaPesquisa] == '.') 
            {
                totalPecasAFlanquear = 0; // Se encontrar um ponto. Então retorna 0 peças flanqueadas porque não pode flanquear um ponto.
                continuar = false;
            } else {
                totalPecasAFlanquear += 1; // Se encontrar mais uma do oponente a cada ciclo, adiciona +1 nas peças a flanquear.
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

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função casasPossiveisDeFlanquear é chamada na função selecionaPosicaoComputador e permite encontrar a jogada onde de acordo com o incremento da linha e coluna é possível flanquear mais peças do 
inimigo.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
int casasPossiveisDeFlanquear(char tabuleiro[8][8], int linhaAJogar, int colunaAJogar, int incrementoLinha, int incrementoColuna, char pecaUtilizador, char pecaComputador){
    int totalPecasAFlanquear = 0; // Número total de peças que pode flanquear nessa posição.
    bool continuar = true;
    int linhaAAnalisar = linhaAJogar;
    int colunaAAnalisar = colunaAJogar;
    int numeroPecasFlanquear = 0; // Para cada análise de posição, quantas peças ele vai flanquear.
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
            if (continuar)  // Nao foi encontrado nenhuma peca a finalizar a direcao da jogada, do computador que esta a jogar.
            {
                continuar = false; // Então não é possível continuar a pesquisa
            }
        }
        if (totalPecasAFlanquear < numeroPecasFlanquear)
        {
            totalPecasAFlanquear = numeroPecasFlanquear;
        }
        
    }
    return totalPecasAFlanquear;
}


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função selecionaPosicaoComputador procura uma posição válida para o computador jogar, de acordo com as regras implementadas. A função verifica qual é a melhor jogada de acordo com o númerode peças
que pode flanquear do utilizador.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
const char* selecionaPosicaoComputador(char tabuleiro[8][8], char pecaUtilizador, char pecaComputador, char *jogada ){
    int linhaTab = 0;
    int colunaTab = 0;
    char posicaoLinhaAnalisada;
    char posicaoColunaAnalisada = 'Z';
    int totalPecasAVirar = 0; // Variável inicializada a 0 para ser de referência.
    int totalRetornado = 0; // Variável inicializada a 0 e será o retorno das funções anteriores com o máximo de peças que se pode flanquear numa x direção de uma posição válida.
    char colunaTitulos[8] = {'A','B','C','D','E','F','G','H'};

    for ( linhaTab = 0; linhaTab < 8; linhaTab++ ) // for ==> Percorrer tabuleiro para encontrar as posicoes livres de acordo com as regras implementadas.
    {
        for ( colunaTab = 0; colunaTab < 8; colunaTab++ )
        {   
            if (tabuleiro[linhaTab][colunaTab] == '.') // A posição a avaliar tem que ter um ponto.
            {
                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 0, 1, pecaUtilizador, pecaComputador); // Direção direita
                if(totalPecasAVirar < totalRetornado) // Caso o numero de peças a virar seja maior que 0, então fica na variavel quantas peças pode flanquear.
                {
                    totalPecasAVirar = totalRetornado; // Caso se confirmar que pode ser esta posição então o computador guarda a linha e a respetiva coluna
                    posicaoLinhaAnalisada = linhaTab+1; // e envia no array jogada para a main para ser jogada nessa posição.
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 0, -1, pecaUtilizador, pecaComputador); // Direção esquerda
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, 0, pecaUtilizador, pecaComputador);  // Direção inferior
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }
               
                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, 0, pecaUtilizador, pecaComputador); // Direção superior
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, 1, pecaUtilizador, pecaComputador); // Direção inferior direita
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, 1, -1, pecaUtilizador, pecaComputador); // Direção inferior esquerda
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, 1, pecaUtilizador, pecaComputador); // Direção superior direita
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }

                totalRetornado = casasPossiveisDeFlanquear(tabuleiro, linhaTab, colunaTab, -1, -1, pecaUtilizador, pecaComputador); // Direção superior esquerda
                if(totalPecasAVirar < totalRetornado)
                {
                    totalPecasAVirar = totalRetornado;
                    posicaoLinhaAnalisada = linhaTab+1;
                    posicaoColunaAnalisada = colunaTitulos[colunaTab];
                }
            }
        }
    }
    // Nós criámos um array char, para enviar para a main após a verificação de todas as jogadas, o programa analisar qual é a melhor jogada do computador e vai enviar como string, ou seja, primeiro
    // a linha como inteiro e depois a coluna como char e retorna para a main essa jogada.
    jogada[0] = posicaoLinhaAnalisada + '0'; // Maneira de juntar a linha e coluna numa string.
    jogada[1] = posicaoColunaAnalisada;
    return jogada;
}



/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
A função validarPosicoesVazias para verificar quando é que acaba o jogo, ou seja, verifica se existem posições livres (pontos), senão existirem mais pontos então o jogo acaba. Permite verificar se
o utilizador tem jogadas disponiveis, se tiver, continua, senão tiver passa a vez para o computador e vice-versa. Se ambos não tiverem mais jogadas disponíveis, então o jogo acaba.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
const int* validarPosicoesVazias(char tabuleiro[8][8], char jogadorAJogar, char pecaUtilizador, char pecaComputador, int *totaisMatrizPorJogador, char *jogada){ // Jogador pode ser 'x' ou 'o'
    int contadorLinha = 0;
    int contadorColuna = 0;
    int totPecasJogador = 0;
    int totPecasComputador = 0;
    int totPecasLivres = 0;
    bool ePossivelJogadorJogar = false;
    int ePossivelJogadorJogar2 = -1; // Inicializamos a -1 pois para ver se o utilizador tem jogadas disponiveís ele pode ficar com o valor 0 (false) ou 1 (true) da função onde verifica se a jogada é possível.
    int ePossivelComputadorJogar = -1; // Inicializamos a -1 pois para ver se o computador pode ou não jogar de acordo com a função porque pode ter o valor da linha 0,1,2,3,4,5,6,7.
    char colunaTitulos[8] = {'A','B','C','D','E','F','G','H'};

    for ( contadorLinha = 0; contadorLinha < 8; contadorLinha++)
    {
        for ( contadorColuna = 0; contadorColuna < 8; contadorColuna++)
        {
            if (tabuleiro[contadorLinha][contadorColuna] == '.'){
                totPecasLivres += 1; // Vai contar as posições disponiveis no tabuleiro, ou seja, a cada ponto encontrado adiciona o valor +1 nesta variavel.

                if (!ePossivelJogadorJogar) // Verificar se há jogadas para o utilizador ou não.
                {
                    ePossivelJogadorJogar = validarMovimentos(tabuleiro, contadorLinha+1, colunaTitulos[contadorColuna], pecaUtilizador, pecaComputador, 'j', false);
                }
                if (ePossivelComputadorJogar == -1) // Verificar se há jogadas para o computador ou não.
                {
                    const char* jogadaPossivelParaComputador = selecionaPosicaoComputador(tabuleiro, pecaUtilizador, pecaComputador, jogada);
                    ePossivelComputadorJogar = atoi(&jogadaPossivelParaComputador[0]);
                }
            }    
            if (tabuleiro[contadorLinha][contadorColuna] == pecaUtilizador){
                totPecasJogador += 1; // Contagem de peças do utilizador. Vai percorrer o array e cada peça encontrada que seja do utilizador, então conta +1 para esta variável.
            }    
            if (tabuleiro[contadorLinha][contadorColuna] == pecaComputador){
                totPecasComputador += 1; // Contagem de peças do computador. Vai percorrer o array e cada peça encontrada que seja do computador, então conta +1 para esta variável.
            }        
        }       
    }  

    if (ePossivelJogadorJogar) // Se for possível o jogador jogar, é um bool, se for true, então a variável para retornar para a main terá valor 1, caso contrário tem valor -1.
    {
        ePossivelJogadorJogar2 = 1;
    }
    
// Este array foi criado para passar para a main 5 variaveis importante, ou seja:
    totaisMatrizPorJogador[0] = totPecasJogador; // Esta retorna na posição 0 do array, o número de peças do utilizador.
    totaisMatrizPorJogador[1] = totPecasComputador; // Esta retorna na posição 1 do array, o número de peças do computador.
    totaisMatrizPorJogador[2] = totPecasLivres; // Esta retorna na posição 2 do array, o número de peças livres.
    totaisMatrizPorJogador[3] = ePossivelJogadorJogar2; // Esta retorna na posição 3 do array, se é possível o jogador jogar (retorna 1), senao for possível (retorna -1).
    totaisMatrizPorJogador[4] = ePossivelComputadorJogar; // Esta retorna na posição 4 do array,  se é possível o computador jogar (retorna -1 senão for possível).

    return totaisMatrizPorJogador;
}