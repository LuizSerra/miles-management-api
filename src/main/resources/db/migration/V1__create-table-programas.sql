CREATE TABLE programas (

id bigint not null auto_increment,
nome varchar(100) not null unique,
descricao varchar(150),
saldo bigint,
saldo_disponivel bigint,

primary key(id)
 
 );
