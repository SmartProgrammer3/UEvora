function main() {
    let gc = document
                .getElementById("acanvas")
                .getContext("2d");
    gc.fillStyle = "crimson";
    gc.fillRect(0,0,256,256);
    
    let message_pane = document.getElementById("myconsole");

    message_pane.innerText = "Bye";
}