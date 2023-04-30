#include <stdio.h>
#include <math.h>

int main() {

    float perimetro, area, volume, raio, pi;

    printf("Qual o valor do raio?\n");
    scanf("%f" , &raio); 

    pi= 3.141593;
    perimetro = 2 * pi * raio;
    area = pi * raio * raio;
    volume = (4 * pi * raio * raio * raio)/3;

    printf("Qual o raio? %f\n" , raio);
    printf("Perimetro da circunferência = %f\n" , perimetro);
    printf("Area do círculo = %f\n" , area);
    printf("Volume da esfera = %f\n" , volume);

return 0;
}