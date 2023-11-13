CREATE TABLE transactions (
  id bigint NOT NULL AUTO_INCREMENT UNIQUE,
  date date NOT NULL,
  expiration date  NOT NULL,
  transaction_type varchar(20) NOT NULL,
  amount int,
  price decimal(15,2),
  program_sender_id bigint,
  program_receiver_id bigint,
  user_id bigint,
  miles_id bigint,

  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (miles_id) REFERENCES miles(id),
  FOREIGN KEY (program_sender_id) REFERENCES programs(id),
  FOREIGN KEY (program_receiver_id) REFERENCES programs(id)
);