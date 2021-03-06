CREATE TABLE IF NOT EXISTS `rentsystemclass`.`cars` (
  `id` VARCHAR(50) NOT NULL,
  `year` INT(4) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  `plate` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `plate_UNIQUE` (`plate` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1