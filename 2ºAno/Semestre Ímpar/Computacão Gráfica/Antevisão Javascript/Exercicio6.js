// Escreva uma função, norm, calcular a norma de um vetor 3D, usando a função dot que usou para o produto interno.
// Norma: ∥u∥ = sqrt(u * u)

function r(x) { 
    return Math.round(x * 1000) / 1000;
}
function norm(a){ // A variável a é o vetor, ou seja, recebe (x,y,z) e faz as contas sqrt
    return Math.sqrt(a.x*a.x + a.y*a.y + a.z*a.z);
}

console.log(r(norm({x: 1, y: 0, z: 0})))