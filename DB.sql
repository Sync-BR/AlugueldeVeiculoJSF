


CREATE SCHEMA `autenticador`; 
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE `autenticador`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(15) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`, `user`)
);

INSERT INTO `autenticador`.`usuarios` (`id`, `user`, `Password`) 
VALUES ('1', 'Admin', '1415');

CREATE TABLE `autenticador`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(100) NOT NULL,
  `Endereco` VARCHAR(100) NOT NULL,
  `Uf` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Nome`, `Cpf`),
  UNIQUE (`Cpf`)
);

CREATE TABLE `autenticador`.`veiculos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `placa` VARCHAR(45) NOT NULL,
  `fabricante` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `anoModelo` INT NOT NULL,
  `qtdPortas` INT NOT NULL,
  `Acessorios` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `placa`),
   UNIQUE (`placa`)
);


CREATE TABLE `autenticador`.`aluguel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Veiculo` VARCHAR(45) NOT NULL,
  `DataAluguel` DATE NOT NULL,
  `DataEntrega` DATE NOT NULL,
  `Cliente` VARCHAR(45) NOT NULL,
  `Entregue` CHAR NOT NULL,
  `Observacao` VARCHAR(100) NULL,
  `ValorPago` DECIMAL NOT NULL,
  PRIMARY KEY (`id`, `Cliente`));
