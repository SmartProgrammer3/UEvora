#ifndef doubleLList_h
#define doubleLList_h

#include <stdio.h>
#include "bigInt.h"
#include "doubleLList.h"
#include <string.h>
#include <stdlib.h>
#include "fatal.h"
typedef int ElementType;

struct DNode;
struct DListStruct;
typedef struct DNode *DPosition;
typedef struct DListStruct *DList;

DList CreateDList( void );
void MakeEmptyDList( DList L);
int SizeDList(DList L);
DPosition DHeader(DList L);
DPosition DFooter(DList L);
int IsEmptyDList(DList L); // Verifica se está vazia a lista
void InsertDList(ElementType X, DPosition P, DList L); // Insere na lista o elemento X a seguir à posição p
void InsertDListIth(ElementType X, int i, DList L); // Insere na lista na i-ésima posição
void addDList(ElementType X, DList L); // Insere no fim da lista
int FindDList(ElementType e, DList L); // Procura a posição do elemento pretendido
void DeleteElement(ElementType e, DList L); // Deleta o elemento da lista
ElementType RemoveElementAt(int i, DList L); // Remove um elemento numa i posição
DPosition Advance(DPosition P);
DPosition Back(DPosition P);
ElementType Retrieve(DPosition P);
void PrintDList(char *name, DList L);

#endif /* doubleLList_h */