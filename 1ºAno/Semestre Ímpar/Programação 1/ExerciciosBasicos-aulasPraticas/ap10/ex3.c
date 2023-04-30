#include <stdio.h>
#include <stdbool.h>

int main(){
    int var1=5; // Declaro inteiro
    char var2='a';  // Declaro char com valor A
    bool var3 = true; // Declaro um bool true
    int *ptr1 = &var1; // Guardo a posicao de memória (endereco do 7)
    char *ptr2 = &var2; // Guardo a posicao de memoria (endereco do A)
    bool *ptr3 = &var3; // Guardo a posicao de memoria (endereco do true)

    //*ptr1=7;
    *ptr2='b'; // Atribuo um novo char ao apontado (Novo valor)
    // *ptr2 = 'c';
    *ptr3 = false;

    // ptr vai dar o endereço da memoria
    // *ptr vai dar o valor da variavel
    printf("var1 tem o endereço %p e o valor %d\n", ptr1, *ptr1); // *ptr1 = var1
    printf("var2 tem o endereçco %p e o valor %c\n", ptr2, *ptr2); // *ptr2 = var2
    printf("var3 tem o endereçco %p e o valor %d\n", ptr3, *ptr3); // *ptr3 = var3

    printf("O tamanho da var1 e: %ld e o tamanho de ptr1 e: %ld\n", sizeof(var1), sizeof(ptr1));
    printf("O tamanho da var2 e: %ld e o tamanho de ptr2 e: %ld\n", sizeof(var2), sizeof(ptr2));

    printf("O valor de ptr1+1 e: %p e o valor de *ptr1+1 e: %d\n", ptr1+1, *ptr1+1);
    printf("O valor de var2+1 e: %p e o valor de *ptr2+1 e: %c\n", ptr2+1, *ptr2+1);
}
