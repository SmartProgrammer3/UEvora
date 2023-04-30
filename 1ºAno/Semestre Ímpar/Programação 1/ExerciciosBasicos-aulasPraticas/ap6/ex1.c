#include <stdio.h>
#include <assert.h>

int sucessor(int num) {
    return num+1;    //printf("%d\n", num=num+1)
}

int main() {
    int n;

    printf("Insira um número inteiro:\n");
    scanf("%d", &n);

    printf("O sucessor é: %d",sucessor(n));

    return 0;
}