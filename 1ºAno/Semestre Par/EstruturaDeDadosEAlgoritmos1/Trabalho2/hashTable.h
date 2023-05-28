#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef int ElementType;
    
#ifndef _HashQuad_H
#define _HashQuad_H

typedef unsigned int Index;
typedef Index Position;

struct HashTbl;
typedef struct HashTbl *HashTableQuadratica;

bool CheckPrime(int N);
int NextPrime(int N);
Index Hash(ElementType Key, int TableSize, int i);
HashTableQuadratica InitializeTable( int TableSize );
bool Find( char *palavra, HashTableQuadratica H );
void Insert( ElementType Key, char *palavra, HashTableQuadratica H );
float LoadFactor(HashTableQuadratica H);
HashTableQuadratica Rehash( HashTableQuadratica H );
ElementType Retrieve( Position P, HashTableQuadratica H );
void DestroyTable( HashTableQuadratica H );
void PrintTable(HashTableQuadratica H);


#endif