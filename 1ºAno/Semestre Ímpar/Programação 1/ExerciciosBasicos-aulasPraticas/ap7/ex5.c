#include <stdio.h>

int mdc(int m, int n){                                                          //mdc --> máximo divisor comum
    if (n==m)
    {
        return m;
    }
    else if (m>n)
    {
        return mdc(m-n,n);
    }
    else if (n>m)
    {
        return mdc(m,n-m);
    }
}

int VerificarNumeroPositivo1(int m){
    if (m<0)
    {
        printf("Digite números positivos. Tente novamente:");
        scanf("%d", &m);
        return VerificarNumeroPositivo1(m);
    }
    else{
        return m;
    }
    
}

int VerificarNumeroPositivo2(int n){
    if (n<0)
    {
        printf("Digite números positivos. Tente novamente:");
        scanf("%d", &n);
        return VerificarNumeroPositivo2(n);
    }
    else{
        return n;
    }
    
}


int main(){
    int m;
    int n;

    printf("Digite o número inteiro e positivo para m:");
    scanf("%d", &m);
    m=VerificarNumeroPositivo1(m);

    printf("Digite o número inteiro e positivo para n:");
    scanf("%d", &n);
    n=VerificarNumeroPositivo2(n);

    printf("O máximo divisor comum entre %d e %d é: %d", m,n, mdc(m,n));
    return 0;
}