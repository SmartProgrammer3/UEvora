#include <stdio.h>

void sub_cadeias(char s[]){
    int i;
    int t = 4;
    int count;
    for(count = 0; count < 4; count++){
        for (i = 0; i < t; i++)
        {
            printf("%c", s[i]);
        }
        printf("\n");
        t--;
    }
}

int main(){
    char s[5] = {"Aula"};
    sub_cadeias(s);
}




























/*
void sub_cadeia(char string[]){
    int contador=0;
    int variavelTamanho=sizeof(string)-1;
    char caracteresAEscrever;
    int contadorWhile;
    int contador2;

    for ( contador = 0; contador < sizeof(string)-1 ; contador++)
    {
        caracteresAEscrever="";
        contadorWhile=sizeof(string)-contador-1;  // Retirar letras primeiro com o contador
        /*while (string[contadorWhile] != '\0')
        {
            caracteresAEscrever=string[variavelTamanho-contadorWhile];
        }

            caracteresAEscrever=strcpy(string, 0, contadorWhile);

        printf("%c\n", caracteresAEscrever);
    } 
}

int main(){
    char string[]="Aula";

    /*fnFOR(string);*/
   // sub_cadeia(string);

//}
