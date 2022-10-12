// Escreva uma função "range" para gerar uma lista com todos os números pares (condição: "x % 2 === 0") entre dois valores dados.

function range(a, b) {
    let arrayNumerosPares = []
    
    for(let x=a ; x <= b; x++){
        if(x % 2 === 0){
            arrayNumerosPares.push(x)
        }
    }
    return arrayNumerosPares
}

console.log(range(0,3))