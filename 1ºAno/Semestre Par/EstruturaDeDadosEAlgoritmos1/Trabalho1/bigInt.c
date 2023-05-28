#include <stdio.h>
#include "bigInt.h"
#include "doubleLList.h"
#include <string.h>
#include <stdlib.h>



BigInt big_new(char *num){
    BigInt numeroBig = CreateDList();
    int numero;
    
    for(int andar = 0; num[andar] != '\0'; andar++){
        
        if(num[andar]==45){
            andar++;
            numero = num[andar] - '0';
            addDList(-numero, numeroBig);
        } else{
            numero = num[andar] - '0';
            addDList(numero, numeroBig);
        }
    }
    return numeroBig;
}


BigInt sum_b(BigInt a, BigInt b){
    BigInt somaNumerosBig = CreateDList();

    int elementoA;
    int elementoB;
    int somaElementos = 0;
    int carryOut = 0;
    int mudancaDeSinalA;
    int mudancaDeSinalB;
    int mudancaDeSinal;

    DPosition posicaoA = Back(DFooter(a));
    DPosition posicaoB = Back(DFooter(b));




    if ((Retrieve(Advance(DHeader(a))) >= 0) && (Retrieve(Advance(DHeader(b))) >= 0)) // Soma normal, se os dois forem positivos
    {
     
        while ((posicaoA != DHeader(a)) && (posicaoB != DHeader(b))){
            compararBigIntsEAdicionarZerosNoMenor(a,b);
            elementoA = Retrieve(posicaoA);
            elementoB = Retrieve(posicaoB);

            somaElementos = elementoA + elementoB + carryOut;
            carryOut = somaElementos/10;

            printf("elemento A: %d ; elemento B: %d ; soma: %d ; carryout: %d\n", elementoA, elementoB, somaElementos, carryOut);
            somaElementos = somaElementos % 10;

            InsertDList(somaElementos,DHeader(somaNumerosBig),somaNumerosBig);

            posicaoA = Back(posicaoA);
            posicaoB = Back(posicaoB);
        }
    } else if((Retrieve(Advance(DHeader(a))) < 0) && (Retrieve(Advance(DHeader(b))) >= 0)){ // Se o a for negativo e o b positivo -a+b = b-a

            compararBigIntsEAdicionarZerosNoMenor(a,b);
            BigInt copiaDeA = a;

            mudancaDeSinalA = Retrieve(Advance(DHeader(a)));
            DeleteElement(mudancaDeSinalA, copiaDeA);
            InsertDList(-mudancaDeSinalA, DHeader(copiaDeA), copiaDeA);
            
            posicaoA = Back(posicaoA);
            posicaoB = Back(posicaoB);
            
            somaNumerosBig = sub_b(copiaDeA,b);   

    } else if((Retrieve(Advance(DHeader(a))) >= 0) && (Retrieve(Advance(DHeader(b))) < 0)){ // Se o b for negativo e o a positivo
        compararBigIntsEAdicionarZerosNoMenor(a,b);


    
    } else if((Retrieve(Advance(DHeader(a))) < 0) && (Retrieve(Advance(DHeader(b))) < 0)){ // soma de 2 negativos -a-b = -(a+b)
        while ((posicaoA != DHeader(a)) && (posicaoB != DHeader(b))){
            elementoA = Retrieve(posicaoA); // Elemento A igual a cada elemento da posição de A
            elementoB = Retrieve(posicaoB);

            mudancaDeSinalA = Retrieve(Advance(DHeader(a))); //O objetivo é remover o sinal de a, para deixar de ser negativo e passar a ser positivo
            DeleteElement(mudancaDeSinalA, a);   // de modo a faciliar a soma
            InsertDList(-mudancaDeSinalA, DHeader(a), a);

            mudancaDeSinalB = Retrieve(Advance(DHeader(b))); //O objetivo é remover o sinal de b, para deixar de ser negativo e passar a ser positivo
            DeleteElement(mudancaDeSinalB, b);
            InsertDList(-mudancaDeSinalB, DHeader(b), b);

            compararBigIntsEAdicionarZerosNoMenor(a,b); // Equilibrar tamanhos adicionando zeros

            // Faz-se a soma normal
            somaElementos = elementoA + elementoB + carryOut; 
            carryOut = somaElementos/10;
            somaElementos = somaElementos % 10;

            InsertDList(somaElementos,DHeader(somaNumerosBig),somaNumerosBig);

            posicaoA = Back(posicaoA);
            posicaoB = Back(posicaoB);
        }
    }


    if (carryOut != 0)
    {
        InsertDList(carryOut,DHeader(somaNumerosBig),somaNumerosBig);     
    }
    
    return somaNumerosBig;
}



BigInt compararBigIntsEAdicionarZerosNoMenor(BigInt a, BigInt b){ // Esta função vai comparar os dois bigints, e vai adicionar zeros no início do menor bigInt para igualar o tamanho
                                                                // de modo a facilitar as operações.
    while (SizeDList(a) != SizeDList(b))
    {
        if (SizeDList(a) < SizeDList(b))
        {
            InsertDList(0 , DHeader(a), a);
            
        } else{
            InsertDList(0 , DHeader(b), b);     
        }  
    }
}



int compararTamanho(BigInt a, BigInt b){  
    if (SizeDList(a) > SizeDList(b))
    {
        return 1;        
    } else{
        return 0;
    }  
}



BigInt sub_b(BigInt a, BigInt b){
    BigInt diferencaNumerosBig = CreateDList();

    int elementoA;
    int elementoB;
    int diferencaElementos = 0;
    int carryOut = 0;
    int mudarSinal;

    DPosition posicaoA = Back(DFooter(a));
    DPosition posicaoB = Back(DFooter(b));

    compararBigIntsEAdicionarZerosNoMenor(a,b);

    if((Retrieve(Advance(DHeader(a))) >= 0) && (Retrieve(Advance(DHeader(b))) < 0)){ // Se a positivo e b negativo, então fica a-(-b) = a+b
        BigInt copiaDeB = b; // Novo bigint igual a b, com o objetivo de muda o sinal para positivo de B, e assim basta fazer a soma a+b

        mudarSinal = Retrieve(Advance(DHeader(a))); // Guarda o primeiro elemento onde vai o ter o sinal -
        DeleteElement(mudarSinal, copiaDeB); // Deleta o node desse elemento
        InsertDList(-mudarSinal, DHeader(copiaDeB), copiaDeB); // Adiciona o simétrico desse elemento, retirando o sinal -*-1 = 1 , no primeiro elemento
        
        diferencaNumerosBig = sum_b(a,copiaDeB);   // É a soma de a com b
    } else if((Retrieve(Advance(DHeader(a))) < 0) && (Retrieve(Advance(DHeader(b))) >= 0)){ // Se a negativo e b positivo, então fica -a-b = -(a+b)
        diferencaNumerosBig = sum_b(a,b);   

    } else if((Retrieve(Advance(DHeader(a))) >= 0) && (Retrieve(Advance(DHeader(b))) >= 0)){ // Se ambos forem positivos fica a-b   
        while ((posicaoA != DHeader(a)) || (posicaoB != DHeader(b)))
        {
            elementoA = Retrieve(posicaoA);
            elementoB = Retrieve(posicaoB);
            if (elementoA < elementoB && elementoA != 0) // Quando o digito de A é inferior que o digito de B é necessario pedir emprestimo
            {
                if (compararTamanho(a,b) == 1) // Se a for maior que b
                {       
                    diferencaElementos = (10 + elementoA) - elementoB + carryOut; // o 10 equivale ao emprestimo
                    diferencaElementos = diferencaElementos % 10;
                    carryOut = -1; // Tirar emprestimo
                } else if(compararTamanho(a,b) == 0 || elementoB < elementoA){ // Se b for maior que a
                    diferencaElementos = (10 + elementoB) - elementoA + carryOut;
                    diferencaElementos = diferencaElementos % 10;
                    carryOut = 0;  
                    if ((posicaoA ==Advance(DHeader(a))) && (posicaoB == Advance(DHeader(b))))
                    {
                        diferencaElementos = -elementoB + carryOut;
                    }               
                }
            } else {
                diferencaElementos =  elementoA - elementoB + carryOut;
                diferencaElementos = diferencaElementos % 10;
                carryOut = 0;

                printf("%d  %d  %d  %d\n", elementoA, elementoB, carryOut, diferencaElementos );
            }

            InsertDList(diferencaElementos,DHeader(diferencaNumerosBig),diferencaNumerosBig);

            posicaoA = Back(posicaoA);
            posicaoB = Back(posicaoB);
        }   
    } else{ // Se a é negativo e b é negativo -a - (-b) = -a + b = b-a


    }

    if (!(elementoA < elementoB && elementoA != 0))
    {
        carryOut = 0;
    }

    if (carryOut != 0)
    {
        InsertDList(carryOut,DHeader(diferencaNumerosBig),diferencaNumerosBig);     
    }

    return diferencaNumerosBig;

}

BigInt mult_b(BigInt a, BigInt b){
    BigInt multiplicacaoBigInts = CreateDList();

    DPosition posicaoA = Back(DFooter(a));
    DPosition posicaoB = Back(DFooter(b));

    int carryOut = 0;
    int multiplicacao = 0;
    int elementoA;
    int elementoB;

    while (posicaoB != DHeader(b)) {

        elementoB = Retrieve(posicaoB);
        while (posicaoA != DHeader(a))
        {
            elementoA = Retrieve(posicaoA);
            multiplicacao = ((elementoA * elementoB)  + carryOut) % 10;
            carryOut = ((elementoA * elementoB) + carryOut) / 10;
            InsertDList(multiplicacao,DHeader(multiplicacaoBigInts),multiplicacaoBigInts);  
            Back(posicaoA);

            if (carryOut != 0)
            {
                InsertDList(carryOut,DHeader(multiplicacaoBigInts),multiplicacaoBigInts);    
            }
        }
        Back(posicaoB);

    }
}


BigInt div_b(BigInt a, BigInt b){

}

BigInt mod_b(BigInt a, BigInt b){

}

void print_b(BigInt a){
    PrintDList("a", a);
}
