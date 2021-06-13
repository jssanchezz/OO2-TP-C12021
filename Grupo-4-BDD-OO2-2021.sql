-- SCRIPT CREACION BD - GRUPO 04
-- USUARIOS TEST
-- ADMINISTRADOR, user: admin - pass: 123456
-- AUDITOR, user: auditor - pass: 123456

-- Creación de tablas

create schema `obj2_tp`;

use `obj2_tp`;

CREATE TABLE `lugar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo_postal` varchar(10) NOT NULL,
  `createdat` datetime DEFAULT NULL,
  `lugar` varchar(45) NOT NULL,
  `updatedat` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dni` bigint DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `person_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK73n1kv4l36ytnjo9if20v1li4` (`person_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso_diario` (
  `motivo` varchar(255) DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso_lugar` (
  `fk_permiso` int NOT NULL,
  `fk_lugar` int NOT NULL,
  PRIMARY KEY (`fk_permiso`,`fk_lugar`),
  KEY `FKi7smpy34xrwwjmoiobr642xk6` (`fk_lugar`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso_periodo` (
  `cant_dias` int DEFAULT NULL,
  `vacaciones` bit(1) DEFAULT NULL,
  `id` int NOT NULL,
  `rodado_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpl3oq8w0b13yy9ge2cbk069jd` (`rodado_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime DEFAULT NULL,
  `role` varchar(100) NOT NULL,
  `updatedat` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rodado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime DEFAULT NULL,
  `dominio` varchar(7) NOT NULL,
  `updatedat` datetime DEFAULT NULL,
  `vehiculo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime DEFAULT NULL,
  `dni` varchar(8) NOT NULL,
  `email` varchar(70) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `type_doc` varchar(255) DEFAULT NULL,
  `updatedat` datetime DEFAULT NULL,
  `user_name` varchar(25) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserción de datos

-- ROLES
INSERT INTO `obj2_tp`.`user_role` (`id`,`createdat`,`role`,`enabled`)
VALUES(1,now(),'ROLE_ADMIN',1);

INSERT INTO `obj2_tp`.`user_role` (`id`,`createdat`,`role`,`enabled`)
VALUES(2,now(),'ROLE_AUDITOR',1);

-- USUARIOS
INSERT INTO `obj2_tp`.`user`(`id`,`createdat`,`dni`,`email`,`enabled`,`name`,`surname`,`type_doc`,`user_name`,`user_password`,`user_role_id`)
VALUES(1,NOW(),'10000000','admin@gmail.com',1,'Admin','Administrador','DNI','admin','$2a$10$47nUVb6oTrUSvZO14NBBs.g/hD4ecMWfk3Td.0w9/zftynsMxM/LO',1);

INSERT INTO `obj2_tp`.`user`(`id`,`createdat`,`dni`,`email`,`enabled`,`name`,`surname`,`type_doc`,`user_name`,`user_password`,`user_role_id`)
VALUES(2,NOW(),'10000001','auditor@gmail.com',1,'Auditor','Auditor','DNI','auditor','$2a$10$47nUVb6oTrUSvZO14NBBs.g/hD4ecMWfk3Td.0w9/zftynsMxM/LO',2);

-- LUGARES
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (1, "1846",now(), "ADROGUÉ");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (2, "1846",now(), "ALMIRANTE BROWN");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (3, "1852",now(), "BURZACO");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (4, "1849",now(), "CLAYPOLE");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (5, "1856",now(), "GLEW");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (6, "1846",now(), "JOSÉ MARMOL");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (7, "1854",now(), "LONGCHAMPS");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (8, "1852",now(), "MINISTRO RIVADAVIA");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (9, "1847",now(), "RAFAEL CALZADA");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (10, "1870",now(), "AVELLANEDA");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (11, "1871",now(), "DOCK SUD");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (12, "1872",now(), "SARANDÍ");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (13, "1875",now(), "WILDE");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (14, "1824",now(), "LANÚS");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (15, "1828",now(), "BANFIELD");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (16, "1836",now(), "LLAVALLOL");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (17, "1832",now(), "LOMAS DE ZAMORA");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (18, "1834",now(), "TEMPERLEY");
INSERT INTO `obj2_tp`.`lugar`(`id`,`codigo_postal`,`createdat`,`lugar`) VALUES (19, "1834",now(), "TURDERA");

-- PERSONAS

INSERT INTO `obj2_tp`.`person`(`id`,`dni`,`name`,`surname`)
VALUES(1,'10000000','Juan','Sanchez');
INSERT INTO `obj2_tp`.`person`(`id`,`dni`,`name`,`surname`)
VALUES(2,'11000000','Federico','Procs');
INSERT INTO `obj2_tp`.`person`(`id`,`dni`,`name`,`surname`)
VALUES(3,'12000000','Rolando','Rapali');

