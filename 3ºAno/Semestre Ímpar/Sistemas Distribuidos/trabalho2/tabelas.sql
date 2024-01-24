-- https://www.cs.toronto.edu/~nn/csc309/guide/pointbase/docs/html/htmlfiles/dev_datatypesandconversionsFIN.html
CREATE TABLE utilizadores (
    username           varchar(255) NOT NULL,
    email              varchar(255) NOT NULL,
    password           varchar(255) NOT NULL,
    tipoDeUtilizador   varchar(255) NOT NULL,
    PRIMARY KEY(username)
);


CREATE TABLE artistas (
    nomeartista   varchar(255) NOT NULL,
    tipodearte    varchar(255) NOT NULL,
    latitude      DOUBLE PRECISION,
    longitude     DOUBLE PRECISION,
    aatuar        BOOLEAN,
    estado        varchar(255) NOT NULL,
    artistid      integer,
    PRIMARY KEY(artistid)
);


CREATE TABLE atuacoes (
    iddoartista         INTEGER REFERENCES,
    latitude            DOUBLE PRECISION,
    longitude           DOUBLE PRECISION,
    datadaatuacao       DATE,
    idatuacao           integer,
    PRIMARY KEY(idatuacao)
);


CREATE TABLE donativos (
    idartista           integer, 
    valordodonativo     integer, 
    userdodonativo      varchar(255) NOT NULL,
    datadodonativo      DATE,
    iddoacao            integer,
    PRIMARY KEY(iddoacao)
);



CREATE TABLE classificacoes (
    artistid         integer,
    rating           integer,    -- 0 a 10 0-> Muito mau 10 -> Muito bom
    utilizadorClas   varchar(255) NOT NULL,
    idclassificacao  integer,
    PRIMARY KEY(idclassificacao)
);




Select * from utilizadores;
Select * from artistas;
Select * from atuacoes;
Select * from donativos;
Select * from classificacoes;


