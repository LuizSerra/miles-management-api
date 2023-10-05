CREATE TABLE transactions (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  date date NOT NULL,
  transaction_type varchar(20) NOT NULL,
  amount int,
  price decimal(15,2),
  source_program_id bigint,
  target_program_id bigint,
  
	PRIMARY KEY (id),
 	FOREIGN KEY (source_program_id) REFERENCES programs(id),
 	FOREIGN KEY (target_program_id) REFERENCES programs(id)
);