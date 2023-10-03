CREATE TABLE users_roles (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT FkidUsuarioUP FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FKidPapelUP FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- admin
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

-- gerente
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
