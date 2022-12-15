/*
    Existe dois objetos gráficos (square e ball) Com atributos x e y e aspeto colour
    O modelo tem um atributo alert_distance.
        -> Quando a distancia de ball a square é superior ao valor do alert_distance. A cor da bola (model.ball.colour) é "seagreen"
        -> Caso contrário, a cor da bola é "crimson"
*/

function ball_step(model) {
    let condicaoDistancia = (model.square.x - model.ball.x > model.alert_distance ) || (model.square.y - model.ball.y > model.alert_distance)
    // A variavel condicaoDistancia é basicamente um boolean em que diz que se a distancia, tanto no eixo de x como no eixo de y forem superiores ao valor do 
    // model.alert_distance, então a condição e verdadeira.

    if(condicaoDistancia){
        model.ball.colour = "seagreen"
    } else {
        model.ball.colour = "crimson"
    }
    return model
}
