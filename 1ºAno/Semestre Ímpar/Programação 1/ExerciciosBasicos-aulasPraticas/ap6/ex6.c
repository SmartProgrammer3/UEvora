#include <stdio.h>

float custosEncomenda(int ncopias, float precolivro , float kg) {

    float custoencomenda;
    int custoenvio1=3;
    float custoenvio2;


    custoenvio2=custoenvio1+(0.2*kg);

    return custoencomenda=custoenvio1+custoenvio2*(ncopias-1);    
}

int main() {
    int ncopias;
    float precolivro;
    float kg;
    printf("Insira o número de cópias encomendadas:\n"); //não é necessário o \n, devido ao scanf
    scanf("%d", &ncopias);
    printf("Insira o preço unitário do livro:\n");
    scanf("%f", &precolivro);
    printf("Insira o peso do livro em kg:\n");
    scanf("%f", &kg);

    printf("A custo de encomenda é: %.2f",custosEncomenda(ncopias, precolivro, kg ));

    return 0;
}