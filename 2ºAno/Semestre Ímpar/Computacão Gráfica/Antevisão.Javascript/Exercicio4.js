// Escreva uma função map(f, a) que, dada uma função f: int -> int e uma lista de números inteiros a,
// devolver a lista com os elementos de a transformados por f.

function map(f, a) {
    let arrayElementosTransformados = []
    
    // f é a expressão dos elementos, x é o respetivo elemento 
    // avaliado.
    for(let x of a){
        arrayElementosTransformados.push(f(x))
    }
    
    return arrayElementosTransformados
}   
// Exemplos para verificar
console.log(map(x => 2*x, [1, 2, 3, 4]))
console.log(map(x => x*x, [-2, -1, 0, 1, 2]))