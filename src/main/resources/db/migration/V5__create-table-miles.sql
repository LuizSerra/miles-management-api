CREATE TABLE miles (

id bigint not null auto_increment,
amount int,
price decimal(15,2),
expiration date,
program_id bigint,

primary key(id),
FOREIGN KEY (program_id) REFERENCES programs(id)
);