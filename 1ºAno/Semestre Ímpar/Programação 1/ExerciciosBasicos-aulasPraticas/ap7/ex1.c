#include <stdio.h>


int fatorial1(int n){
    int fatorial=1;

    while (n>=1)                                         //iterativa
    {
        fatorial=fatorial*n;
        n=n-1;
    }
    return fatorial;
}

int fatorial2(int n){
    if (n==1)
    {
        return 1;                                               //recursiva
    }
    else{
        return n*fatorial2(n-1);
    }
    
}


int main(){ 
    int n;
    
    printf("Escolha o número que pretende fazer o seu fatorial:");
    scanf("%d", &n);

    printf("O resultado de %d fatorial na forma iterativa é:%d\n", n, fatorial1(n));
    printf("O resultado de %d fatorial na forma recursiva é:%d\n", n, fatorial2(n));

    return 0;
}

