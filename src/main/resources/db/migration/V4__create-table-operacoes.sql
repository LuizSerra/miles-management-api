CREATE TABLE `milesmm`.`operacoes` (
  `id` bigint NOT NULL AUTO_INCREMENT UNIQUE,
  `data` date NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `quantidade`int,
  `valor` double(15,2),
  `programa_origem_id`bigint,
  `programa_destino_id`bigint,
  
	PRIMARY KEY (`id`),
 	FOREIGN KEY (programa_origem_id) REFERENCES `milesmm`.`programas`(id),
 	FOREIGN KEY (programa_destino_id) REFERENCES `milesmm`.`programas`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;