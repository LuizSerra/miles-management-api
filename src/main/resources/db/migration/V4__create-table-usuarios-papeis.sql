CREATE TABLE usuarios_papeis (
  usuario_id bigint NOT NULL,
  papel_id bigint NOT NULL,
  PRIMARY KEY (usuario_id, papel_id),
  CONSTRAINT FKidPapelUP FOREIGN KEY (papel_id) REFERENCES papeis (id),
  CONSTRAINT FkidUsuarioUP FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);

-- admin
INSERT INTO usuarios_papeis (usuario_id, papel_id) VALUES (1, 1);

-- gerente
INSERT INTO usuarios_papeis (usuario_id, papel_id) VALUES (2, 2);
