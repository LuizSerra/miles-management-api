CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  active boolean NOT NULL,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL UNIQUE,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO users (name, email, password, active) VALUES ('ADMINISTRADOR', 'admin@softplan.com', '$2a$12$HwSYmZ7cl0R8MCTEaqZQe.XrSweX35mnsBT3kRoCTqquk4mBGChYe', true );
INSERT INTO users (name, email, password, active) VALUES ('USUARIO_TESTE', 'gerente@softplan.com', '$2a$12$HwSYmZ7cl0R8MCTEaqZQe.XrSweX35mnsBT3kRoCTqquk4mBGChYe', true );
