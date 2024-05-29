
/*Criar banco de dados*/
CREATE SCHEMA `autenticador` ;

/*Criar tabela*/
CREATE TABLE `autenticador`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(15) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`, `user`));
/*Inserir dados*/
INSERT INTO `autenticador`.`usuarios` (`id`, `user`, `Password`) VALUES ('1', 'Admin', '1415');


CREATE TABLE `autenticador`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(100) NOT NULL,
  `Endereco` VARCHAR(100) NOT NULL,
  `Uf` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Nome`));
