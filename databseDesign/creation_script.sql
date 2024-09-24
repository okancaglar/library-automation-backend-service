-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`customer` (
  `id_customer` INT NOT NULL,
  `first_name_customer` VARCHAR(50) NOT NULL,
  `last_name_customer` VARCHAR(50) NOT NULL,
  `registiration_date_customer` DATE NOT NULL,
  PRIMARY KEY (`id_customer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book` (
  `id_book` INT NOT NULL,
  `name_book` VARCHAR(150) NOT NULL,
  `adding_date_book` DATE NOT NULL,
  `blurb_book` LONGTEXT NULL,
  `rental_price_book` DOUBLE NOT NULL,
  PRIMARY KEY (`id_book`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`rented_books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`rented_books` (
  `id_book` INT NOT NULL,
  `customer_id_customer` INT NOT NULL,
  `rental_date` DATE NOT NULL,
  `return_date` DATE NULL,
  INDEX `fk_rented_books_book1_idx` (`id_book` ASC) VISIBLE,
  PRIMARY KEY (`id_book`, `customer_id_customer`, `rental_date`),
  INDEX `fk_rented_books_customer1_idx` (`customer_id_customer` ASC) VISIBLE,
  CONSTRAINT `fk_rented_books_book1`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`book` (`id_book`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rented_books_customer1`
    FOREIGN KEY (`customer_id_customer`)
    REFERENCES `library`.`customer` (`id_customer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`book_genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book_genres` (
  `id_book` INT NOT NULL,
  `genre_name` VARCHAR(100) NOT NULL,
  INDEX `fk_book_genres_book1_idx` (`id_book` ASC) VISIBLE,
  PRIMARY KEY (`id_book`, `genre_name`),
  CONSTRAINT `fk_book_genres_book1`
    FOREIGN KEY (`id_book`)
    REFERENCES `library`.`book` (`id_book`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`role` (
  `name_role` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`name_role`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`employee` (
  `id_employee` INT NOT NULL,
  `user_password` VARCHAR(100) NOT NULL,
  `first_name_employee` VARCHAR(45) NOT NULL,
  `last_name_employee` VARCHAR(45) NOT NULL,
  `registration_date_employee` DATE NOT NULL,
  `daily_wage_employee` DOUBLE NOT NULL,
  `name_role` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_employee`),
  INDEX `fk_employee_role1_idx` (`name_role` ASC) VISIBLE,
  CONSTRAINT `fk_employee_role1`
    FOREIGN KEY (`name_role`)
    REFERENCES `library`.`role` (`name_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library`.`writer_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`writer_book` (
  `writer_name` VARCHAR(200) NOT NULL,
  `book_id_book` INT NOT NULL,
  PRIMARY KEY (`writer_name`, `book_id_book`),
  INDEX `fk_writer_book_book1_idx` (`book_id_book` ASC) VISIBLE,
  CONSTRAINT `fk_writer_book_book1`
    FOREIGN KEY (`book_id_book`)
    REFERENCES `library`.`book` (`id_book`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
