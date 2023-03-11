function message(text){
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}


function modeloNovo(){ //Criar modelo
    let model = {
        frames: 0,
        palavra:{
            x: 0,
            y: 0,
            z: 0,
            vx: 1,
            vy: 1,
            vz: 1,
            anguloX: 0,
            anguloY: 0,
            anguloZ: 0,
            anguloRotacao: -0.1 * Math.PI,

        }
    }
    
    model.update = update;
    return model;
}

function update(){
    let palavra = this.palavra;

    this.frames += 1;

    palavra.anguloX += palavra.anguloRotacao;
    palavra.x += palavra.vx;
    palavra.y += palavra.vy;
    palavra.z += palavra.vz;
}

function contexto(){ 
    let x3d_palavra = document.getElementById("palavra"); // É o conjunto das letras

    let context = {
        x3d_palavra: x3d_palavra,
    };

    context.render = render;
    return context;

}

function render(model){
    message(`FRAMES: ${model.frames}`);

    this.x3d_palavra.setAttribute("transform", `rotate(${model.palavra.anguloX}, ${model.palavra.anguloY}, ${model.palavra.anguloZ})`);
    this.x3d_palavra.setAttribute("transform", `translate(${model.palavra.x}, ${model.palavra.y}, ${model.palavra.z})`);
        
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