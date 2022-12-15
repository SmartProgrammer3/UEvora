/*
    atributos earth e moon

*/

function moon_step(model){
    model.moon.x = model.earth.x - (Math.cos(model.moon.a) * model.d)
    model.moon.y = model.earth.y + (Math.sin(model.moon.a) * model.d)

    // As coordenadas do centro da lua são definidas pelo ângulo (model.moon.a) com o centro da terra, ou seja, temos de multiplicar o centro pelo angulo 
    // e vamos obter as coordenadas que depois multiplicamos pela distancia que queremos 
    return model
}