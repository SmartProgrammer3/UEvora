// Escreva uma função, count_pred(p, x), que aceita como input uma uma função p: int -> boolean e uma lista de inteiros x e que
// calcula quantos elementos de x verificam a condição p(x).

// p é a função que é para ser verificar por cada elemento de x
function count_pred(p,x){
    let numeroElementos = 0
    
    for(var i of x){ // The for...of statement executes a loop that operates on a sequence of values sourced from an iterable object.
        if(p(i)){ // Verificar
        numeroElementos++ // Se for verificado então incrementa elemento verificado.
        }
    }
 
    return numeroElementos
}

console.log(count_pred(x => x > 0, [-2, 0, 2]))