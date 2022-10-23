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
    FROM membro NATURAL INNER JOIN leu NATURAL INNER JOIN gosta NATURAL INNER JOIN genero

/* 4e) */
    /* SQL */
    SELECT DISTINCT Nome /* É amigo do oleitor */
    FROM membro NATURAL INNER JOIN amigo
    WHERE IdMemb1 = 'oleitor'
    INTERSECT
    SELECT DISTINCT Nome /* Gostaram de um livro de José Saramago */
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE Coda = '0000-0000-0000-0001'

/* 4f) */
    /* SQL */
    SELECT DISTINCT nome, idmemb
    FROM membro natural inner join amigo
    EXCEPT
    SELECT DISTINCT nome, idmemb2
    FROM amigo natural inner join membro
    WHERE idmemb1 = 'oleitor'

/* 4g) */
    /* SQL */
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE coda = '0000-0000-0000-0002'
    INTERSECT
    SELECT DISTINCT nome
    FROM membro NATURAL INNER JOIN gosta NATURAL INNER JOIN autoria
    WHERE coda = '0000-0000-0000-0004'

/* 4h) */
    /* SQL */
    SELECT COUNT(idmemb2) 
    FROM amigo
    WHERE idmemb1 = 'oleitor'

/* 4i) */
    /* SQL */
    SELECT MAX(idmemb2) /* Idmemb do membro com mais amigos*/
    FROM amigo

/* 4j) */
    /* SQL */
    SELECT MAX(idmemb) /* Idmemb do membro que gosta de mais livros*/
    FROM membro NATURAL INNER JOIN gosta

/* 4k) */
    /* SQL */
