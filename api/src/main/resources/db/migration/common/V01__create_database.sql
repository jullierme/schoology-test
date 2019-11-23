CREATE TABLE country
(
    id           int(11)      NOT NULL auto_increment,
    name         varchar(100) NOT NULL,
    abbreviation varchar(2)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;
