#include <stdio.h>
#include <math.h>

int calculoMedia(int notasAlunos[15], int notaMinima, int notaMaxima){
    printf("nota minima %d %d", notaMinima, notaMaxima);
    int soma=0;
    int contador;
    int somaMedia; //Soma das 13 notas elegiveis para a média
    float Media;
    
    for ( contador = 0; contador < 15; contador++)
    {
        soma += notasAlunos[contador];
    }
    
    somaMedia = soma - (notaMinima + notaMaxima);
    Media= somaMedia/13;

    return round(Media);
}

int verificarNotaMaxima(int notasAlunos[15]){
    int contador;
    int notaMaxima;

    for ( contador = 0; contador < 15; contador++)
    {
        if (contador==0)
        {
            notaMaxima = notasAlunos[contador];
        }   else {
                if (notasAlunos[contador] > notaMaxima)
                {
                    notaMaxima = notasAlunos[contador];
                }
        }
    }
    return notaMaxima;
}

int verificarNotaMinima(int notasAlunos[15]){
    int contador;
    int notaMinima;

    for ( contador = 0; contador < 15; contador++)
    {
        if (contador==0)
        {
            notaMinima=notasAlunos[contador];
        }   else{
            if (notasAlunos[contador] < notaMinima)
            {
                notaMinima=notasAlunos[contador];
            }   
        }
    }
    return notaMinima;
}

int notasDasSemanas(){
    int semana;
    int notasAlunos[15];

    for ( semana = 0; semana < 15; semana++)
    {
        printf("Introduza a sua nota do trabalho da semana %d : ", semana+1);
        scanf("%d", &notasAlunos[semana]);
    }
    return notasAlunos[15];
}

int main(){
    
    printf("\nNas questões seguintes, introduza a nota do seu trabalho (0-20), caso não o tenha realizado, introduza -1.\n");

    int mainNotasAlunos[15];
    mainNotasAlunos[15]=notasDasSemanas();

    int notaMinima=verificarNotaMinima(mainNotasAlunos);
    int notaMaxima=verificarNotaMaxima(mainNotasAlunos);
    printf("nota minima %d %d", notaMinima, notaMaxima);

    printf("\nA média do seu trabalho ao longo destas 15 semanas, retirando a nota máxima e mínima é : %d", calculoMedia(notaMinima, notaMaxima));

    return 0;
}