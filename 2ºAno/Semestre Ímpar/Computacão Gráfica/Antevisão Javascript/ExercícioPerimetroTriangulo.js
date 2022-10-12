// Calcular a função perímetro de um triângulo dado 3 pontos


function distance(p1, p2) { // Utilizar a função distância para ir buscar o valor dos 3 lados.
    return Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));  
}

function perimetro(pontoP, pontoQ, pontoR){
    let lado1 = distance(pontoP, pontoQ) //Lado P - Q
    let lado2 = distance(pontoP, pontoR) //Lado P - R
    let lado3 = distance(pontoQ, pontoR) //Lado Q - R

    let perimetro = lado1 + lado2 + lado3 //Perimetro é a soma dos 3 lados

    return perimetro
}

console.log(perimetro( {x: 0, y: 0}, {x: 1, y: 0} , {x: 1, y: 0} ))