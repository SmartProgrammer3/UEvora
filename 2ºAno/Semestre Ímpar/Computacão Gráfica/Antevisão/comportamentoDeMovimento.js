/*
    model.motion -> movimento
    = 0          -> está parado
    = 1          -> vira á direita (model.step_size)
    = 2          -> vira á esquerda (model.step_size)
    = 3          -> vira para baixo (model.step_size)
    = 4          -> vira para cima (model.step_size)

    model.step_size -> É a variável que se incrementa na posição para realizar o movimento se não ficar parado.
    model.hero -> Herói (o objeto que queremos movimentar).
    model.hero.x -> Posição do herói em função ao x.
    model.hero.y -> Posição do herói em função ao y.
*/

function hero_step(model) {
    // Criar 5 booleans para fazer depois as condições (valores de acordo com o comentário a cima)
    let estaParado = model.motion === 0; // Se o valor do model.motion é 0 então está parado. A variável é verdadeira
    let viraDireita = model.motion === 1; 
    let viraEsquerda = model.motion === 2; 
    let desce = model.motion === 3; 
    let sobe = model.motion === 4; 

    if(estaParado){ // Se está parado não se mexe nas coordenadas
        model.hero.x = model.hero.x
        model.hero.y = model.hero.y
    }

    if(viraDireita){ // Se vira à direita, o y continua igual mas o x vai ser incrementado
        model.hero.x = model.hero.x + model.step_size
        model.hero.y = model.hero.y
    }

    if(viraEsquerda){ // Se vira à esquerda, o y continua igual mas o x vai ser decrementado
        model.hero.x = model.hero.x - model.step_size
        model.hero.y = model.hero.y
    }

    if(desce){ // Se desce, o x continua igual mas o y vai ser incrementado
        model.hero.x = model.hero.x 
        model.hero.y = model.hero.y + model.step_size
    }

    if(sobe){ // Se sobe, o x continua igual mas o y vai ser decrementado
        model.hero.x = model.hero.x 
        model.hero.y = model.hero.y - model.step_size
    }

    return model
}

