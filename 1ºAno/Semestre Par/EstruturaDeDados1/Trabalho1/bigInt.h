#ifndef bigInt_h
#define bigInt_h

#include <stdio.h>
#include "bigInt.h"
#include "doubleLList.h"
#include <string.h>
#include <stdlib.h>
#include "fatal.h"

typedef DList BigInt;

BigInt big_new(char *num);
BigInt sum_b(BigInt a, BigInt b);
BigInt compararBigIntsEAdicionarZerosNoMenor(BigInt a, BigInt b);
int compararTamanho(BigInt a, BigInt b);
BigInt sub_b(BigInt a, BigInt b);
BigInt mult_b(BigInt a, BigInt b);
BigInt div_b(BigInt a, BigInt b);
BigInt mod_b(BigInt a, BigInt b);
void print_b(BigInt a);



#endif /* bigInt_h */