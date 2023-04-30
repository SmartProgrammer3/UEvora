#include <stdio.h>
int main(){
int i, j, *p1, *p2, **pp1, **pp2;
i=4;
j=5;
p1 = &i;
p2 = &j;
pp1 = &p2;
pp2 = &p1;
'\0'

    printf("i tem o endereço %p e o valor %d\n", p1, *p1); // *ptr1 = var1
    printf("j tem o endereço %p e o valor %d\n", p2, *p2); // *ptr2 = var2
}

/*
Conteudo:
p1 = &i (endereço i) = 1000
p2 = &j (endereço j) = 1007
pp1 = &p2 (endereço p2) = 1053
pp2 = &p1 (endereço p1) = 1030;

Conteudo:
i=4;
*p2 = j = 5;
&i = 1000;
&p2 = 1053;
*pp1 = *p2 = j = 5; 
*pp2 = *p1 = i = 4; 
&(*p1) = 
j = 5;
*p1 = i =4;
*(&p1) //passed by reference = 
*&p1 = *pp2 = *p1 = i =4 
acho que elas anulam-se de certa forma porque  * p1 = valor de i      então       &( * p1 ) = adereço de memória do valor de i, ou seja 1000

*/