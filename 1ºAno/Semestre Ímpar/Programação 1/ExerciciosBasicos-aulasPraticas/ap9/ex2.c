#include <stdio.h>
#include <string.h>

void letrasfor(int tamanhoString, char string[tamanhoString]){
    int contagem=0;
    for ( contagem = 0; string[contagem] != "\0"; contagem++)
    {
         printf("%c\n", string[contagem]);
    }  
}

int main(){
    int tamanhoString;

    printf("Qual o tamanho da sua string? ");
    scanf("%d", &tamanhoString);

    char string[tamanhoString];

    printf("Qual é a tua string? ");
    scanf("%s", &string[tamanhoString]);

    letrasfor(tamanhoString, string);
    return 0;
}