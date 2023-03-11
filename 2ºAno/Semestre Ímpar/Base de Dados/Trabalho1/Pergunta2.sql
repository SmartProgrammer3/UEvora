Create table membro (
Nome varchar(50),
IdMemb char(15) primary key,
Pais char(50)
);


Create table autor (
Coda char(20) primary key,
Nome varchar(50),
Pais char(50)
);


Create table livro (
ISBN varchar(50) primary key,
Titulo char(50)
);


Create table amigo (
IdMemb1 varchar(50),
IdMemb2 varchar(50),
primary key (IdMemb1, IdMemb2),
foreign key (IdMemb1) references membro on delete restrict,
foreign key (IdMemb2) references membro on delete restrict

);


Create table gosta (
IdMemb varchar(50),
ISBN char(50),
primary key (IdMemb, ISBN),
foreign key (IdMemb) references membro on delete restrict,
foreign key (ISBN) references livro on delete restrict
);


Create table leu (
IdMemb char(50),
ISBN char(50),
primary key (IdMemb, ISBN),
foreign key (IdMemb) references membro on delete restrict,
foreign key (ISBN) references livro on delete restrict
);


Create table genero (
ISBN varchar(50),
Genero char(20),
primary key (ISBN, Genero),
foreign key (ISBN) references livro on delete restrict
);


Create table autoria (
ISBN varchar(50),
Coda char(20),
primary key (ISBN, Coda),
foreign key (ISBN) references livro on delete restrict,
foreign key (Coda) references autor on delete restrict
);


