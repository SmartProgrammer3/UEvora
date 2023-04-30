#include <stdio.h>

int tamanho(char s[]){
    int digito = 0;
    int countTamanho = 0;
    while(s[digito] != '\0')
    {
        countTamanho++;
        digito++;
    }
    return countTamanho;
}

int main(){
    char s[] = "Ee";
    printf("%d\n", tamanho(s));
    return 0;
}