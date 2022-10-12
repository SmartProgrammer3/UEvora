// Escreva uma função reverse(a) que, dada uma lista a, devolve uma lista com os elementos por ordem inversa.

function reverse(a) {
    let arrayElementosOrdemInversa = []
    //Vamos percorrer o array do fim para o início
    //indice é igual ao cumprimento do array - 1 pq o array 
    //começa com indice 0 / a é a lista inicial
    let indice = a.length - 1
    for(indice ; indice >= 0; indice--){
        arrayElementosOrdemInversa.push(a[indice])
    }
        
    return arrayElementosOrdemInversa
}

console.log(reverse([1, 2, 3]))