CREATE TABLE `milesmm`.`papeis` (
  `id` bigint NOT NULL AUTO_INCREMENT UNIQUE,
  `nome` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- papel
INSERT INTO `milesmm`.`papeis` (id, nome) VALUES (1, 'ROLE_ADM');
INSERT INTO `milesmm`.`papeis` (id, nome) VALUES (2, 'ROLE_USUARIO');
