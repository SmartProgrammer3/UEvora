#include "hashTable.h"
#include "fatal.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MinTableSize (10)

enum KindOfEntry { Legitimate, Empty, Deleted };

struct HashEntry
{
    char  Element[50];
    enum KindOfEntry Info;
};
typedef struct HashEntry Cell;


struct HashTbl
{   int Ocupados;
    int TableSize;
    Cell *TheCells;
};


bool CheckPrime(int N) {
    if (N == 2 || N == 3) {
        return true;
    }
    
    if (N == 1 || N% 2 == 0) {

        return false;
    }

    for (int i = 3; i * i <= N; i +=2) {
        if (N % i == 0) {
            return false;
        }
        return true;
    }
}



int NextPrime( int N ) // Para ver o próximo primo
{
    int i;
    for ( i = 2; i < N; i++)
    {
        if (N%i == 0)
        {
            return NextPrime(N);
        } 
    }
    return N;
}



Index Hash(ElementType Key, int TableSize, int i){
    return (Key+i*i) % TableSize;
}



HashTableQuadratica InitializeTable( int TableSize )
{
    HashTableQuadratica H;
    int i;

    if( TableSize < MinTableSize ){
        Error( "Table size too small" );
        return NULL;
    }


    H = malloc( sizeof( struct HashTbl ) );
    if( H == NULL )
        FatalError( "Out of space!!!" );


    H->TableSize =  TableSize ;
    H->TheCells = malloc( sizeof( Cell ) * H->TableSize );

    if( H->TheCells == NULL )
        FatalError( "Out of space!!!" );

    for( i = 0; i < H->TableSize; i++ )
        H->TheCells[ i ].Info = Empty;

    return H;
}



bool Find( char *palavra, HashTableQuadratica H){
    for (int i = 0; i < H->TableSize; i++){
        if (!strcmp(H->TheCells[i].Element, palavra)){
            return true;
        }
    }
    return false;
}




void Insert( ElementType Key, char *palavra, HashTableQuadratica H ){
    int indice;

    for (int i = 0; i < H->TableSize; i++)
    {
        indice = Hash(Key, H->TableSize, i);
        
        if (H->TheCells[indice].Info == Empty || H->TheCells[indice].Info == Deleted)
        {
            strcpy(H->TheCells[indice].Element, palavra); //Inserir a palavra no indice respetivo da tabela
            H->TheCells[indice].Info = Legitimate;
            H->Ocupados += 1;

            if (LoadFactor(H) >= 0.75){
                H =Rehash(H);
            }
            break;
        }
    }
}


float LoadFactor(HashTableQuadratica H){ // Qual é a percentagem da capacidade da hash?
    return (float)H->Ocupados/H->TableSize; //Uma medida que decide quando aumentar a capacidade da HashTable para manter a operação de pesquisa e inserção.
}



HashTableQuadratica Rehash( HashTableQuadratica H ){
    int i, OldSize;
    Cell *OldCells;

    OldCells = H->TheCells;
    OldSize  = H->TableSize;

    H = InitializeTable( 2 * OldSize );

    for( i = 0; i < OldSize; i++ ){
        if( OldCells[ i ].Info == Legitimate ){
            Insert(i, OldCells[ i ].Element, H );
        }
    }
    free( OldCells );

    return H;
}



ElementType Retrieve( Position P, HashTableQuadratica H ){
    return H->TheCells[ P ].Element;
}


void DestroyTable( HashTableQuadratica H ){
    free( H->TheCells );
    free( H );
}


void PrintTable(HashTableQuadratica H){
    for (int i = 0; i < H->TableSize; i++)
    {
        if (H->TheCells[i].Info == Legitimate)
        {
            printf("%s", H->TheCells[i].Element);
        }      
    }
}