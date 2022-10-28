/* 4a) */
    /* SQL */
    SELECT DISTINCT nome
    FROM autor NATURAL INNER JOIN autoria NATURAL INNER JOIN genero
    WHERE genero = 'romance'
    /* Algebra relacional*/
    π nome
    σ genero = "romance" (autor ⋈ autoria ⋈ genero)

/* 4b) */
    /* SQL */
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN leu NATURAL INNER JOIN autoria 
    WHERE Coda = '0000-0000-0000-0001'
    /* Algebra relacional*/
    π nome
    σ Coda = "0000-0000-0000-0001" (membro ⋈ leu ⋈ autoria)

/* 4c) */
    /* SQL */
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN leu NATURAL INNER JOIN genero
    WHERE genero = 'romance'
    INTERSECT
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN genero
    WHERE genero = 'romance'
    /* Algebra relacional*/
    π nome
    σ genero = "romance" (membro ⋈ leu ⋈ gosta ⋈ genero)

/* 4d) */
    /* SQL */
    SELECT nome
    FROM membro NATURAL INNER JOIN leu
    EXCEPT
    SELECT nome
    FROM membro NATURAL INNER JOIN leu NATURAL INNER JOIN gosta 
    /* Algebra relacional*/
    π nome(membro ⋈ leu) - π nome(membro ⋈ leu ⋈ gosta)

/* 4e) */
    /* SQL */
    SELECT DISTINCT Nome /* É amigo do oleitor */
    FROM membro NATURAL INNER JOIN amigo
    WHERE IdMemb1 = 'oleitor'
    INTERSECT
    SELECT DISTINCT Nome /* Gostaram de um livro de José Saramago */
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE Coda = '0000-0000-0000-0001'
    /* Algebra relacional*/
    π nome
        σ IdMemb1 = "oleitor" (membro ⋈ amigo)
            ∩
    π nome
        σ Coda = "0000-0000-0000-0001" (membro ⋈ gosta ⋈ autoria)

/* 4f) */
    /* SQL */
    SELECT DISTINCT nome, idmemb
    FROM membro NATURAL INNER JOIN amigo
    EXCEPT
    SELECT DISTINCT nome, idmemb2
    FROM membro NATURAL INNER JOIN amigo
    WHERE idmemb1 = 'oleitor'
    /* Algebra relacional*/
    π nome, idmemb (membro ⋈ amigo) 
        -
    π nome, idmemb2
        σ IdMemb1 = "oleitor" (membro ⋈ amigo)

/* 4g) */
    /* SQL */
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE coda = '0000-0000-0000-0002' /*Código Francisco José Viegas*/
    INTERSECT
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE coda = '0000-0000-0000-0004' /*Código Lobo Antunes*/
    /* Algebra relacional*/
    π nome
        σ coda = "0000-0000-0000-0002" (membro ⋈ gosta ⋈ autoria)
            ∩
    π nome
        σ Coda = "0000-0000-0000-0004" (membro ⋈ gosta ⋈ autoria)

/* 4h) */
    /* SQL */
    SELECT COUNT(distinct idmemb2) 
    FROM amigo
    WHERE idmemb1 = 'oleitor'
    /* Algebra relacional*/
    R <- amigo
    S <-  σ idmemb1 = "oleitor" amigo (R)
    G count (idmemb2) as n(S) /* Agregação */

/* 4i) */
    /* SQL */
    SELECT MAX(idmemb2) /* Idmemb do membro com mais amigos*/
    FROM amigo
    /* Algebra relacional*/
    R <- amigo


/* 4j) */
    /* SQL */
    SELECT MAX(idmemb) /* Idmemb do membro que gosta de mais livros*/
    FROM membro NATURAL INNER JOIN gosta
    /* Algebra relacional*/

/* 4k) */
    /* SQL */
