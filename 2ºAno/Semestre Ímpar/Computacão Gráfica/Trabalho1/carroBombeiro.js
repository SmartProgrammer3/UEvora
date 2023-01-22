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
                anguloRotacao: -0.03*Math.PI,
            }    
        },
        predios: {
            x: 0,
            y: 0,
            vx: 0.1,
        },
        tracejados:{
            x: 0,
            y: 0,
            vx: 0.5,
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
                anguloRotacao: -0.02*Math.PI,
            }
        },
        arcoiris: {
            x: 0,
            y: 0,
            vx: 0.2,
        },
        arvores: {
            x: 0,
            y: 0,
            vx: 0.5,
        },
        flocos: {
            x: 0,
            y: 0,
            vy: 0.5,
            vx: 0.05,
        },
        nuvens1: {
            x: 0,
            y: 0,
            vx: 0.1,
        },
        nuvens2: {
            x: 0,
            y: 0,
            vx: 0.2,
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
    let predios = this.predios;
    let arcoiris = this.arcoiris;
    let arvores = this.arvores;
    let flocos = this.flocos;
    let nuvens1 = this.nuvens1;
    let nuvens2 = this.nuvens2;



    if(gtr.x < 600){
        gtr.vy = 1;
        bombeiro.vy = 1;
        bombeiro.rodasBombeiro.anguloRotacao =  -0.01*Math.PI
        gtr.rodasGtr.anguloRotacao = -0.02*Math.PI
        if(gtr.y > 620){
            gtr.vy = 0;
            bombeiro.vy = 0;
            gtr.vx = -0.2; 
            bombeiro.vx = -0.2;
            bombeiro.rodasBombeiro.anguloRotacao =  -0.005*Math.PI
            gtr.rodasGtr.anguloRotacao = -0.01*Math.PI
        }
    }

    if(bombeiro.x < 360){
        gtr.vy = -0.7;
        bombeiro.vy = -0.7;
        if(gtr.y < 470){
            gtr.vy = 0;
            bombeiro.vy = 0;
            gtr.vx = -2.5;
            bombeiro.vx = -2.5;
            bombeiro.rodasBombeiro.anguloRotacao =  -0.025*Math.PI
            gtr.rodasGtr.anguloRotacao = -0.035*Math.PI
        }
    }

    if(gtr.x < -1000){
        tronco.x = 300;
        gtr.x = 1000;
        bombeiro.x = 1200;
        tracejados.x = 0;
        gtr.vx = -2;
        bombeiro.vx = -2;
        predios.x = 0;
        arcoiris.x = 0;
        arvores.x = 0;
        flocos.x = 0;
        flocos.y = 0;
        nuvens1.x = 0;
        nuvens2.x = 0;
    }

    nuvens1.x += nuvens1.vx;
    nuvens2.x += nuvens2.vx;
    flocos.x += flocos.vx
    flocos.y += flocos.vy
    arvores.x += arvores.vx;
    arcoiris.x += arcoiris.vx;
    predios.x += predios.vx;
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
    let arcoIris_SVG = document.getElementById("arcoiris");
    let arvores_SVG = document.getElementById("conjuntoDeArvores");
    let flocosDeNeve_SVG = document.getElementById("conjuntoFlocosDeNeve");
    let nuvens1_SVG = document.getElementById("NuvemTipo1");
    let nuvens2_SVG = document.getElementById("NuvemTipo2");

    let context = {
        carrogtr_SVG: carrogtr_SVG,
        rodasgtr_SVG: rodasgtr_SVG,
        predios_SVG: predios_SVG,
        tracejados_SVG: tracejados_SVG,
        troncos_SVG: troncos_SVG,
        carroBombeiro_SVG: carroBombeiro_SVG,
        rodasBombeiro_SVG: rodasBombeiro_SVG,
        arcoIris_SVG: arcoIris_SVG,
        arvores_SVG: arvores_SVG,
        flocosDeNeve_SVG: flocosDeNeve_SVG,
        nuvens1_SVG: nuvens1_SVG,
        nuvens2_SVG: nuvens2_SVG,
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

    let arcoiris_x = model.arcoiris.x;
    let arcoiris_y = model.arcoiris.y;
    this.arcoIris_SVG.setAttribute(
        "transform", `translate(${arcoiris_x}, ${arcoiris_y})`); 

    let arvores_x = model.arvores.x;
    let arvores_y = model.arvores.y;
    this.arvores_SVG.setAttribute(
        "transform", `translate(${arvores_x}, ${arvores_y})`); 

    let flocos_x = model.flocos.x;
    let flocos_y = model.flocos.y;
    this.flocosDeNeve_SVG.setAttribute(
        "transform", `translate(${flocos_x}, ${flocos_y})`); 

    let nuvens1_x = model.nuvens1.x;
    let nuvens1_y = model.nuvens1.y;
    this.nuvens1_SVG.setAttribute(
        "transform", `translate(${nuvens1_x}, ${nuvens1_y})`); 

    let nuvens2_x = model.nuvens2.x;
    let nuvens2_y = model.nuvens2.y;
    this.nuvens2_SVG.setAttribute(
        "transform", `translate(${nuvens2_x}, ${nuvens2_y})`); 
    
        
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