-- ------------------------------------------------------------------------------
-- - Gestion des droits d'accès                                     ---
-- ------------------------------------------------------------------------------
USE web;

-- -----------------------------------------------------------------------------
-- - Construction de la table des Users                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	username			varchar(25)		PRIMARY KEY,
	password			varchar(250),
	active				boolean
) ENGINE = InnoDB;

INSERT INTO T_Users (username, password, active) VALUES ( 'arthur',  '$2a$12$W50FchoIhDeFZHeWz7PtsOdhUIORlcZMPqBzFNO3hDubBceYkCNNu' , '1' );	-- pwd 123
INSERT INTO T_Users (username, password, active) VALUES ( 'ambre',  '$2a$12$W50FchoIhDeFZHeWz7PtsOdhUIORlcZMPqBzFNO3hDubBceYkCNNu' , '1' );

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table avec 2 Roles principaux                        ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Roles (
	role 		varchar(25)		PRIMARY KEY
) ENGINE = InnoDB;

INSERT INTO T_Roles (role) VALUES ('ADMIN');
INSERT INTO T_Roles (role) VALUES ('USER');

-- -----------------------------------------------------------------------------
-- - Construction de la table des rôles par utilisateur	                     ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users_Roles (
	username 		varchar(25),
	role 			varchar(25),
	PRIMARY KEY(username,role)
) ENGINE = InnoDB;

INSERT INTO T_Users_Roles (username,role) VALUES ('arthur','ADMIN');
INSERT INTO T_Users_Roles (username,role) VALUES ('arthur','USER');
INSERT INTO T_Users_Roles (username,role) VALUES ('ambre','USER');

