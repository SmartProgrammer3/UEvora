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
    WITH contarAmigos(idmemb, livro) as (select gosta.idmemb, 
        COUNT(gosta.isbn) as livro
        From gosta
        GROUP BY  idmemb)

    SELECT DISTINCT idmemb2
        from(select max(livro) as livro
        from contarAmigos) as gosta, amigo, contarAmigos
        where gosta.livro=contarAmigos.livro and amigo.idmemb1=contarAmigos.idmemb;
    /* Algebra relacional*/

/* 4l) */
    /* SQL */
    SELECT DISTINCT titulo, count (genero)
    from livro, genero
    where livro.isbn = genero.isbn
    group by titulo;
    /* Algebra relacional*/

/* 4m) */
    /* SQL */
    SELECT DISTINCT titulo, 
        count(genero.isbn) as numeroDeGeneros, 
        count(gosta.isbn) as numeroDeGostos

    FROM livro NATURAL INNER join genero NATURAL INNER join gosta
    WHERE livro.isbn=gosta.isbn AND genero.isbn=gosta.isbn AND livro.isbn=genero.isbn 
    GROUP BY titulo;
    /* Algebra relacional*/

/* 4n) */
    /* SQL */
    SELECT DISTINCT  autor.nome, 
        count(livro.isbn) as numerosDeLivros, 
        count(genero.isbn) as numeroDeGeneros,
        count(gosta.isbn) as numeroDeGostos

    FROM autor NATURAL INNER join autoria NATURAL INNER join livro NATURAL INNER join gosta NATURAL INNER join genero
    WHERE autor.coda = autoria.coda AND autoria.isbn = gosta.isbn AND autoria.isbn = livro.isbn AND autoria.isbn = genero.isbn
    GROUP BY  autor.nome;
    /* Algebra relacional*/

/* 4o) */
    /* SQL */
    SELECT DISTINCT nome, 
        count(amigo.idmemb2) as numeroDeAmigos, 
        count(gosta.isbn) as numeroDeLivrosQueGosta

    FROM membro NATURAL INNER join amigo NATURAL INNER join gosta
    WHERE membro.idmemb=amigo.idmemb1 AND membro.idmemb=gosta.idmemb
    GROUP BY nome;
    /* Algebra relacional*/

/* 4p) */
    /* SQL */
    SELECT DISTINCT nome
    FROM membro as amigo1
    where not exists (SELECT idmemb
        FROM membro
        WHERE membro.idmemb != amigo1.idmemb
        EXCEPT(SELECT idmemb1
            FROM amigo
            WHERE amigo.idmemb2 = amigo1.idmemb
                UNION
                    SELECT idmemb2
                    FROM amigo
                    WHERE amigo.idmemb1 = amigo1.idmemb));

    /* Algebra relacional*/



/* 4q) */
    /* SQL */
    WITH contarGostos (idmemb) as (Select gosta.idmemb 
        FROM gosta)

    SELECT DISTINCT titulo
    FROM count_gostos NATURAL INNER join livro NATURAL INNER join amigo
    WHERE amigo.idmemb1='oleitor' AND amigo.idmemb2=count_gostos.idmemb
    /* Algebra relacional*/