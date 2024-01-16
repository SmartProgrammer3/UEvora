CREATE TABLE tabelaDeUtilizadores
(
  username      varchar(40)  NOT NULL,
  userpassword  varchar(255) NOT NULL,
  email         varchar(255) NOT NULL,
  genero        varchar(40)  NOT NULL,
  escalao       varchar(40)  NOT NULL,
  enable        smallint     NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);


CREATE TABLE roleUtilizadores
(
  username  varchar(40) NOT NULL,
  user_role varchar(15) NOT NULL,
  FOREIGN KEY (username) REFERENCES tabelaDeUtilizadores (username)
);


CREATE TABLE eventos
(
  nomeevento varchar(40)  NOT NULL,
  descricao  varchar(255) NOT NULL,
  data       date         NOT NULL,  -- formato: (YYYY-MM-DD)
  valor      real         NOT NULL DEFAULT 0, -- formato aceita decimais (real)
  PRIMARY KEY (nomeevento)
);


CREATE TABLE inscricoes
(
  nomeParticipante  varchar(40)  NOT NULL,
  nomeevento        varchar(40)  NOT NULL,
  nparticipante     int          NOT NULL,
  PRIMARY KEY (nomeevento, nomeParticipante),
  FOREIGN KEY (nomeevento) REFERENCES eventos (nomeevento)
);


CREATE TABLE referencias
(
  username   varchar(40) NOT NULL,
  entidade   int         NOT NULL,
  referencia int         NOT NULL,
  valor      varchar(20) NOT NULL,
  nomeevento varchar(40) NOT NULL,
  PRIMARY KEY (entidade, referencia),
  FOREIGN KEY (username) REFERENCES tabelaDeUtilizadores (username),
  FOREIGN KEY (nomeevento) REFERENCES eventos (nomeevento)
);


CREATE TABLE pagamentos
(
  username   varchar(40) NOT NULL,
  nomeevento varchar(40) NOT NULL,
  PRIMARY KEY (username, nomeevento),
  FOREIGN KEY (username) REFERENCES tabelaDeUtilizadores (username),
  FOREIGN KEY (nomeevento) REFERENCES eventos (nomeevento)
);



CREATE TABLE tempos
(
  nomeevento     varchar(40) NOT NULL,
  nparticipante  int         NOT NULL,
  pontodaprova   varchar(8)  NOT NULL,
  tempodatahora  timestamp   NOT NULL,    -- formato: YYYY-MM-DD HH:MM:SS
  PRIMARY KEY (nparticipante, nomeevento, pontodaprova),
  FOREIGN KEY (nomeevento) REFERENCES eventos (nomeevento)
);



-- Dar STAFF ao staff
UPDATE roleUtilizadores
set user_role='ROLE_STAFF'
WHERE username = 'Joao';

-- Apagar tabela DROP TABLE "nomeTabela" CASCADE;
-- DROP TABLE eventos CASCADE;
-- DROP TABLE inscricoes CASCADE;
-- DROP TABLE referencias CASCADE;
-- DROP TABLE roleutilizadores CASCADE;
-- DROP TABLE tabeladeutilizadores CASCADE;
-- DROP TABLE tempos CASCADE;
-- DROP TABLE pagamentos CASCADE;

-- SELECT * FROM tempos WHERE nomeevento = 'nomeevento';
-- SELECT nomeParticipante FROM inscricoes WHERE nomeevento = 'nomeevento'; Nome do evento que quero
-- SELECT nParticipante FROM inscricoes WHERE nomeevento = 'nomeevento';  Numero do evento que quero


