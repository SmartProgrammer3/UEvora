#include <stdio.h>
#include "bigInt.h"
#include "doubleLList.h"
#include <string.h>
#include <stdlib.h>
#include "fatal.h"

int main(int argc, const char *argv[]){

    /* //Experimentar as funções da doublelist
    DList L = CreateDList();
    InsertDList(5,DHeader(L),L);
    InsertDList(6,DHeader(L),L);
    InsertDList(7,DHeader(L),L);
    InsertDList(8,DHeader(L),L);
    addDList(4,L);
    addDList(10,L);
    InsertDListIth(3,2,L);
    PrintDList("a", L);
    printf("%d\n", SizeDList(L));
    printf("%d\n", FindDList(8,L));
    RemoveElementAt(1,L);
    PrintDList("a", L);
    printf("%d\n", SizeDList(L));
    RemoveElementAt(15,L);
    PrintDList("a", L);
    printf("%d\n", SizeDList(L));
    
    DeleteElement(5,L);
    PrintDList("a", L);
    printf("%d\n", SizeDList(L));

    MakeEmptyDList(L);
    PrintDList("a", L);
    printf("%d\n", SizeDList(L));
    */



    BigInt a = big_new("136");
    BigInt b = big_new("25");

    printf("a =");
    print_b(a);
    printf("b =");
    print_b(b);

    BigInt soma = sum_b(a,b);
    printf("soma =");
    print_b(soma);

    BigInt diferenca = sub_b(a,b);
    printf("diferenca =");
    print_b(diferenca);
/*
    BigInt multiplicao = mult_b(a, b);
    printf("multiplicão =");
    print_b(multiplicao);
*/

    return 0;
}



