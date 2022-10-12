//Escreva uma função "range" para gerar uma lista com todos os números ímpares (condição: "x % 2 === 1") entre dois valores dados.
function range(a, b) {
    var array = [];

    for( a; a <= b; a++){
        if(a % 2 === 1){
            array.push(a);
        }
    }
    return array;
}
console.log(range(0, 3))