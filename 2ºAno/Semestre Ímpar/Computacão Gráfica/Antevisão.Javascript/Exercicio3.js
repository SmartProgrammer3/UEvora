// Escreva uma função range(a, b) para gerar todos os números inteiros entre a e b (incluindo os extremos).

function range(a, b) {
    let arrayNumerosInteirosAeB = []
    
    for(a ; a <= b ; a++ ){
        arrayNumerosInteirosAeB.push(a)    
    }
    
    return arrayNumerosInteirosAeB
}    

console.log(range(0, 3))