#include <stdio.h>

int A(int m,int n){
    if (m==0)
    {
        return n+1;
    }
    else if (m>0 && n==0)
    {
        return A(m-1,1);
    }
    else if (m>0 && n>0)
    {
        return A(m-1,A(m,n-1));
    }
}


int main(){
    int m;
    int n;

    printf("Digite um valor inteiro e positivo para m:\n");
    scanf("%d", &m);
    printf("Digite um valor inteiro e positivo para n:\n");
    scanf("%d", &n);

    printf("Pela função Ackermann, o resultado é: %d", A(m,n));
    return 0;
}