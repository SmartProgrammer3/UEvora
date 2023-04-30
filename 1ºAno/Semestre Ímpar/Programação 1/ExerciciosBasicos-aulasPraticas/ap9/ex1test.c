#include <stdio.h>
#include <string.h>

int main() {
    int tamanhoString;
    int contador=0;
    char caracter;
    
    printf("Digite o tamanho da sua string: ");
    scanf("%d", &tamanhoString);

    char string[tamanhoString];  //cada tamanho equivale a um byte
    /*printf("A string tem %ld bytes\n", sizeof(string));*/

    printf("Digite a sua string: ");
    scanf("%s", &string[tamanhoString]);

    for ( contador = 0; string[contador] != '\0' ; contador++)
    {   
        caracter=string[contador];
        printf("%c\n", caracter);
    }
    

    /*
    strcpy(string, "abcde");   // É a função strlen() que conta todos caracteres até achar o terminador nulo. \0
    printf("\nA string, abcde, conta com %ld caracteres\n", strlen(string));
    strcpy(string, "texto longo");
    printf("\nA string, texto longo, conta com %ld caracteres\n", strlen(string));
    strcpy(string, "s");
    printf("\nA string, s, conta com %ld caracteres\n", strlen(string));
    */
}