function message(text){
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}


function modeloNovo(){ //Criar modelo
    let model = {
        paused: false,
        gtr: {
            x: 200,
            y: 370,
            vx: 5,
       
        }   
    }
    document.addEventListener("keypress", (e) =>{
        if(e.key == " ") model.paused = !model.paused;
    });
    model.update = update;
    return model;
}

function update(){
    let gtr = this.gtr;

    if(gtr.x >= 100 && gtr.x <= 500){
        gtr.vx = 2;
    }
    else{
        gtr.vx = 5;
    }
    gtr.x += gtr.vx;

}

function contexto(){ //Vai buscar os objetos
    let svg_gtr = document.getElementById("carroGtrUsado");
    let context = {
        svg_gtr : svg_gtr,
    };
    context.render = render;
    return context;
}

function render(model){

    /*****carroSvg*****/
    let gtr_x = model.gtr.x;
    let gtr_y = model.gtr.y;
    this.svg_gtr.setAttribute(
        "transform", `translate(${gtr_x}, ${gtr_y})`);
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