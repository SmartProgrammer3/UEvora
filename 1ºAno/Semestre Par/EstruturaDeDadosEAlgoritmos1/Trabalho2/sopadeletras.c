#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "sopadeletras.h"
#include "hashTable.h"

int main(int argc, char *argv[])
{
    int tamanhoLinha;
    int tamanhoColuna;
    int numeroPalavras;
    char caracterAvaliado;
    char matrizSopaDeLetras[tamanhoLinha][tamanhoColuna];
    char palavra[50];
    HashTableQuadratica TabelaPalavras;
    HashTableQuadratica TabelaPrefixos;

    printf("X----------------------------------------------------------X\n");
    printf("        Bem-vindo ao jogo da sopa de letras!\n");
    printf("X----------------------------------------------------------X\n");


    FILE *ficheiroParaAbrir = fopen("sopadeletras.txt", "r"); // Ler o ficheiro que contém a matriz sopa de letras

    if (!ficheiroParaAbrir) //Senão abrir o ficheiro com a sopa de letras, então não foi possível abrir o ficheiro.
    {
        printf("Desculpa, mas não foi possível abrir o ficheiro\n");
        return 1;
    }

    fscanf(ficheiroParaAbrir, "%d", &numeroPalavras);

    printf("Existe %d palavras na sopa de letras\n", numeroPalavras);

    while (numeroPalavras != 0)
    {
        fscanf(ficheiroParaAbrir, "%s", palavra);
        numeroPalavras--;
    }
    
    fscanf(ficheiroParaAbrir, "%d%d", &tamanhoLinha, &tamanhoColuna);
    printf("O tamanho do puzzle é de %dx%d\n", tamanhoLinha, tamanhoColuna);
    
    for (int contadorLinhaFicheiro = 0; contadorLinhaFicheiro < tamanhoLinha; contadorLinhaFicheiro++)
    {
        printf("%d | ", contadorLinhaFicheiro);
        for (int contadorColunaFicheiro = 0; contadorColunaFicheiro < tamanhoColuna; contadorColunaFicheiro++)
        {
            fscanf(ficheiroParaAbrir, " %c", &caracterAvaliado);
            if (caracterAvaliado == ' ')
            {
                fscanf(ficheiroParaAbrir, " %c", &caracterAvaliado);
                matrizSopaDeLetras[contadorLinhaFicheiro][contadorColunaFicheiro] = caracterAvaliado;
            } else{
                matrizSopaDeLetras[contadorLinhaFicheiro][contadorColunaFicheiro] = caracterAvaliado;              
            }
            printf("%c ", matrizSopaDeLetras[contadorLinhaFicheiro][contadorColunaFicheiro]);
        }
        printf("\n");
    }
    fclose(ficheiroParaAbrir);
}



























