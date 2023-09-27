CREATE TABLE operacoes (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  data date NOT NULL,
  tipo varchar(20) NOT NULL,
  quantidade int,
  valor decimal(15,2),
  programa_origem_id bigint,
  programa_destino_id bigint,
  
	PRIMARY KEY (id),
 	FOREIGN KEY (programa_origem_id) REFERENCES programas(id),
 	FOREIGN KEY (programa_destino_id) REFERENCES programas(id)
);