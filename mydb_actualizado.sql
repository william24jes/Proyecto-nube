-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hr` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hr` ;

-- -----------------------------------------------------
-- Table `hr`.`regions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`regions` (
  `region_id` DECIMAL(22,0) NOT NULL,
  `region_name` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`region_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`countries` (
  `country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `country_name` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `region_id` DECIMAL(22,0) NULL DEFAULT NULL,
  PRIMARY KEY (`country_id`),
  INDEX `countr_reg_fk` (`region_id` ASC) VISIBLE,
  CONSTRAINT `countr_reg_fk`
    FOREIGN KEY (`region_id`)
    REFERENCES `hr`.`regions` (`region_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`locations` (
  `location_id` INT NOT NULL,
  `street_address` VARCHAR(40) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `postal_code` VARCHAR(12) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `city` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `state_province` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `country_id` CHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  INDEX `loc_city_ix` (`city` ASC) VISIBLE,
  INDEX `loc_country_ix` (`country_id` ASC) VISIBLE,
  INDEX `loc_state_province_ix` (`state_province` ASC) VISIBLE,
  INDEX `loc_c_id_fk` (`country_id` ASC) VISIBLE,
  CONSTRAINT `loc_c_id_fk`
    FOREIGN KEY (`country_id`)
    REFERENCES `hr`.`countries` (`country_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`jobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`jobs` (
  `job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `job_title` VARCHAR(35) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `min_salary` INT NULL DEFAULT NULL,
  `max_salary` INT NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`employees` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `last_name` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `email` VARCHAR(25) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `phone_number` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `hire_date` DATETIME NOT NULL,
  `job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `salary` DECIMAL(8,2) NULL DEFAULT NULL,
  `commission_pct` DECIMAL(2,2) NULL DEFAULT NULL,
  `manager_id` INT NULL DEFAULT NULL,
  `department_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `emp_email_uk` (`email` ASC) VISIBLE,
  INDEX `emp_department_ix` (`department_id` ASC) VISIBLE,
  INDEX `emp_job_ix` (`job_id` ASC) VISIBLE,
  INDEX `emp_manager_ix` (`manager_id` ASC) VISIBLE,
  INDEX `emp_name_ix` (`last_name` ASC, `first_name` ASC) VISIBLE,
  INDEX `emp_job_fk` (`job_id` ASC) VISIBLE,
  CONSTRAINT `emp_dept_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `hr`.`departments` (`department_id`),
  CONSTRAINT `emp_job_fk`
    FOREIGN KEY (`job_id`)
    REFERENCES `hr`.`jobs` (`job_id`),
  CONSTRAINT `emp_manager_fk`
    FOREIGN KEY (`manager_id`)
    REFERENCES `hr`.`employees` (`employee_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 207
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`departments` (
  `department_id` INT NOT NULL,
  `department_name` VARCHAR(30) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `manager_id` INT NULL DEFAULT NULL,
  `location_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  INDEX `dept_location_ix` (`location_id` ASC) VISIBLE,
  INDEX `dept_mgr_fk` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `dept_loc_fk`
    FOREIGN KEY (`location_id`)
    REFERENCES `hr`.`locations` (`location_id`),
  CONSTRAINT `dept_mgr_fk`
    FOREIGN KEY (`manager_id`)
    REFERENCES `hr`.`employees` (`employee_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`employees_credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`employees_credentials` (
  `employee_id` INT NOT NULL,
  `email` VARCHAR(25) NOT NULL,
  `password` VARCHAR(25) NOT NULL,
  `password_hashed` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `fk_table1_employees1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employees` (`employee_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hr`.`job_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hr`.`job_history` (
  `employee_id` INT NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  `job_id` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `department_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`, `start_date`),
  INDEX `jhist_department_ix` (`department_id` ASC) VISIBLE,
  INDEX `jhist_employee_ix` (`employee_id` ASC) VISIBLE,
  INDEX `jhist_job_ix` (`job_id` ASC) VISIBLE,
  INDEX `jhist_job_fk` (`job_id` ASC) VISIBLE,
  CONSTRAINT `jhist_dept_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `hr`.`departments` (`department_id`),
  CONSTRAINT `jhist_emp_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hr`.`employees` (`employee_id`),
  CONSTRAINT `jhist_job_fk`
    FOREIGN KEY (`job_id`)
    REFERENCES `hr`.`jobs` (`job_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `dni` VARCHAR(8) NOT NULL,
  `celular` VARCHAR(15) NOT NULL,
  `codigoPucp` VARCHAR(10) NOT NULL,
  `correoPucp` VARCHAR(50) NOT NULL,
  `categoria` VARCHAR(50) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`zonapucp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`zonapucp` (
  `idzonaPucp` INT NOT NULL AUTO_INCREMENT,
  `nombreZona` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idzonaPucp`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`incidencias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`incidencias` (
  `idIncidencia` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idSeguridad` INT NULL DEFAULT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(200) NOT NULL,
  `idzonaPucp` INT NOT NULL,
  `tipo` VARCHAR(100) NOT NULL,
  `ubicacion` VARCHAR(200) NULL DEFAULT NULL,
  `foto` BLOB NULL DEFAULT NULL,
  `destacado` INT NOT NULL,
  `fechaHora` DATETIME NOT NULL,
  `anonimo` TINYINT(1) NOT NULL,
  `urgencia` VARCHAR(20) NOT NULL,
  `estadoIncidencia` VARCHAR(30) NOT NULL,
  `numEstrellas` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idIncidencia`),
  INDEX `fk_incidencias_zonaPucp1_idx` (`idzonaPucp` ASC) VISIBLE,
  INDEX `fk_incidencias_usuarios1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_incidencias_usuarios2_idx` (`idSeguridad` ASC) VISIBLE,
  CONSTRAINT `fk_incidencias_usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `mydb`.`usuarios` (`idUsuario`),
  CONSTRAINT `fk_incidencias_usuarios2`
    FOREIGN KEY (`idSeguridad`)
    REFERENCES `mydb`.`usuarios` (`idUsuario`),
  CONSTRAINT `fk_incidencias_zonaPucp1`
    FOREIGN KEY (`idzonaPucp`)
    REFERENCES `mydb`.`zonapucp` (`idzonaPucp`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comentarios` (
  `contenido` VARCHAR(500) NOT NULL,
  `idIncidencia` INT NOT NULL,
  `idComentario` VARCHAR(45) NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idComentario`),
  INDEX `fk_IS_has_incidencias_incidencias1_idx` (`idIncidencia` ASC) VISIBLE,
  INDEX `fk_comentarios_usuarios1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_comentarios_usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `mydb`.`usuarios` (`idUsuario`),
  CONSTRAINT `fk_IS_has_incidencias_incidencias1`
    FOREIGN KEY (`idIncidencia`)
    REFERENCES `mydb`.`incidencias` (`idIncidencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`incidencias_destacadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`incidencias_destacadas` (
  `idUsuario` INT NOT NULL,
  `idIncidencia` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idIncidencia`),
  INDEX `fk_usuarios_has_incidencias_incidencias1_idx` (`idIncidencia` ASC) VISIBLE,
  INDEX `fk_usuarios_has_incidencias_usuarios1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_has_incidencias_incidencias1`
    FOREIGN KEY (`idIncidencia`)
    REFERENCES `mydb`.`incidencias` (`idIncidencia`),
  CONSTRAINT `fk_usuarios_has_incidencias_usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `mydb`.`usuarios` (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
