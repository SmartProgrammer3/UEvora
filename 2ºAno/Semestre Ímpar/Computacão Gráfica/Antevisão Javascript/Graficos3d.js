function vertex(i, j, section, spine){
    
    if((i<0) || (i >= section.length) || (j<0) || (j >= section.length)){
        return null;
    }
    
    let ponto = section[i]
    let vetor = spine[j]
    
    return {x: ponto.x + vetor.x, y: vetor.y, z: ponto.z+vetor.z}
}



function list_lens(x){
    let elementosString = []
    
    for(let i = 0; i < x.length; i++){
        elementosString.push(x[i].length)
    }
    return elementosString;
}


function count_pes(x){
    let numeros = 0

    for(let i = 0; i < x; i++){
        if(x[i] > 0){
            numeros++
        }
    }
    return numeros
}