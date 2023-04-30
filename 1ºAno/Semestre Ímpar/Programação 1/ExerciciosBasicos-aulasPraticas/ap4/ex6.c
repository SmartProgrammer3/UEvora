#include <stdio.h>

    int main() {
    float x, a, y;
    float epsilon=0.00001;
    printf("Introduza um numero:");
    scanf("%f", &a);
    x=a/2;
    y=(x+a/x)/2;

    while((y-x)*(y-x) >= epsilon*epsilon){
        x=y;
        y=(x+a/x)/2;
    }
printf("A raiz quadrada é %f.\n", y);
return 0;
}