#include <stdio.h>

float listaDeTemposAtletas(int totalAtletas, float temposAtletas[totalAtletas]){
    int atleta;
    for ( atleta = 0; atleta < totalAtletas; atleta++)
    {
        printf ("\nIntroduza o tempo do atleta [%d] : ",atleta+1);
        scanf ("%f",&temposAtletas[atleta]);
    }
    return temposAtletas[totalAtletas]; 
}

float primeiroLugar(int totalAtletas, float mainTemposAtletas[totalAtletas]){
    float minimoTempo;
    int contador;
    for ( contador = 0; contador < totalAtletas ; contador++)
    {
        if (contador==0)
        {                                                   // A primeira posição do array na variável minimoTempo que é
            minimoTempo=mainTemposAtletas[contador];        // para depois comparar com os outros tempos.
        }   else {
                if (mainTemposAtletas[contador]<minimoTempo)  //Ou seja, caso nas posições seguintes haja um valor mais baixo                                                   
                {                                               // que o minimoTempo, a variavel minimoTempo será atualizada
                    minimoTempo=mainTemposAtletas[contador];
                }
        }
    }
    return minimoTempo;
}

float segundoLugar(float minimoTempo, int totalAtletas, float mainTemposAtletas[totalAtletas]){
    float minimoTempo2=1000000000000000000000000000;   //Para depois comparar, visto que ninguem corre tanto tempo
    int contador;
    for ( contador=0; contador < totalAtletas ; contador++)
    {       //o tempo desse "andar"                         //Se o andar for menos que o mintempo2
        if ((mainTemposAtletas[contador] > minimoTempo) && (mainTemposAtletas[contador] < minimoTempo2))
        {
            minimoTempo2=mainTemposAtletas[contador];
        }      
    }
    return minimoTempo2;
}

float terceiroLugar(float minimoTempo2, int totalAtletas, float mainTemposAtletas[totalAtletas]){
    float minimoTempo3=0;
    int contador;
    for ( contador = 0; contador < totalAtletas; contador++)
    {
        if ((minimoTempo3==0) && (mainTemposAtletas[contador] > minimoTempo2))  //O programa vai ler o resto dos "andares"
        {                                                      //Desta forma, o andar acima do minimo anterior será o minimo de agora.
            minimoTempo3=mainTemposAtletas[contador];
        }   else {
            if ((mainTemposAtletas[contador] < minimoTempo3) && (mainTemposAtletas[contador] > minimoTempo2))
            {
                minimoTempo3=mainTemposAtletas[contador];
            }
        }  
    }
    return minimoTempo3;
}


void main(){
    int totalAtletas;
    float minimoTempo; // Primeiro lugar
    float minimoTempo2; // Segundo lugar
    float minimoTempo3; // Terceiro lugar

    printf("Quantos atletas são? ");
    scanf("%d", &totalAtletas);

    float tA[totalAtletas];
    float mainTemposAtletas[totalAtletas];
    mainTemposAtletas[totalAtletas]=listaDeTemposAtletas(totalAtletas, tA);

    minimoTempo=primeiroLugar(totalAtletas, tA);
    minimoTempo2=segundoLugar(minimoTempo, totalAtletas, tA);
    minimoTempo3=terceiroLugar(minimoTempo2, totalAtletas, tA);

    printf("O primeiro lugar é: %f\n", minimoTempo);
    printf("O segundo lugar é: %f\n", minimoTempo2);
    printf("O terceiro lugar é: %f\n", minimoTempo3);
}