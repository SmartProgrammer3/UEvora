#include <stdio.h>

float triangulo(float v1, float v2, float v3);

void main(){
    float v1;
    float v2;
    float v3;

    printf("Digite um lado:");
    scanf("%f", &v1);
    printf("Digite o segundo lado:");
    scanf("%f", &v2);
    printf("Digite o terceiro lado:");
    scanf("%f", &v3);
    printf("%f", triangulo(v1,v2,v3));
}

float triangulo(float v1, float v2, float v3){
    if (v1+v2>v3 && v1+v3>v2 && v2+v3>v1)
    {
        if(v1!=v2 && v2!=v3 && v1!=v3){
            return 1;
        }
        else if(v1==v2 && v2==v3 && v1==v3){
            return 3;
        }
        else{
            return 2;
        }
    }
    else {
        return -1;
    }   
}