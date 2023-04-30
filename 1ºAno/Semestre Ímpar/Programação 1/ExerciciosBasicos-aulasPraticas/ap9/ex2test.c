#include <stdio.h>

void fnFOR(char string[]){
    int contador=0;

    for ( contador = 0; contador < sizeof(string)-1 ; contador++)
    {
        printf("%c\n", string[contador]);
    }
}

void fnWhile(char string[]){
    int contador=0;

    while ( string[contador] != '\0')
    {
        printf("%c\n", string[contador]);
        contador++;
    }
}

int main(){
    char string[]="Aula";

    /*fnFOR(string);*/
    fnWhile(string);

}