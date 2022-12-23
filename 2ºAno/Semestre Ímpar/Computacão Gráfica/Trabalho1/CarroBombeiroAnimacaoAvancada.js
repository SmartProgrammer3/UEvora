function message(text){
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}


function modeloNovo(){ //Criar modelo
    let model = {
        gtr: {
            x: 1100,
            y: 460,
            vx: -1.5,
            vy: 0,
            rodasGtr: {
                angulo: 0,
                anguloRotacao: -0.02*Math.PI,
            }    
        },
        predios: {
            x: 0,
            y: 0,
            vx: 204,
        },
        tracejados:{
            x: 0,
            y: 0,
            vx:0.5,
        },
        tronco: {
            x: 100,
            y: 0,
            vx: 2.5,
        },
        bombeiro: {
            x: 1100,
            y: 270,
            vx: 0,
            vy: 0,
        },
        distanciaEntreCarros: 100,
    }


    model.update = update;

    return model;
}

function update(){
    let gtr = this.gtr;
    let tronco = this.tronco;
    let tracejados = this.tracejados;
    let bombeiro = this.bombeiro;


    if(tronco.x >= 1200){
        tronco.vx = 0;
    }

    if(gtr.x > 250 && gtr.x < 500){
        gtr.vx = -3
    }

    if(gtr.x < 250){
        gtr.vx = 0;
        gtr.rodasGtr.anguloRotacao = 0;
        tracejados.vx = 0;
        bombeiro.vx = -4;
    }
    if(bombeiro.x < 500){
        bombeiro.vx = 0;
        bombeiro.vy = 1;
        if(bombeiro.y > 350){
            bombeiro.vy = 0;
            bombeiro.vx = -3;
            if(bombeiro.x < 0){
                bombeiro.vx = 0;
                tronco.x = 5000;
                gtr.vx = 2;
                gtr.rodasGtr.anguloRotacao = 0.02*Math.PI;
                if(gtr.x > 750){
                    gtr.vx = 0;
                    gtr.vy = 1;
                }
            }
        }
    }
    if(gtr.y > 480){
        gtr.vy = 0;
        bombeiro.vy = -1;
        if(bombeiro.y < 250){
            bombeiro.vy = 0;
            bombeiro.vx = 1;
            if((bombeiro.x - gtr.x) > this.distanciaEntreCarros){
                bombeiro.vx = 0;
                
            }
        }
    }


    bombeiro.y += bombeiro.vy;
    bombeiro.x += bombeiro.vx;
    tronco.x += tronco.vx;
    tracejados.x += tracejados.vx;
    predios.x += predios.vx;
    gtr.x += gtr.vx;
    gtr.y += gtr.vy;
    gtr.rodasGtr.angulo += gtr.rodasGtr.anguloRotacao;

}

function contexto(){ //Vai buscar os objetos
    let carrogtr_SVG = document.getElementById("carroGtrUsado");
    let rodasgtr_SVG = document.getElementById("rodasGtr");
    let predios_SVG = document.getElementById("prediosUsados");
    let tracejados_SVG = document.getElementById("tracejadosUsados");
    let troncos_SVG = document.getElementById("ConjuntoDeTroncos");
    let carroBombeiro_SVG = document.getElementById("carroBombeiroUsado");


    let context = {
        carrogtr_SVG: carrogtr_SVG,
        rodasgtr_SVG: rodasgtr_SVG,
        predios_SVG: predios_SVG,
        tracejados_SVG: tracejados_SVG,
        troncos_SVG: troncos_SVG,
        carroBombeiro_SVG: carroBombeiro_SVG,
    };

    context.render = render;
    return context;
}

function render(model){
 
    let gtr_x = model.gtr.x;
    let gtr_y = model.gtr.y; 
    this.carrogtr_SVG.setAttribute(
        "transform", `translate(${gtr_x}, ${gtr_y})`);

    let rodasGraus = (model.gtr.rodasGtr.angulo) * (180/Math.PI);
    this.rodasgtr_SVG.setAttribute(
        "transform", `rotate(${rodasGraus})`);    

    let predios_x = model.predios.x;
    let predios_y = model.predios.y;
    message(`${model.predios.x}`);
    this.predios_SVG.setAttribute(
        "transform", `translate(${predios_x}, ${predios_y})`);

    let tracejados_x = model.tracejados.x;
    let tracejados_y = model.tracejados.y;
    message(`${model.predios.x}`);
    this.tracejados_SVG.setAttribute(
        "transform", `translate(${tracejados_x}, ${tracejados_y})`);

    
    let tronco_x = model.tronco.x;
    let tronco_y = model.tronco.y;
    this.troncos_SVG.setAttribute(
        "transform", `translate(${tronco_x}, ${tronco_y})`);

    let bombeiro_x = model.bombeiro.x;
    let bombeiro_y = model.bombeiro.y;
    this.carroBombeiro_SVG.setAttribute(
        "transform", `translate(${bombeiro_x}, ${bombeiro_y})`);

}

function main(){
    message("teste");

    let gc = contexto()
    let model = modeloNovo();

    let step = (ts) => {
        model.update();
        gc.render(model);
        requestAnimationFrame(step);
    };
    requestAnimationFrame(step);
}

/*
function message(text){
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}


function modeloNovo(){ //Criar modelo
    let model = {
        gtr: {
            x: 1000,
            y: 470,
            vx: -2,
            vy: 0,
            rodasGtr: {
                angulo: 0,
                anguloRotacao: -0.02*Math.PI,
            }    
        },
        predios: {
            x: 0,
            y: 0,
            vx: 204,
        },
        tracejados:{
            x: 0,
            y: 0,
            vx:0.5,
        },
        tronco: {
            x: 300,
            y: 0,
            vx: 2.5,
        },
        bombeiro: {
            x: 1200,
            y: 280,
            vx: -2,
            vy: 0,
            rodasBombeiro: {
                angulo: 0,
                anguloRotacao: -0.03*Math.PI,
            }
        }
    }


    model.update = update;

    return model;
}

function update(){
    let gtr = this.gtr;
    let tronco = this.tronco;
    let tracejados = this.tracejados;
    let bombeiro = this.bombeiro;   


    
    if(gtr.x < 600){
        gtr.vy = 1;
        bombeiro.vy = 1;
        if(gtr.y > 620){
            gtr.vy = 0;
            bombeiro.vy = 0;
            gtr.vx = -0.2; // 
            bombeiro.vx = -0.2;
        }
    }

    if(bombeiro.x < 360){
        gtr.vy = -0.7;
        bombeiro.vy = -0.7;
        if(gtr.y < 470){
            gtr.vy = 0;
            bombeiro.vy = 0;
        }

    }


    bombeiro.y += bombeiro.vy;
    bombeiro.x += bombeiro.vx;
    tronco.x += tronco.vx;
    tracejados.x += tracejados.vx;
    predios.x += predios.vx;
    gtr.x += gtr.vx;
    gtr.y += gtr.vy;
    gtr.rodasGtr.angulo += gtr.rodasGtr.anguloRotacao;
    bombeiro.rodasBombeiro.angulo += bombeiro.rodasBombeiro.anguloRotacao;

}

function contexto(){ //Vai buscar os objetos
    let carrogtr_SVG = document.getElementById("carroGtrUsado");
    let rodasgtr_SVG = document.getElementById("rodasGtr");
    let predios_SVG = document.getElementById("prediosUsados");
    let tracejados_SVG = document.getElementById("tracejadosUsados");
    let troncos_SVG = document.getElementById("ConjuntoDeTroncos");
    let carroBombeiro_SVG = document.getElementById("carroBombeiroUsado");
    let rodasBombeiro_SVG = document.getElementById("rodas");


    let context = {
        carrogtr_SVG: carrogtr_SVG,
        rodasgtr_SVG: rodasgtr_SVG,
        predios_SVG: predios_SVG,
        tracejados_SVG: tracejados_SVG,
        troncos_SVG: troncos_SVG,
        carroBombeiro_SVG: carroBombeiro_SVG,
        rodasBombeiro_SVG: rodasBombeiro_SVG,
    };

    context.render = render;
    return context;
}

function render(model){
 
    let gtr_x = model.gtr.x;
    let gtr_y = model.gtr.y; 
    this.carrogtr_SVG.setAttribute(
        "transform", `translate(${gtr_x}, ${gtr_y})`);

    let rodasGraus = (model.gtr.rodasGtr.angulo) * (180/Math.PI);
    this.rodasgtr_SVG.setAttribute(
        "transform", `rotate(${rodasGraus})`);    

    let predios_x = model.predios.x;
    let predios_y = model.predios.y;
    message(`${model.predios.x}`);
    this.predios_SVG.setAttribute(
        "transform", `translate(${predios_x}, ${predios_y})`);

    let tracejados_x = model.tracejados.x;
    let tracejados_y = model.tracejados.y;
    message(`${model.predios.x}`);
    this.tracejados_SVG.setAttribute(
        "transform", `translate(${tracejados_x}, ${tracejados_y})`);

    
    let tronco_x = model.tronco.x;
    let tronco_y = model.tronco.y;
    this.troncos_SVG.setAttribute(
        "transform", `translate(${tronco_x}, ${tronco_y})`);

    let bombeiro_x = model.bombeiro.x;
    let bombeiro_y = model.bombeiro.y;
    this.carroBombeiro_SVG.setAttribute(
        "transform", `translate(${bombeiro_x}, ${bombeiro_y})`);

    let rodasBombeiro = (model.bombeiro.rodasBombeiro.angulo) * (180/Math.PI);
    this.rodasBombeiro_SVG.setAttribute(
        "transform", `rotate(${rodasBombeiro})`);  

}

function main(){
    message("teste");

    let gc = contexto()
    let model = modeloNovo();

    let step = (ts) => {
        model.update();
        gc.render(model);
        requestAnimationFrame(step);
    };
    requestAnimationFrame(step);
}

*/