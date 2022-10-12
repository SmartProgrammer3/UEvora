// Escreva uma função clock_handles(h, m) para calcular as posições dos ponteiros de um relógio, dadas as horas h e os minutos m.
// O ponteiro dos minutos tem comprimento 1 e que o ponteiro das horas tem comprimento 0.7.
// O resultado deve ser um objeto javascript com atributos { hx, hy, mx, my } em que hx, hy são as coordenadas do fim do ponteiro das
// horas e mx, my são as coordenadas do fim do ponteiro dos minutos. Estes valores devem ser arredondados pela função r indicada.
// A função clock_handles deve validar os argumentos: 0 <= h < 12 e 0 <= m < 60. Inválido é igual ao meio-dia
// Atenção que o ângulo é em radianos em js.

function r(x) {
    return Math.round(x * 100) / 100;
}
  
function clock_handles(h, m) { // Se os elementos forem inválidos
    if(h < 0 || h > 11 || m < 0 || m > 59){
        h = 0 // Corresponde ao meio-dia
        m = 0
    }
    //Calcular o angulo do ponteiro das horas e passar para radianos 
    let anguloH = Math.PI*(0.5 - h / 6)
    let hx = 1 * Math.cos(anguloH) // Eixo x é cos
    let hy = 1 * Math.sin(anguloH) // Eixo y é sen
      
    let anguloM = Math.PI*(0.5 - m / 30)  
    let mx = 0.7 * Math.cos(anguloM) // Eixo x é cos
    let my = 0.7 * Math.sin(anguloM) // Eixo y é sen 
      
    return { hx: r(hx), hy: r(hy), mx: r(mx), my: r(my) }
}

console.log(clock_handles(0, 15))
console.log(clock_handles(3, 45))
  