const eventosPorPagina = 4;
let paginaAtual = 1;

// https://www.w3schools.com/jsref/prop_style_display.asp
const mostrarEventos = () => {
    const eventos = document.getElementById("listaDeEventos").children; // Obter os eventos individuais
    
    const indiceInicial = (paginaAtual - 1) * eventosPorPagina;
    const indiceFinal = indiceInicial + eventosPorPagina; // 4 eventos por pagina

    for (let i = 0; i < eventos.length; i++) {

        if (i >= indiceInicial && i < indiceFinal) {  // Se tiver espaço para o evento ser exibido, então é mostrado
            eventos[i].style.display = 'table-row'; // Element is rendered as a table row (like <tr>)
        } else {
            eventos[i].style.display = 'none'; // Caso contrário, é ocultado
        }
    }
};

const updatePagina = () => {
    const totalDeEventos = document.getElementById("listaDeEventos").children.length;
    const totalDePaginas = Math.ceil(totalDeEventos / eventosPorPagina);

    let paginacaoBotoes = "";

    // Verifica se não for a primeira pagina
    if (paginaAtual > 1) {
        paginacaoBotoes += "<button title = 'Anterior' onclick='goToPreviousPage()'> << </button>";
    }   

    // Verifica se não é a ultima pagina
    if (paginaAtual < totalDePaginas) {
        paginacaoBotoes += "<button title = 'Seguinte' onclick='goToNextPage()'> >> </button>";
    }

    document.getElementById("pagination").innerHTML = paginacaoBotoes;
};


// Filtro para o nome e data
const filtro = () => {
    const eventos = document.getElementById("listaDeEventos").children;
    let aux = 0;


    const procuraNome = document.getElementsByName("event-name")[0].value.toLowerCase().trim();
    const procuraData = document.getElementsByName("event-date")[0].value.trim();
    
    if (!procuraNome && !procuraData) {
        alert("Tens que preencher algum filtro.");
        return; 
    }
    
    let visualizarAtleta = false;
    let nomeEvento = [];
    let dataEvento = [];
    // avalia cada evento
    for (const evento of eventos) {  
        nomeEvento = evento.id.split("|")[0].toLowerCase();    // Tirar o nome da lista de evento que vem do controlador
        dataEvento = evento.id.split("|")[1].trim();

        // Se o utilizador meter nome do evento e a respetiva data.
        if ((nomeEvento.includes(procuraNome)) && (dataEvento === procuraData)) {
            aux++;
            visualizarAtleta = true;
        } else {
            if (nomeEvento.includes(procuraNome) && procuraNome != "") {
                
                if(procuraData != ""){
                    // Se o nome for parecido e tiver uma data, verifica se a data corresponde à data procura.
                    // Será mais para os eventos com nomes parecidos (Porque estamos a usar includes) e retirar aqueles que têm a data diferente.
                    // É um auxilio da condição em cima
                    if(procuraData === dataEvento){
                        visualizarAtleta = true;
                    } else{
                        visualizarAtleta = false;
                    }
                } else{
                    aux++;
                    visualizarAtleta = true;
                }
            } else {
                if (dataEvento === procuraData) {

                    if(procuraNome != ""){
                        if(nomeEvento.includes(procuraNome)){
                            visualizarAtleta = true;
                        } else{
                            visualizarAtleta = false;
                        }
                    } else{
                        aux++;
                        visualizarAtleta = true;
                    }
                } else {
                    visualizarAtleta = false;
                }
            }
        }

        if(visualizarAtleta){
            evento.style.display = 'table-row';
        } else{
            evento.style.display = 'none';
        }
    }

    const emptyMessage = document.getElementById("empty");
    emptyMessage.style.display = aux === 0 ? 'table-row' : 'none';
    paginaAtual = 1; // Reinicia a página para a primeira ao realizar uma nova pesquisa
    updatePagina();
};


const goToPreviousPage = () => {
    if (paginaAtual > 1) {
        paginaAtual--;
        mostrarEventos();
        updatePagina();
    }
};

const goToNextPage = () => {
    const totalDeEventos = document.getElementById("listaDeEventos").children.length;
    const totalDePaginas = Math.ceil(totalDeEventos / eventosPorPagina);

    if (paginaAtual < totalDePaginas) {
        paginaAtual++;
        mostrarEventos();
        updatePagina();
    }
};


function filtroClassificacao() {
    const classificacoes = document.getElementById("listaDeClassificacoes").children;
    const procuraNome = document.getElementById("nomedoatleta").value.toLowerCase().trim();
    const procuraEscalao = document.getElementById("escalaodosatletas").value.trim();
    let aux = 0;
    let classificacao, nomeAtleta, escalaoAtleta;

    if (!procuraNome && !procuraEscalao) {
        alert("Tens que preencher algum filtro.");
        return; 
    }


    for (classificacao of classificacoes) {
        nomeAtleta = classificacao.cells[1].textContent.toLowerCase().trim();
        escalaoAtleta = classificacao.cells[3].textContent.trim();

        if ((nomeAtleta === procuraNome) && (escalaoAtleta == procuraEscalao)) {
            aux++;
            visualizarAtleta = true;
        } else {
            if ((nomeAtleta === procuraNome) && (procuraNome != "")) {
                
                if(procuraEscalao != ""){
                    if(procuraEscalao === escalaoAtleta){
                        visualizarAtleta = true;
                    } else{
                        visualizarAtleta = false;
                    }
                } else{
                    aux++;
                    visualizarAtleta = true;
                }
            } else {
                if (escalaoAtleta === procuraEscalao) {

                    if(procuraNome != ""){
                        if(nomeAtleta === procuraNome){
                            visualizarAtleta = true;
                        } else{
                            visualizarAtleta = false;
                        }
                    } else{
                        aux++;
                        visualizarAtleta = true;
                    }
                } else {
                    visualizarAtleta = false;
                }
            }
        }

        if(visualizarAtleta){
            classificacao.style.display = 'table-row';
        } else{
            classificacao.style.display = 'none';
        }



        //const emptyMessage = document.getElementById("empty");
        //emptyMessage.style.display = aux === 0 ? 'table-row' : 'none';
    }


    const emptyMessage = document.getElementById("empty");
    if (emptyMessage) {
        emptyMessage.style.display = aux === 0 ? 'table-row' : 'none';
    }
}
