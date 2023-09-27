CREATE TABLE papeis (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  nome varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

-- papel
INSERT INTO papeis (id, nome) VALUES (1, 'ROLE_ADM');
INSERT INTO papeis (id, nome) VALUES (2, 'ROLE_USUARIO');
