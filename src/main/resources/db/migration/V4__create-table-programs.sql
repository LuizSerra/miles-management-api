CREATE TABLE programs (

id bigint not null auto_increment,
name varchar(100) not null,
description varchar(150),
balance bigint,
balance_available bigint,
user_id bigint,

primary key(id),
FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO programs(name, description, balance, balance_available, user_id) VALUES ('LIVELO', 'Programa de Pontos Livelo', 3000, 1000, 1);
INSERT INTO programs(name, description, balance, balance_available, user_id) VALUES ('ESFERA', 'Programa de Pontos ESFERA', 53000, 50000, 1);
INSERT INTO programs(name, description, balance, balance_available, user_id) VALUES ('ESFERA', 'Programa de Pontos ESFERA', 53000, 50000, 2);