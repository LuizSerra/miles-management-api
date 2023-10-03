CREATE TABLE roles (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  name varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

-- papel
INSERT INTO roles (name) VALUES ('ROLE_ADM');
INSERT INTO roles (name) VALUES ('ROLE_USUARIO');
