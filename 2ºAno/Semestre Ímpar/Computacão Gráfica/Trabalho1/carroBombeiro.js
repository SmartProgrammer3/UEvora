function message(text){
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}


function modeloNovo(){ //Criar modelo
    let model = {
        frames: 0,
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

    this.frames += 1;
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
    let svg_CarroGtr = document.getElementById("carroGtrUsado");
    let svg_RodasGtr = document.getElementById("rodasGtr");
    let svg_Predios = document.getElementById("prediosUsados");
    let svg_Tracejados= document.getElementById("tracejadosUsados");
    let svg_Troncos = document.getElementById("ConjuntoDeTroncos");
    let svg_CarroBombeiro = document.getElementById("carroBombeiroUsado");
    let svg_RodasBombeiro = document.getElementById("rodas");
    let svg_ArcoIris = document.getElementById("arcoiris");
    let svg_Arvore = document.getElementById("conjuntoDeArvores");
    let svg_FlocosDeNeve = document.getElementById("conjuntoFlocosDeNeve");
    let svg_nuvens1 = document.getElementById("NuvemTipo1");
    let svg_nuvens2 = document.getElementById("NuvemTipo2");

    let context = {
        svg_CarroGtr: svg_CarroGtr,
        svg_RodasGtr: svg_RodasGtr,
        svg_Predios: svg_Predios,
        svg_Tracejados: svg_Tracejados,
        svg_Troncos: svg_Troncos,
        svg_CarroBombeiro: svg_CarroBombeiro,
        svg_RodasBombeiro: svg_RodasBombeiro,
        svg_ArcoIris: svg_ArcoIris,
        svg_Arvore: svg_Arvore,
        svg_FlocosDeNeve: svg_FlocosDeNeve,
        svg_nuvens1: svg_nuvens1,
        svg_nuvens2: svg_nuvens2,
    };

    context.render = render;
    return context;
}

function render(model){
    message(`FRAMES: ${model.frames}`);

    let gtr_x = model.gtr.x;
    let gtr_y = model.gtr.y; 
    this.svg_CarroGtr.setAttribute(
        "transform", `translate(${gtr_x}, ${gtr_y})`);

    let rodasGtr = (model.gtr.rodasGtr.angulo) * (180/Math.PI);
    this.svg_RodasGtr.setAttribute(
        "transform", `rotate(${rodasGtr})`);    

    let predios_x = model.predios.x;
    let predios_y = model.predios.y;
    this.svg_Predios.setAttribute(
        "transform", `translate(${predios_x}, ${predios_y})`);

    let tracejados_x = model.tracejados.x;
    let tracejados_y = model.tracejados.y;
    this.svg_Tracejados.setAttribute(
        "transform", `translate(${tracejados_x}, ${tracejados_y})`);

    let tronco_x = model.tronco.x;
    let tronco_y = model.tronco.y;
    this.svg_Troncos.setAttribute(
        "transform", `translate(${tronco_x}, ${tronco_y})`);

    let bombeiro_x = model.bombeiro.x;
    let bombeiro_y = model.bombeiro.y;
    this.svg_CarroBombeiro.setAttribute(
        "transform", `translate(${bombeiro_x}, ${bombeiro_y})`);

    let rodasBombeiro = (model.bombeiro.rodasBombeiro.angulo) * (180/Math.PI);
    this.svg_RodasBombeiro.setAttribute(
        "transform", `rotate(${rodasBombeiro})`);  

    let arcoiris_x = model.arcoiris.x;
    let arcoiris_y = model.arcoiris.y;
    this.svg_ArcoIris.setAttribute(
        "transform", `translate(${arcoiris_x}, ${arcoiris_y})`); 

    let arvores_x = model.arvores.x;
    let arvores_y = model.arvores.y;
    this.svg_Arvore.setAttribute(
        "transform", `translate(${arvores_x}, ${arvores_y})`); 

    let flocos_x = model.flocos.x;
    let flocos_y = model.flocos.y;
    this.svg_FlocosDeNeve.setAttribute(
        "transform", `translate(${flocos_x}, ${flocos_y})`); 

    let nuvens1_x = model.nuvens1.x;
    let nuvens1_y = model.nuvens1.y;
    this.svg_nuvens1.setAttribute(
        "transform", `translate(${nuvens1_x}, ${nuvens1_y})`); 

    let nuvens2_x = model.nuvens2.x;
    let nuvens2_y = model.nuvens2.y;
    this.svg_nuvens2.setAttribute(
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