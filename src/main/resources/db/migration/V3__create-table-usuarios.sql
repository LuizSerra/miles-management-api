CREATE TABLE usuarios (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  ativo boolean NOT NULL,
  nome varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL UNIQUE,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);



INSERT INTO usuarios (nome, email, senha, ativo) VALUES ('ADMINISTRADOR', 'admin@softplan.com', '$2a$12$HwSYmZ7cl0R8MCTEaqZQe.XrSweX35mnsBT3kRoCTqquk4mBGChYe', true );
INSERT INTO usuarios (nome, email, senha, ativo) VALUES ('USUARIO', 'gerente@softplan.com', '$2a$12$HwSYmZ7cl0R8MCTEaqZQe.XrSweX35mnsBT3kRoCTqquk4mBGChYe', true );
