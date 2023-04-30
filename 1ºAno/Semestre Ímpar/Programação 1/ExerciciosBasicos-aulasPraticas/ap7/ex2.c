#include <stdio.h>

int fibbonaci(int n){

    if (n==0)
    {
        return 0;
    }
    else if (n>0 && n<2)
    {
        return 1;
    }
    else{
        return fibbonaci(n-1)+fibbonaci(n-2);
    }
}

int main(){
    int n; 

    printf("Que número da sucessão de fibbonaci quer estudar?\n");
    scanf("%d", &n);
    printf("%d\n", fibbonaci(n));
    return 0;
}