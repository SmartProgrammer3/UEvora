// Escreva uma função split(p, a) que, dada uma função p: int -> boolean e uma lista de números inteiros a, devolve duas listas, a primeira
// com todos os elementos de a que verificam a condição definida por p e a segunda lista com todos os elementos de a que não verificam essa
// condição.

function split(p, a) {
    let arrayElementosVerificam=[];
    let arrayElementosNaoVerificam=[];
    for(let x of a){ //for of - Condinção ciclo do elemento x até a
        if(p(x)){ // Se a condição de p para x for verificada
            arrayElementosVerificam.push(x);// Adiciona o elemento 
        }else{
            arrayElementosNaoVerificam.push(x);
        }
    }
    // Retorna as 2 listas
    return {ElementosVerificados: arrayElementosVerificam ,ElementosNaoVerificam: arrayElementosNaoVerificam}
}

console.log(split(x => x >= 2, [1, 2, 3]))