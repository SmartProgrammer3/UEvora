function newModel() {
    let model = {
        carang: {
            x: 120,
            y: -80,
            vx: -0.2,
            vy: 1,

        },
        mar: {
            x: 400,
            y: 0,
            vx: -0.1,
            vy: 1,
        },
    }
    model.update = update;
    return model;
  }



function context() {

    let mar_SVG = document.getElementById("mar");
    let caranguejo_SVG = document.getElementById("caranguejoUsado");

  
    let context = {
        mar_SVG: mar_SVG,
        caranguejo_SVG: caranguejo_SVG,
        caranguejo_SVG: caranguejo_SVG,
    }
    context.render = render;
    return context;
  }
  
function update() {
    let mar = this.mar;
    let carang = this.carang;

    carang.x += carang.vx;
    mar.x += mar.vx;




    if(mar.x < 350){
        mar.vx = 0.1;
        carang.vx = 0.2;
    } 

    if(mar.x > 450){
        mar.x = 400;
        mar.vx = -0.1;
        carang.x = 120;
        carang.vx = -0.2;
    }


}
  
  
  
function render(model) {

    let mar_x = model.mar.x;
    let mar_y = model.mar.y;
    this.mar_SVG.setAttribute(
        "transform", `rotate(${20}) translate(${mar_x}, ${mar_y})`);

    let carang_x = model.carang.x;
    let carang_y = model.carang.y;

    this.caranguejo_SVG.setAttribute(
        "transform", `rotate(${15}) translate(${carang_x}, ${carang_y})`);
}

function main(){

    let gc = context();
    let model = newModel();

    let step = (ts) => {
        model.update();
        gc.render(model);
        requestAnimationFrame(step);
    };
    requestAnimationFrame(step);
}