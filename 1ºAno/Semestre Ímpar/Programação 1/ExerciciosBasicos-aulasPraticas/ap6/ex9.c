#include <stdio.h>

float tresvalorestriangulo(float v1, float v2, float v3){
    if (v1+v2 > v3 && v1+v3 > v2 && v2+v3 > v1)
    {
        printf("Estamos na presença de um triângulo.");
    }
    else
    {
        printf("Não estamos na presença de um triângulo.");
    }
    return 0;
}

void main() {
    float v1, v2, v3;

    printf("Insira o valor de um lado:\n");
    scanf("%f", &v1);
    printf("Insira o valor de outro lado:\n");
    scanf("%f", &v2);
    printf("Insira o valor do terceiro lado:\n");
    scanf("%f", &v3);
    
    tresvalorestriangulo(v1,v2,v3);

}