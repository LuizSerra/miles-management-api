CREATE TABLE `milesmm`.`usuarios_papeis` (
  `usuario_id` bigint NOT NULL,
  `papel_id` bigint NOT NULL,
  KEY `FkidUsuarioUP` (`usuario_id`),
  KEY `FKidPapelUP` (`papel_id`),
  CONSTRAINT `FkidUsuarioUP` FOREIGN KEY (`papel_id`) REFERENCES `papeis` (`id`),
  CONSTRAINT `FKq26gl900j38e97cp7rbhw1nqc` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- admin
INSERT INTO `milesmm`.`usuarios_papeis` (usuario_id, papel_id) VALUES (1, 1);

-- gerente
INSERT INTO `milesmm`.`usuarios_papeis` (usuario_id, papel_id) VALUES (2, 2);
