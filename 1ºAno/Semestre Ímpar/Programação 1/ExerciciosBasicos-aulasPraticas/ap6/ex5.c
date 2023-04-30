#include <stdio.h>

float custosEnvio(int nlivros, float c , float cs) {
    float custoenvio;
    return custoenvio=c+cs*(nlivros-1);    
}

int main() {
    int nlivros;
    float c;
    float cs;
    printf("Insira o número de livros:\n");
    scanf("%d", &nlivros);
    printf("Insira o custo de envio para a primeira cópia:\n");
    scanf("%f", &c);
    printf("Insira o custo de envio unitário para as seguintes:\n");
    scanf("%f", &cs);

    printf("A custo de envio é: %.2f",custosEnvio(nlivros, c , cs));

    return 0;
}