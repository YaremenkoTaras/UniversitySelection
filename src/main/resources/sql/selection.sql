-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema selection
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `selection` ;

-- -----------------------------------------------------
-- Schema selection
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `selection` DEFAULT CHARACTER SET utf8 ;
USE `selection` ;

-- -----------------------------------------------------
-- Table `selection`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`user_role` ;

CREATE TABLE IF NOT EXISTS `selection`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `role` ENUM('ADMIN', 'USER', 'GUEST') NULL,
  PRIMARY KEY (`user_role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`user` ;

CREATE TABLE IF NOT EXISTS `selection`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `sex` ENUM('M', 'F') NULL,
  `birth` DATE NULL,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(120) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `user_role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_user_user_type_idx` (`user_role_id` ASC),
  CONSTRAINT `fk_user_user_type`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `selection`.`user_role` (`user_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`faculty` ;

CREATE TABLE IF NOT EXISTS `selection`.`faculty` (
  `faculty_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `number_of_student` INT NULL,
  `short_name` VARCHAR(10) NULL,
  PRIMARY KEY (`faculty_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`subject` ;

CREATE TABLE IF NOT EXISTS `selection`.`subject` (
  `subject_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`subject_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`applicant_mark`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`applicant_mark` ;

CREATE TABLE IF NOT EXISTS `selection`.`applicant_mark` (
  `applicant_mark_id` INT NOT NULL AUTO_INCREMENT,
  `mark` DECIMAL(3) NULL,
  `user_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  PRIMARY KEY (`applicant_mark_id`),
  INDEX `fk_applicant_mark_user1_idx` (`user_id` ASC),
  INDEX `fk_applicant_mark_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_applicant_mark_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `selection`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_applicant_mark_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `selection`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`faculty_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`faculty_subject` ;

CREATE TABLE IF NOT EXISTS `selection`.`faculty_subject` (
  `faculty_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `min_mark` DECIMAL(3) NULL,
  PRIMARY KEY (`faculty_id`, `subject_id`),
  INDEX `fk_faculty_subjects_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_faculty_subjects_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `selection`.`faculty` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faculty_subjects_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `selection`.`subject` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`application_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`application_status` ;

CREATE TABLE IF NOT EXISTS `selection`.`application_status` (
  `application_status_id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('ACCEPT', 'PROCESS', 'DECLINE') NULL DEFAULT 'PROCESS',
  PRIMARY KEY (`application_status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `selection`.`application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `selection`.`application` ;

CREATE TABLE IF NOT EXISTS `selection`.`application` (
  `application_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `description` VARCHAR(100) NULL,
  `overall` INT NULL DEFAULT 0,
  `faculty_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `application_status_id` INT NOT NULL,
  PRIMARY KEY (`application_id`),
  INDEX `fk_aplication_faculty1_idx` (`faculty_id` ASC),
  INDEX `fk_aplication_user1_idx` (`user_id` ASC),
  INDEX `fk_application_application_status1_idx` (`application_status_id` ASC),
  CONSTRAINT `fk_aplication_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `selection`.`faculty` (`faculty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aplication_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `selection`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_application_application_status1`
    FOREIGN KEY (`application_status_id`)
    REFERENCES `selection`.`application_status` (`application_status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
