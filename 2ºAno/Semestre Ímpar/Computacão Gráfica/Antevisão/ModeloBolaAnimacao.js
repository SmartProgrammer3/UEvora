function ball_step(model) {
    if(model.ball.x < model.ball.r){ // Se a po
        model.ball.vx *= -1
    }

    if(model.ball.y < model.ball.r){
        model.ball.vy *= -1
    }

    if(model.ball.x > model.width - model.ball.r){
        model.ball.vx *= -1
    }
    
    if(model.ball.y > model.height - model.ball.r){
        model.ball.vy *= -1
    }
    return model;
}

