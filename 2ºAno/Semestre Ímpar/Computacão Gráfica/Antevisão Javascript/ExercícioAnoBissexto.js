// Verificar se é ano bissexto
// É ano bissexto se for múltiplo de 4 
// Não é ano bissexto se for múltiplo de 100 mas é se for de 400

function is_leap(year){
    if((year % 100 === 0) && !(year % 400 === 0)){
        return false  // Se for múltiplo de 100 e se não for múltiplo de 400, retorna falso
    }
    if((year % 4 === 0) || (year % 400 === 0)){
        return true // Se for múltiplo de 4 ou múltiplo de 400, retorna true.
    }
    if((year % 4 !== 0) || (year % 400 !== 0)){
        return false // Se não for múltiplo nem de 100 nem de 400 nem de 4, tipo 5
    }               // para não ficar "undefined"
}

console.log(is_leap(100))
console.log(is_leap(4))
console.log(is_leap(2020))
console.log(is_leap(5))