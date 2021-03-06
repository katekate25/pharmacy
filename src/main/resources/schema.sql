-- MySQL Script generated by MySQL Workbench
-- Thu Feb 10 14:55:47 2022
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
  `password` VARCHAR(65) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NULL,
  `roles_code` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `telephone_number` VARCHAR(45) NOT NULL,
  `work_place` VARCHAR(100) NULL,
  `specialization` VARCHAR(100) NULL,
  `salt` VARCHAR(45) NULL,
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
  `doctor_id` INT NOT NULL,
  `status` VARCHAR(20) NULL,
  PRIMARY KEY (`prescription_number`),
  INDEX `fk_prescription_users2_idx` (`client_id` ASC) VISIBLE,
  UNIQUE INDEX `prescription_number_UNIQUE` (`prescription_number` ASC) VISIBLE,
  INDEX `fk_prescriptions_medicines1_idx` (`medicines_id` ASC) VISIBLE,
  INDEX `fk_prescriptions_users1_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_prescription_users2`
    FOREIGN KEY (`client_id`)
    REFERENCES `pharmacy`.`users` (`id`)
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
-- Table `pharmacy`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`orders` (
  `number` INT NOT NULL AUTO_INCREMENT,
  `clients_id` INT NULL,
  `pharmacists_id` INT NULL,
  `delivery_time` DATETIME(6) NULL,
  `order_status` VARCHAR(45) NULL,
  `payment_status` VARCHAR(45) NULL,
  `total_price` DOUBLE NULL,
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
-- Table `pharmacy`.`order_entry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pharmacy`.`order_entry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `package_amount` DOUBLE NOT NULL,
  `order_number` INT NULL,
  `medicines_id` INT NOT NULL,
  `total_price` DOUBLE NULL,
  `prescriptions_prescription_number` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_entry_order1_idx` (`order_number` ASC) VISIBLE,
  INDEX `fk_order_entry_medicines1_idx` (`medicines_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_entry_prescriptions1_idx` (`prescriptions_prescription_number` ASC) VISIBLE,
  CONSTRAINT `fk_order_entry_order1`
    FOREIGN KEY (`order_number`)
    REFERENCES `pharmacy`.`orders` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_entry_medicines1`
    FOREIGN KEY (`medicines_id`)
    REFERENCES `pharmacy`.`medicines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_entry_prescriptions1`
    FOREIGN KEY (`prescriptions_prescription_number`)
    REFERENCES `pharmacy`.`prescriptions` (`prescription_number`)
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
  `approved` TINYINT NULL,
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


INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('sidorov_n', '12345', '?????????????? ??????????????', 'DOCTOR', '+375336454545');
INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('sergeev_p', '12345', '???????? ??????????????', 'DOCTOR', '+375336545678');
UPDATE `pharmacy`.`users` SET `work_place` = '?????? ??.??????????????????', `specialization` = '??????' WHERE (`id` = '3');
UPDATE `pharmacy`.`users` SET `work_place` = '???? ????????????????', `specialization` = '????????????' WHERE (`id` = '4');


INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIBIOTICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('VITAMINS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HYPNOTICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('SEDATIVE');
INSERT INTO pharmacy.disease_groups (code) VALUES ('NONSTEROIDAL_ANTIINFLAMMATORY');
INSERT INTO pharmacy.disease_groups (code) VALUES ('DIURETIC');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HORMONES');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANALGESICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HYPERTENSIVE');
INSERT INTO pharmacy.disease_groups (code) VALUES ('NEUROLEPTICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIHYPERGLYCEMIC');
INSERT INTO pharmacy.disease_groups (code) VALUES ('DIETARY_SUPPLIMENT');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIBACTERIAL');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ADAPTOGENS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('IMMUNOMODULATORS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTACIDS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTISECRETORY');
INSERT INTO pharmacy.disease_groups (code) VALUES ('SPASMOLITICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('DIGESTIVE_ENZYMES');
INSERT INTO pharmacy.disease_groups (code) VALUES ('LAXATIVES');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ENTEROSORBENTS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTITUSSIVE');

INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIFUNGAL');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HOMEOPATHIC');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HEPATOPROTECTORS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('NITRATE');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIALLERGIC');
INSERT INTO pharmacy.disease_groups (code) VALUES ('MUCOLYTICS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTIDEPRESSANTS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ANTICONVULSANT');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ADRENERGIC');
INSERT INTO pharmacy.disease_groups (code) VALUES ('CALCIUM_CHANNELS_BLOCKERS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('ACE_INHIBITORS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('HERBAL_MEDICINES');
INSERT INTO pharmacy.disease_groups (code) VALUES ('PHARMACEUTICAL_PRODUCTS');
INSERT INTO pharmacy.disease_groups (code) VALUES ('COSMETICS');



INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('ivan_ivanov123', '12345', '???????? ????????????', 'CUSTOMER', '+375331234567');
INSERT INTO `pharmacy`.`users` (`login`, `password`, `name`, `roles_code`, `telephone_number`) VALUES ('petrova_a', '12345', '???????? ??????????????', 'PHARMACIST', '+375337654321');




INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('Sandoz', '????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('??????????????????????????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('??????????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????????????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('Bayer', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('KRKA', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????????????-????????', '????????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????. ??????????`??', '??????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('??????????????????', '????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('????????????????????????', '????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('EGIS Pharmaceuticals', '??????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('OPELLA HEALTHCARE', '??????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('???????????? ????????????', '??????????????');
INSERT INTO `pharmacy`.`producers` (`factory_name`, `producer_country`) VALUES ('??????????', '????????????????');