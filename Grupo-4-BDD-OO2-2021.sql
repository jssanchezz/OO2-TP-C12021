create schema `obj2_tp`;

use `obj2_tp`;

create table `user_role`(
 `id` int AUTO_INCREMENT primary key,
 `createdat` datetime,
 `role` varchar(100),
 `updatedat` datetime
 );

create table `user`(
`id` int AUTO_INCREMENT primary key,
`email` varchar(45),
`createdat` datetime,
`dni` varchar(8),
`name` varchar(45),
`surname` varchar(45),
`type_doc` varchar(255),
`updatedat` datetime,
`user_name` varchar(25),
`user_password` varchar(100),
`user_role_id`int,
foreign key (`user_role_id`) references `user_role` (`id`)
);
 
 -- CREACION DE ROL ADMINISTRADOR
 insert into user_role values(1,"2021-05-26 10:00:00",
 'ROLE_ADMIN',"2021-05-26 10:00:00");
 
 -- CREACION DE ROL AUDITOR
 insert into user_role values(2,"2021-05-26 10:00:00",
 'ROLE_AUDITOR',"2021-05-26 10:00:00");
 
 -- CREACION DE USUARIO ADMINISTRADOR
 -- PASS: spring2021
 insert into user values(1,'gustavo@gmail.com',"2021-05-26 10:00:00",
'12345678','Gustavo','Siciliano','DNI',"2021-05-26 10:00:00",
'Gus','$2a$04$xoszyR7d.F/EjPAEiuqN7OPZCFv10Yu/LJ36ytvY39nCsjbFcXqc2',1);

-- CREACION DE USUARIO AUDITOR
-- PASS: hibernate
 insert into user values(2,'romina@gmail.com',"2021-05-26 10:00:00",
'87654321','Romina','Mansilla','DNI',"2021-05-26 10:00:00",
'Romi','$2a$04$wTGoL/HvuR2LDnhoNv4qY.UO2S/kdEVTP6unESkABUFan9is5LVCm',2);