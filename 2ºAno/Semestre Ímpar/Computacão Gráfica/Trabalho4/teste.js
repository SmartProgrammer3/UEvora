function message(text) { //mostra mensagens para o jogador
    let terminal = document.getElementById("terminal");
    terminal.innerHTML = text;
}
function clip(x, a, b) {
    return x < a ? a : (x > b ? b : x);
}
function rand_cr(c, r) {
    return c + r * (Math.random() * 2 - 1);
}
function rand_vel(){
    return (Math.random()*0.5)+0.3
}
function range(n) {
    return [...Array(n).keys()];
}
function distance(a) {
    return Math.hypot(this.pos_x - a.pos_x, this.pos_y - a.pos_y);
} 
function render(model){         //desenha o model
   
    message(` Running | Time ${Math.ceil(model.time/60)}`);
    document.getElementById("play").style.display='none';
    if(model.paused){
        message(`Paused | Time ${Math.ceil(model.time/60)}`);
        document.getElementById("play").style.display='none';
    }
    if(model.win){
        message(`You Win | Time ${Math.ceil(model.time/60)}`);
    }
    if(model.lose){
        message(`Game Over | Time ${Math.ceil(model.time/60)}`);
    }
    let bom=model.player;
    this.fillStyle="skyblue";
    this.fillRect(0,0,600,400);
    this.fillStyle="green";
    this.fillRect(0,0,48,48);
    this.fillRect(552,352,600,400);
    this.enter_ref(bom.pos_x,bom.pos_y,0.4,0.342,0)
    this.superHomem();
    this.leave_ref();
    for(i of model.pudles){
        let cx=i.pos_x;
        let cy=i.pos_y;
        let r=i.r;
        if(r>0){
            
            
            let g = this.createRadialGradient(cx,cy,r,cx,cy,0 );
            g.addColorStop(0.4, "red");
            g.addColorStop(0, "orange");
            this.fillStyle = g;
            this.beginPath();
            this.moveTo(cx-r,cy);
            this.arcTo(cx-r,cy-r,cx,cy-r,r);
            this.arcTo(cx+r,cy-r,cx+r,cy,r);
            this.arcTo(cx+r,cy+r,cx,cy+r,r);
            this.arcTo(cx-r,cy+r,cx-r,cy,r);
            this.fill();
        }
    }
    if(model.win){
        this.fillStyle="green"
        this.fillRect(model.w_x,model.w_y,600,400)
        this.enter_ref(bom.pos_x,bom.pos_y,0.4,0.342,0)
        this.superHomem();
        this.leave_ref();
        if(model.w_x<=0){
            this.fillStyle="black"
            this.font="100px Chilanka"
            this.fillText("You Win!",100,200)
            document.getElementById("play").style.display='inline';
        }
    }
    else if(model.lose ){
        let cx=300;
        let cy=200;
        let r=model.r;
        if(r>0){
            
            
            let g = this.createRadialGradient(cx,cy,r,cx,cy,0 );
            g.addColorStop(0.4, "red");
            g.addColorStop(0, "orange");
            this.fillStyle = g;
            this.beginPath();
            this.moveTo(cx-r,cy);
            this.arcTo(cx-r,cy-r,cx,cy-r,r);
            this.arcTo(cx+r,cy-r,cx+r,cy,r);
            this.arcTo(cx+r,cy+r,cx,cy+r,r);
            this.arcTo(cx-r,cy+r,cx-r,cy,r);
            this.fill();
        }
        if(model.r>=400){
            this.fillStyle="black"
            this.font="100px Chilanka"
            this.fillText("You Lose",100,200)
            document.getElementById("play").style.display='inline';
        }
    }
    
}
function enter_ref(dx, dy, sx, sy, a) { //inicia um novpo referencial
    this.save();
    this.translate(dx, dy);
    this.rotate(a);
    this.scale(sx, sy);
}

function leave_ref() { this.restore(); }    //anbandona o referencial voltando ao antigo

function new_context(){         //cria um novo contexto 2d para desenharmos o nosso modelo
    let gc =document
    .getElementById("canvas")
    .getContext("2d");
    gc.canvas.width=600;
    gc.canvas.height=400;
    gc.superHomem=superHomem;
    gc.render=render;   
    gc.enter_ref=enter_ref;
    gc.leave_ref=leave_ref;
    return gc

}

function update_pudle(model){       // atualiza as poças 
    this.verify(model);             // verifica se esta em contacto com o jogador
    let x=this.pos_x;               // esta função tambem virifica se as poças ser matem no tabuleiro
    let y=this.pos_y;               // e se estas não estão nas safe areas "areas a verde"
    let xd=x+(this.r);
    let xe=x-(this.r)
    let yd=y+(this.r);
    let yu=y-(this.r);
    let distp=Math.hypot(this.pos_x - 48, this.pos_y - 48);
    let distc=Math.hypot(this.pos_x - 552, this.pos_y - 352);
    if((x<48&&y<48)||(x>552 && y>352)){
        this.pos_x= Math.ceil(rand_cr(0.5,0.45)*600);
        this.pos_y= Math.ceil(rand_cr(0.5,0.45)*400);
    }
    if((xe<48&&yu<48)||(xd>552&&yd>352)){
        this.fase=1;
        this.n_of_alt++;
    }
    if(distp<this.r||distc<this.r){
        this.fase=1;
        this.n_of_alt++;
    }
    if(yu<=0||yd>=400||xe<=0||xd>=600){
        this.fase=1;
        this.n_of_alt++;
    }

    if(this.n_of_alt>=4){
        this.pos_x= Math.ceil(rand_cr(0.5,0.45)*600);
        this.pos_y= Math.ceil(rand_cr(0.5,0.45)*400);
        this.r=(Math.random()*15);
        this.vel= rand_vel();
        this.fase=Math.random(); //0 incresing || 1 decresing
        this.n_of_alt= 0;
    }
    if(this.fase<0.5){
        if(this.r>=model.max_radius){
            this.fase=1;
            this.n_of_alt++;
        }
        this.r+=this.vel;
    }
    if(this.fase>0.5){
        if(this.r<=0){
            this.fase=0;
            this.n_of_alt++;
        }
        this.r-=this.vel;
    }

}
function new_pudle() {      //cria uma nova poça
    let pudle = {
        pos_x:( rand_cr(0.5,0.45)*600),
        pos_y:( rand_cr(0.5,0.45)*400),
        r:(Math.random())*15+5,
        vel: rand_vel(),
        fase:Math.random(), //0 incresing || 1 decresing
        n_of_alt: 0,
        flag:0,
    };

    pudle.update = update_pudle;
    pudle.verify=verify;
    return pudle;
}
function verify(model){         //verifica se uma determinada poça esta em contacto com o jogador
    let player=model.player;

    if(player.pos_x>this.pos_x && player.pos_y+48<this.pos_y){
        let distance=Math.hypot(this.pos_x-player.pos_x-4,this.pos_y-(player.pos_y+44));
        if(distance<this.r){
            model.lose=true;
        }
    }
    else if((player.pos_x+40<this.pos_x && player.pos_y+48<this.pos_y)){
        let distance=Math.hypot(this.pos_x-(player.pos_x+36),this.pos_y-player.pos_y+44);
        if(distance<this.r){
            model.lose=true;
        }
    }
    else if((player.pos_x+40<this.pos_x && player.pos_y>this.pos_y)){
        let distance=Math.hypot(this.pos_x-(player.pos_x+36),this.pos_y-player.pos_y-4);
        if(distance<this.r){
            model.lose=true;
        }
    }
    else if((player.pos_x<this.pos_x && player.pos_y>this.pos_y)){
        let distance=Math.hypot(this.pos_x-(player.pos_x-4),this.pos_y-player.pos_y-4);
        if(distance<this.r){
            model.lose=true;
        }
    }
}
function update_model(){        //atualiza o modelo, tambem atualiza as cordenadas do jogador
    if(this.win){
        if(this.w_x>0){

            this.w_x-=6;
            this.w_y-=4;
        }
        return;
    }
    if(this.lose){
        if(this.r<400){

            this.r+=2;
        }   


        return;
    }
    if (this.paused) return;
    this.age++;    
    this.time++;
    let player=this.player;
    if(player.pos_x>=552 && player.pos_y>=352){
        this.win=true
    }
    switch(player.action){
        case 1: /* UP */ player.pos_y -= player.vel; break;
        case 2: /* DOWN */ player.pos_y += player.vel; break;
        case 3: /* RIGHT */ player.pos_x += player.vel; break;
        case 4: /* LEFT */ player.pos_x -= player.vel; break;
        default: break;
    }
    if(player.pos_x+player.size_x>600){
        player.pos_x=600-player.size_x;
    }
    if(player.pos_x<0){
        player.pos_x=0;
    }
    if(player.pos_y+player.size_y>400){
        player.pos_y=400-player.size_y;
    }
    if(player.pos_y<0){
        player.pos_y=0;
    }
    player.action=0;
    this.pudles.forEach(p => p.update(this));
}
function new_model(){           //cria um novo modelo

    let model={
        age:0,
        time:0,
        lose: false,
        win:false,
        w_x:600,
        w_y:400,
        r:10,
        paused:1, //0 :Runing, 1: Paused
        max_radius:50,
        player:{
            pos_x:0,
            pos_y:0,
            scale_x:0.0005,
            scale_y:0.0007,
            vel:5,
            action:0,
            size_x:40,
            size_y:48,
        },
        pudles: range(10).map(i =>new_pudle()),
    }
    model.update=update_model;
    document.addEventListener("keypress",(e)=>{
        switch(e.key){
            case "w": case "W": model.player.action = 1;  model.paused=false; break;
            case "s": case "S": model.player.action = 2;  model.paused=false; break;
            case "d": case "D": model.player.action = 3;  model.paused=false; break;
            case "a": case "A":model.player.action = 4;  model.paused=false; break;
            case " ": model.paused= !model.paused; break; ;
            default: model.player.action = 0; break;
        }
        

    });
    return model;
}
function main(){
    let gc= new_context();      //criar o objeto grafico
    let model=new_model();      //cria o modelo
    let step = (ts) => {        //função de animação
        model.update();         //atualiza o modelo
         gc.render(model);      //desenha o modelo
         requestAnimationFrame(step);       //anima o modelo
     }

     requestAnimationFrame(step);
}