-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `dni` VARCHAR(8) NOT NULL,
  `celular` VARCHAR(15) NOT NULL,
  `codigoPucp` VARCHAR(10) NOT NULL,
  `correoPucp` VARCHAR(50) NOT NULL,
  `categorias` VARCHAR(50) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`zonaPucp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`zonaPucp` (
  `idzonaPucp` INT NOT NULL,
  `nombreZona` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idzonaPucp`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`incidencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`incidencias` (
  `idIncidencias` INT NOT NULL AUTO_INCREMENT,
  `idzonaPucp` INT NOT NULL,
  `tipo` VARCHAR(100) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `ubicacion` VARCHAR(200) NOT NULL,
  `foto` BLOB NOT NULL,
  `destacado` INT(3) NOT NULL,
  `fechaHora` DATETIME NOT NULL,
  `anonimo` BIT(1) NOT NULL,
  `urgencia` VARCHAR(20) NOT NULL,
  `estadoIncidencia` VARCHAR(30) NOT NULL,
  `numEstrellas` INT NULL,
  `usuarios_idUsuarios` INT NOT NULL,
  `usuarios_idUsuarios1` INT NULL,
  PRIMARY KEY (`idIncidencias`),
  INDEX `fk_incidencias_zonaPucp1_idx` (`idzonaPucp` ASC) VISIBLE,
  INDEX `fk_incidencias_usuarios1_idx` (`usuarios_idUsuarios` ASC) VISIBLE,
  INDEX `fk_incidencias_usuarios2_idx` (`usuarios_idUsuarios1` ASC) VISIBLE,
  CONSTRAINT `fk_incidencias_zonaPucp1`
    FOREIGN KEY (`idzonaPucp`)
    REFERENCES `mydb`.`zonaPucp` (`idzonaPucp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incidencias_usuarios1`
    FOREIGN KEY (`usuarios_idUsuarios`)
    REFERENCES `mydb`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incidencias_usuarios2`
    FOREIGN KEY (`usuarios_idUsuarios1`)
    REFERENCES `mydb`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comentarios` (
  `contenido` VARCHAR(500) NOT NULL,
  `idIncidencias` INT NOT NULL,
  `idComentario` VARCHAR(45) NOT NULL,
  `usuarios_idUsuarios` INT NOT NULL,
  INDEX `fk_IS_has_incidencias_incidencias1_idx` (`idIncidencias` ASC) VISIBLE,
  PRIMARY KEY (`idComentario`),
  INDEX `fk_comentarios_usuarios1_idx` (`usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_IS_has_incidencias_incidencias1`
    FOREIGN KEY (`idIncidencias`)
    REFERENCES `mydb`.`incidencias` (`idIncidencias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentarios_usuarios1`
    FOREIGN KEY (`usuarios_idUsuarios`)
    REFERENCES `mydb`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios_por_incidencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios_por_incidencias` (
  `usuarios_idUsuarios` INT NOT NULL,
  `incidencias_idIncidencias` INT NOT NULL,
  PRIMARY KEY (`usuarios_idUsuarios`, `incidencias_idIncidencias`),
  INDEX `fk_usuarios_has_incidencias_incidencias1_idx` (`incidencias_idIncidencias` ASC) VISIBLE,
  INDEX `fk_usuarios_has_incidencias_usuarios1_idx` (`usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_has_incidencias_usuarios1`
    FOREIGN KEY (`usuarios_idUsuarios`)
    REFERENCES `mydb`.`usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_incidencias_incidencias1`
    FOREIGN KEY (`incidencias_idIncidencias`)
    REFERENCES `mydb`.`incidencias` (`idIncidencias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
