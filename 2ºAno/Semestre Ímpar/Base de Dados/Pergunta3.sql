/* 3a) */
insert into membro values('Joaquim Alberto' , 'af25ji67mn32500' , 'Portugal');               
insert into membro values('António Maria Juventude' , 'af25ji67mn32501' , 'Portugal'); 
insert into membro values('Maria das Couves' , 'af25ji67mn32502' , 'Portugal');
insert into membro values('Moreira Álvares' , 'af25ji67mn32503' , 'Portugal');               
insert into membro values('Joana Barros' , 'af25ji67mn32504' , 'Portugal');                  
insert into membro values('Madalena Soares' , 'af25ji67mn32505' , 'Portugal');               
insert into membro values('Luis Miguel' , 'af25ji67mn32506' , 'Portugal');                   
insert into membro values('Pedro dos Santos' , 'af25ji67mn32507' , 'Portugal');              
insert into membro values('Joao Silva' , 'af25ji67mn32508' , 'Portugal');                    
insert into membro values('Carlos da Gama' , 'af25ji67mn32509' , 'Portugal');               
insert into membro values('Ana Garcia' , 'af25ji67mn32510' , 'Portugal');                   
insert into membro values('Ines Sousa' , 'af25ji67mn32511' , 'Portugal');                    
insert into membro values('Barbara Silva' , 'af25ji67mn32512' , 'Portugal'); 
insert into membro values('Lourenço Nunes' , 'af25ji67mn32513' , 'Portugal');
insert into membro values('Teresa Castro' , 'af25ji67mn32514' , 'Portugal');                 
insert into membro values('Mariana Carvalho' , 'af25ji67mn32515' , 'Portugal');              
insert into membro values('Tomás Rebelo' , 'af25ji67mn32516' , 'Portugal');                  
insert into membro values('Miguel Araujo' , 'af25ji67mn32517' , 'Portugal');         
insert into membro values('Rui Barbosa' , 'af25ji67mn32518' , 'Portugal');                           
insert into membro values('Nicole' , 'af25ji67mn32519' , 'Portugal');                        

/* 3b) */
insert into livro values('978-3-16-148410-0' , 'Ensaio sobre a Cegueira'); /* José Saramago */ /*Romance*/
insert into livro values('978-3-16-148410-1' , 'O mar em Casablanca'); /* Francisco José Viegas */ 
insert into livro values('978-3-16-148410-2' , 'Adeus, Minha Adorada'); /* Policial e Romance - Raymond Chandle*/
insert into livro values('978-3-16-148410-3' , 'O Rapaz do Caixote de Madeira'); /* Livro com 3 autores -> Leon Leyson e Marilyn J. Harran e Elizabeth B. Leyson */
insert into livro values('978-3-16-148410-4' , 'A luz de Pequim'); /* Francisco José Viegas */
insert into livro values('978-3-16-148410-5' , 'Bom dia, Verônica'); /* Livro com 2 autores -> Ilana Casoy e Raphael Montes*/
insert into livro values('978-3-16-148410-6' , 'Memorial do Convento'); /* José Saramago */ /*Romance*/
insert into livro values('978-3-16-148410-7' , 'As Crónicas'); /* Lobo Antunes */
insert into livro values('978-3-16-148410-8' , 'O Sono Eterno'); /*Policial e Romance - Raymond Chandle*/ /*Romance*/
insert into livro values('978-3-16-148410-9' , 'As Naus: romance'); /* Lobo Antunes */ /*Romance*/
insert into livro values('978-3-16-148411-0' , 'O mar em Casablanca'); /* Francisco José Viegas */

/* São 5 autores mas tivemos que adicionar mais dados como o pedido na linha e) para responder às questões, como é pedido um livro com 3 autores e outro com 2 autores*/
insert into autor values('0000-0000-0000-0000' , 'Raymond Chandle', 'USA');
insert into autor values('0000-0000-0000-0001' , 'Jose Saramago', 'Portugal');
insert into autor values('0000-0000-0000-0002' , 'Francisco José Viegas', 'Portugal');
insert into autor values('0000-0000-0000-0003' , 'Raphael Montes', 'Brasil');
insert into autor values('0000-0000-0000-0004' , 'Lobo Antunes', 'Portugal');
insert into autor values('0000-0000-0000-0005' , 'Ilana Casoy', 'Brasil');
insert into autor values('0000-0000-0000-0006' , 'Leon Leyson', 'Polónia');
insert into autor values('0000-0000-0000-0007' , 'Marilyn J. Harran', 'USA');
insert into autor values('0000-0000-0000-0008' , 'Elizabeth B. Leyson', 'USA');


insert into autoria values('978-3-16-148410-0'  , '0000-0000-0000-0001');

insert into autoria values('978-3-16-148410-1'  , '0000-0000-0000-0002');

insert into autoria values('978-3-16-148410-2'  , '0000-0000-0000-0000');

insert into autoria values('978-3-16-148410-3'  , '0000-0000-0000-0006');
insert into autoria values('978-3-16-148410-3'  , '0000-0000-0000-0007');
insert into autoria values('978-3-16-148410-3'  , '0000-0000-0000-0008');

insert into autoria values('978-3-16-148410-4'  , '0000-0000-0000-0002');

insert into autoria values('978-3-16-148410-5'  , '0000-0000-0000-0003');
insert into autoria values('978-3-16-148410-5'  , '0000-0000-0000-0005');

insert into autoria values('978-3-16-148410-6'  , '0000-0000-0000-0001');

insert into autoria values('978-3-16-148410-7'  , '0000-0000-0000-0004');

insert into autoria values('978-3-16-148410-8'  , '0000-0000-0000-0000');

insert into autoria values('978-3-16-148410-9'  , '0000-0000-0000-0004');

insert into autoria values('978-3-16-148411-0'  , '0000-0000-0000-0002');


insert into genero values('978-3-16-148410-0'  , 'policial');
insert into genero values('978-3-16-148410-0'  , 'romance');

insert into genero values('978-3-16-148410-1'  , 'policial');

insert into genero values('978-3-16-148410-2'  , 'policial');
insert into genero values('978-3-16-148410-2'  , 'romance');

insert into genero values('978-3-16-148410-3'  , 'policial');
insert into genero values('978-3-16-148410-3'  , 'Biografia');

insert into genero values('978-3-16-148410-4'  , 'policial');
insert into genero values('978-3-16-148410-4'  , 'mistério');

insert into genero values('978-3-16-148410-5'  , 'policial');
insert into genero values('978-3-16-148410-5'  , 'mistério');
insert into genero values('978-3-16-148410-5'  , 'drama');

insert into genero values('978-3-16-148410-6'  , 'policial');
insert into genero values('978-3-16-148410-6'  , 'romance');

insert into genero values('978-3-16-148410-7'  , 'policial');

insert into genero values('978-3-16-148410-8'  , 'policial');
insert into genero values('978-3-16-148410-8'  , 'romance');
insert into genero values('978-3-16-148410-8'  , 'mistério');

insert into genero values('978-3-16-148410-9'  , 'policial');
insert into genero values('978-3-16-148410-9'  , 'romance');

insert into genero values('978-3-16-148411-0'  , 'policial');
insert into genero values('978-3-16-148411-0'  , 'mistério');
/* 3c) */
/* 1 membro é amigo de todos*/
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32500');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32501');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32502');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32503');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32504');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32505');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32506');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32507');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32508');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32509');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32510');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32511');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32512');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32513');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32514');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32515');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32516');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32517');
insert into amigo values('af25ji67mn32519' , 'af25ji67mn32518');

/* 5 membros têm 3 amigos*/
insert into amigo values('af25ji67mn32514' , 'af25ji67mn32500');
insert into amigo values('af25ji67mn32514' , 'af25ji67mn32501');
insert into amigo values('af25ji67mn32514' , 'af25ji67mn32502');

insert into amigo values('af25ji67mn32516' , 'af25ji67mn32503');
insert into amigo values('af25ji67mn32516' , 'af25ji67mn32504');
insert into amigo values('af25ji67mn32516' , 'af25ji67mn32506');

insert into amigo values('af25ji67mn32511' , 'af25ji67mn32503');
insert into amigo values('af25ji67mn32511' , 'af25ji67mn32510');
insert into amigo values('af25ji67mn32511' , 'af25ji67mn32509');

insert into amigo values('af25ji67mn32503' , 'af25ji67mn32501');
insert into amigo values('af25ji67mn32503' , 'af25ji67mn32504');
insert into amigo values('af25ji67mn32503' , 'af25ji67mn32508');

insert into amigo values('af25ji67mn32501' , 'af25ji67mn32505');
insert into amigo values('af25ji67mn32501' , 'af25ji67mn32512');
insert into amigo values('af25ji67mn32501' , 'af25ji67mn32508');

/* 3d) */

/* Todos os membros gostam de pelo menos um livro NOTA: Faz sentido ter lido para puder gostar.*/
insert into gosta values('af25ji67mn32500' , '978-3-16-148410-3' );
insert into gosta values('af25ji67mn32501' , '978-3-16-148410-9');
insert into gosta values('af25ji67mn32502' , '978-3-16-148410-2');
insert into gosta values('af25ji67mn32503' , '978-3-16-148410-7');
insert into gosta values('af25ji67mn32504' , '978-3-16-148410-8'); 
insert into gosta values('af25ji67mn32505' , '978-3-16-148410-4'); 
insert into gosta values('af25ji67mn32506' , '978-3-16-148410-9');
insert into gosta values('af25ji67mn32507' , '978-3-16-148410-5');
insert into gosta values('af25ji67mn32508' , '978-3-16-148410-1'); 
insert into gosta values('af25ji67mn32509' , '978-3-16-148410-0');
insert into gosta values('af25ji67mn32510' , '978-3-16-148410-2');
insert into gosta values('af25ji67mn32511' , '978-3-16-148410-5');
insert into gosta values('af25ji67mn32512' , '978-3-16-148410-8');
insert into gosta values('af25ji67mn32513' , '978-3-16-148410-6');
insert into gosta values('af25ji67mn32514' , '978-3-16-148410-3');
insert into gosta values('af25ji67mn32515' , '978-3-16-148410-0');
insert into gosta values('af25ji67mn32516' , '978-3-16-148410-8');
insert into gosta values('af25ji67mn32517' , '978-3-16-148410-7');
insert into gosta values('af25ji67mn32518' , '978-3-16-148410-9');
insert into gosta values('af25ji67mn32519' , '978-3-16-148410-4'); 
insert into gosta values('af25ji67mn32502' , '978-3-16-148410-9');
insert into gosta values('af25ji67mn32502' , '978-3-16-148410-4'); 
insert into gosta values('af25ji67mn32502' , '978-3-16-148410-3');
insert into gosta values('af25ji67mn32502' , '978-3-16-148410-7');
insert into gosta values('af25ji67mn32518' , '978-3-16-148410-6');
insert into gosta values('af25ji67mn32500' , '978-3-16-148410-0' );

/* Pelo menos UM membro deve gostar de todos os livros de UM autor */ 
/* José Saramago*/
insert into gosta values('af25ji67mn32504' , '978-3-16-148410-0'); 
insert into gosta values('af25ji67mn32504' , '978-3-16-148410-6');
insert into gosta values('af25ji67mn32508' , '978-3-16-148410-0'); 
insert into gosta values('af25ji67mn32508' , '978-3-16-148410-6');
/* Francisco José Viegas */
insert into gosta values('af25ji67mn32513' , '978-3-16-148410-1'); 
insert into gosta values('af25ji67mn32513' , '978-3-16-148410-4'); 
insert into gosta values('af25ji67mn32513' , '978-3-16-148411-0'); 
insert into gosta values('af25ji67mn32518' , '978-3-16-148410-1'); 
insert into gosta values('af25ji67mn32518' , '978-3-16-148410-4');
insert into gosta values('af25ji67mn32518' , '978-3-16-148411-0');
insert into gosta values('af25ji67mn32500' , '978-3-16-148410-1'); 
insert into gosta values('af25ji67mn32500' , '978-3-16-148410-4');
insert into gosta values('af25ji67mn32500' , '978-3-16-148411-0');

/* todos os membros devem ter lido pelo menos 3 livros */
insert into leu values('af25ji67mn32500' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32500' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32500' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32500' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32500' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32500' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32500' , '978-3-16-148410-3' );

insert into leu values('af25ji67mn32501' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32501' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32501' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32501' , '978-3-16-148410-8' );
insert into leu values('af25ji67mn32501' , '978-3-16-148410-9' );

insert into leu values('af25ji67mn32502' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32502' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32502' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32502' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32502' , '978-3-16-148410-2' );

insert into leu values('af25ji67mn32503' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32503' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32503' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32503' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32503' , '978-3-16-148410-0' );

insert into leu values('af25ji67mn32504' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32504' , '978-3-16-148410-8' );

insert into leu values('af25ji67mn32505' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32505' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32505' , '978-3-16-148410-5' );

insert into leu values('af25ji67mn32506' , '978-3-16-148410-8' );
insert into leu values('af25ji67mn32506' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32506' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32506' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32506' , '978-3-16-148410-6' );

insert into leu values('af25ji67mn32507' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32507' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32507' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32507' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32507' , '978-3-16-148411-0' );

insert into leu values('af25ji67mn32508' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32508' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32508' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32508' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32508' , '978-3-16-148410-1' );

insert into leu values('af25ji67mn32509' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32509' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32509' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32509' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32509' , '978-3-16-148410-0' );

insert into leu values('af25ji67mn32510' , '978-3-16-148410-8' );
insert into leu values('af25ji67mn32510' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32510' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32510' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32510' , '978-3-16-148410-2' );

insert into leu values('af25ji67mn32511' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32511' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32511' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32511' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32511' , '978-3-16-148410-8' );

insert into leu values('af25ji67mn32512' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32512' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32512' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32512' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32512' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32512' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32512' , '978-3-16-148410-8' );

insert into leu values('af25ji67mn32513' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32513' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32513' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32513' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32513' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32513' , '978-3-16-148411-0' );

insert into leu values('af25ji67mn32514' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32514' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32514' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32514' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32514' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32514' , '978-3-16-148410-9' );

insert into leu values('af25ji67mn32515' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32515' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32515' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32515' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32515' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32515' , '978-3-16-148410-4' );

insert into leu values('af25ji67mn32516' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32516' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32516' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32516' , '978-3-16-148410-0' );
insert into leu values('af25ji67mn32516' , '978-3-16-148410-8' );

insert into leu values('af25ji67mn32517' , '978-3-16-148410-7' );
insert into leu values('af25ji67mn32517' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32517' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32517' , '978-3-16-148411-0' );

insert into leu values('af25ji67mn32518' , '978-3-16-148410-8' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-2' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-4' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-3' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-5' );
insert into leu values('af25ji67mn32518' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32518' , '978-3-16-148410-9' );

insert into leu values('af25ji67mn32519' , '978-3-16-148410-6' );
insert into leu values('af25ji67mn32519' , '978-3-16-148410-1' );
insert into leu values('af25ji67mn32519' , '978-3-16-148410-8' );
insert into leu values('af25ji67mn32519' , '978-3-16-148410-9' );
insert into leu values('af25ji67mn32519' , '978-3-16-148411-0' );
insert into leu values('af25ji67mn32519' , '978-3-16-148410-4' );



/* 3e) */
/* Foi adicionado o membro com idmemb "oleitor" para responder às alíneas da questão 4*/
insert into membro values('Sr.Oleitor' , 'oleitor' , 'Portugal');


insert into amigo values('oleitor' , 'af25ji67mn32500');
insert into amigo values('oleitor' , 'af25ji67mn32501');
insert into amigo values('oleitor' , 'af25ji67mn32506');
insert into amigo values('oleitor' , 'af25ji67mn32508');
insert into amigo values('oleitor' , 'af25ji67mn32512');
insert into amigo values('oleitor' , 'af25ji67mn32513');
insert into amigo values('oleitor' , 'af25ji67mn32514');
insert into amigo values('oleitor' , 'af25ji67mn32515');
insert into amigo values('oleitor' , 'af25ji67mn32518');
insert into amigo values('oleitor' , 'af25ji67mn32519');


insert into gosta values('oleitor' , '978-3-16-148410-9');
insert into gosta values('oleitor' , '978-3-16-148411-0'); 
insert into gosta values('oleitor' , '978-3-16-148410-4');
insert into gosta values('oleitor' , '978-3-16-148410-8');
insert into gosta values('oleitor' , '978-3-16-148410-6');

insert into leu values('oleitor' , '978-3-16-148410-9');
insert into leu values('oleitor' , '978-3-16-148410-0');
insert into leu values('oleitor' , '978-3-16-148411-0');
insert into leu values('oleitor' , '978-3-16-148410-6');
insert into leu values('oleitor' , '978-3-16-148410-3');
insert into leu values('oleitor' , '978-3-16-148410-4');
insert into leu values('oleitor' , '978-3-16-148410-8');
insert into leu values('oleitor' , '978-3-16-148410-1');