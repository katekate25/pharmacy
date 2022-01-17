-- MySQL Script generated by MySQL Workbench
-- Sun Jan 16 01:53:36 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pharmacy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pharmacy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pharmacy` DEFAULT CHARACTER SET utf8 ;
USE `pharmacy` ;

-- -----------------------------------------------------
-- Table `pharmacy`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`roles` (
  `code` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `title_UNIQUE` (`code` ASC) VISIBLE,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  `roles_code` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `telephone_number` VARCHAR(45) NOT NULL,
  `work_place` VARCHAR(100) NULL,
  `specialization` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_users_roles1_idx` (`roles_code` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`roles_code`)
    REFERENCES `pharmacy`.`roles` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`orders` (
  `number` INT NOT NULL AUTO_INCREMENT,
  `clients_id` INT NULL,
  `pharmacists_id` INT NULL,
  `delivery_time` DATETIME(6) NULL,
  `order_status` VARCHAR(45) NULL,
  `payment_status` VARCHAR(45) NULL,
  PRIMARY KEY (`number`),
  INDEX `fk_order_users1_idx` (`clients_id` ASC) VISIBLE,
  INDEX `fk_orders_users1_idx` (`pharmacists_id` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  CONSTRAINT `fk_order_users1`
    FOREIGN KEY (`clients_id`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`pharmacists_id`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`producers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`producers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `factory_name` VARCHAR(200) NOT NULL,
  `producer_country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `factory_name_UNIQUE` (`factory_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`disease_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`disease_groups` (
  `code` VARCHAR(200) NOT NULL,
  UNIQUE INDEX `group_name_UNIQUE` (`code` ASC) VISIBLE,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`medicines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`medicines` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `commercial_name` VARCHAR(45) NOT NULL,
  `international_name` VARCHAR(45) NULL,
  `medicine_form` VARCHAR(45) NULL,
  `dose` INT NULL,
  `price` DOUBLE NOT NULL,
  `serial_number` VARCHAR(20) NOT NULL,
  `expiration_date` DATETIME(6) NULL,
  `arrival_date` DATETIME(6) NOT NULL,
  `invoice_number` VARCHAR(45) NOT NULL,
  `product_arrival` DOUBLE NULL,
  `balance` DOUBLE NULL,
  `prescription_required` TINYINT NULL,
  `producers_id` INT NULL,
  `disease_groups_code` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `serial_number_UNIQUE` (`serial_number` ASC) VISIBLE,
  INDEX `fk_medicines_producers1_idx` (`producers_id` ASC) VISIBLE,
  INDEX `fk_medicines_disease_groups1_idx` (`disease_groups_code` ASC) VISIBLE,
  CONSTRAINT `fk_medicines_producers1`
    FOREIGN KEY (`producers_id`)
    REFERENCES `pharmacy`.`producers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicines_disease_groups1`
    FOREIGN KEY (`disease_groups_code`)
    REFERENCES `pharmacy`.`disease_groups` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`prescriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`prescriptions` (
  `prescription_number` INT NOT NULL AUTO_INCREMENT,
  `medicines_id` INT NOT NULL,
  `number_of_packages` DOUBLE NOT NULL,
  `usage_instruction` VARCHAR(100) NOT NULL,
  `creation_date` DATE NOT NULL,
  `expiration_date` DATE NOT NULL,
  `client_id` INT NOT NULL,
  `orders_number` INT NULL,
  `doctor_id` INT NOT NULL,
  PRIMARY KEY (`prescription_number`),
  INDEX `fk_prescription_users2_idx` (`client_id` ASC) VISIBLE,
  UNIQUE INDEX `prescription_number_UNIQUE` (`prescription_number` ASC) VISIBLE,
  INDEX `fk_prescriptions_orders1_idx` (`orders_number` ASC) VISIBLE,
  INDEX `fk_prescriptions_medicines1_idx` (`medicines_id` ASC) VISIBLE,
  INDEX `fk_prescriptions_users1_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_prescription_users2`
    FOREIGN KEY (`client_id`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prescriptions_orders1`
    FOREIGN KEY (`orders_number`)
    REFERENCES `pharmacy`.`orders` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prescriptions_medicines1`
    FOREIGN KEY (`medicines_id`)
    REFERENCES `pharmacy`.`medicines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prescriptions_users1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`order_entry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`order_entry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `package_amount` DOUBLE NOT NULL,
  `order_number` INT NULL,
  `medicines_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_entry_order1_idx` (`order_number` ASC) VISIBLE,
  INDEX `fk_order_entry_medicines1_idx` (`medicines_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_order_entry_order1`
    FOREIGN KEY (`order_number`)
    REFERENCES `pharmacy`.`orders` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_entry_medicines1`
    FOREIGN KEY (`medicines_id`)
    REFERENCES `pharmacy`.`medicines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pharmacy`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(300) NOT NULL,
  `date` DATE NULL,
  `recipient` INT NOT NULL,
  `sender` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_table1_users1_idx` (`recipient` ASC) VISIBLE,
  INDEX `fk_table1_users2_idx` (`sender` ASC) VISIBLE,
  CONSTRAINT `fk_table1_users1`
    FOREIGN KEY (`recipient`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_users2`
    FOREIGN KEY (`sender`)
    REFERENCES `pharmacy`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




INSERT INTO `pharmacy`.`roles` (`code`) VALUES ('PHARMACIST');
INSERT INTO `pharmacy`.`roles` (`code`) VALUES ('CUSTOMER');
INSERT INTO `pharmacy`.`roles` (`code`) VALUES ('DOCTOR');
INSERT INTO pharmacy.producers (factory_name, producer_country) VALUES ('SUN', 'Индия');

INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('sidorov_n', '12345', 'Николай Сидоров', 'DOCTOR', '+375336454545');
INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('sergeev_p', '12345', 'Петр Сергеев', 'DOCTOR', '+375336545678');
UPDATE `pharmacy`.`users` SET `work_place` = 'ЦРБ г.Молодечно', `specialization` = 'ЛОР' WHERE (`id` = '3');
UPDATE `pharmacy`.`users` SET `work_place` = 'МЦ МедЦентр', `specialization` = 'Хирург' WHERE (`id` = '4');


INSERT INTO pharmacy.disease_groups (code) VALUES ('Antibiotics');
INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('ivan_ivanov123', '12345', 'Иван Иванов', 'CUSTOMER', '+375331234567');
INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('petrova_a', '12345', 'Анна Петрова', 'PHARMACIST', '+375337654321');

INSERT INTO pharmacy.medicines (commercial_name, international_name, dose, medicine_form, invoice_number, serial_number, price,
                    expiration_date, product_arrival, arrival_date, balance, prescription_required, producers_id, disease_groups_code)
                    VALUES ('Амоксициллин', 'Амоксициллин', 1000, 'Таб', '010101', '10012021', 10, '2023-1-10', 30, '2022-1-10', 30, false, (SELECT id FROM producers WHERE factory_name = 'SUN'), (SELECT code FROM disease_groups WHERE code = 'Antibiotics') );