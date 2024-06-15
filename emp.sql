
CREATE TABLE `myemployee`.`address` (
  `aid` INT NOT NULL AUTO_INCREMENT,
  `HOUSE_NUMBER` INT,
  `STREET` VARCHAR(255),
  `CITY` VARCHAR(255),
  `DISTRICT` VARCHAR(255),
  `ST` VARCHAR(255),
  `CN` VARCHAR(255),
  `PIN` INT,
  PRIMARY KEY (`aid`)
);

CREATE TABLE `myemployee`.`employee` (
  `eid` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255),
  `EMAIL` VARCHAR(255),
  `DATE_OF_BIRTH` DATE,
  `PHONE` BIGINT,
  `address_id` INT,
  PRIMARY KEY (`eid`),
  CONSTRAINT `fk_address_employee`
    FOREIGN KEY (`address_id`)
    REFERENCES `myemployee`.`address` (`aid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
