#include <stdio.h>

void fnForInversa(char string[]){
    int contador=0;
                                //-5 para n escrever caracteres estranhos no terminal
    for ( contador = sizeof(string)-5; contador >= 0 ; contador--)
    {
        printf("%c\n", string[contador]);
    }
}


int main(){
    char string[]="Aula";

    fnForInversa(string);

}