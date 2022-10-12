// Escreva uma função distance(p1, p2) que, dados dois pontos (i.e. objetos com atributos {x, y}), retorna a distância entre eles. A distância
// entre os pontos (x1,y1) e (x2,y2) pode ser calculada pela fórmula: d = sqrt ((x1-x2) ^ 2 + (y1-y2) ^ 2)

function distance(p1, p2) { // p1 - Ponto 1 p2 - Ponto 2 p1.x -> Coordenada x do ponto 1 etc..
    return Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)));  
}

console.log(distance( {x: 0, y: 0}, {x: 1, y: 0}) )
console.log(distance({x: 0, y: 0}, {x: 1, y: 0}) === 1.0)
console.log(distance({x: 0, y: 0}, {x: 1, y: 0}) === 2.0)