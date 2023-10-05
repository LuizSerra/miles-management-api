CREATE TABLE transactions (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  date date NOT NULL,
  transaction_type varchar(20) NOT NULL,
  amount int,
  price decimal(15,2),
  program_id bigint,
  
  PRIMARY KEY (id),
  FOREIGN KEY (program_id) REFERENCES programs(id)
);