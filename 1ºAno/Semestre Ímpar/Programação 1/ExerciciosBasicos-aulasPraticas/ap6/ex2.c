#include <stdio.h>

int quadradoDoSucessor(int num) {
    int quadrado;
    num=num+1;
    return quadrado=(num)*(num);    
}

int main() {
    int n;

    printf("Insira um número inteiro:\n");
    scanf("%d", &n);

    printf("O quadrado do sucessor é: %d",quadradoDoSucessor(n));

    return 0;
}