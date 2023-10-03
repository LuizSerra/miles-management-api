CREATE TABLE programs (

id bigint not null auto_increment,
name varchar(100) not null unique,
description varchar(150),
balance bigint,
balance_available bigint,

primary key(id)
 
);

INSERT INTO programs(name, description, balance, balance_available) VALUES ("LIVELO", "Programa de Pontos Livelo", 3000, 1000);