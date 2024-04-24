CREATE TABLE survey_db.login (
                                 id int NOT NULL AUTO_INCREMENT,
                                 nombre varchar(60) DEFAULT NULL,
                                 codigo varchar(20) DEFAULT NULL,
                                 PRIMARY KEY (id)
);

CREATE TABLE survey_db.votos (
                                  id int NOT NULL AUTO_INCREMENT,
                                  nombre varchar(60) DEFAULT NULL,
                                  voto varchar(20) DEFAULT NULL,
                                  cuando timestamp DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (id)
);

CREATE TABLE survey_db.opiniones (
                                  id int NOT NULL AUTO_INCREMENT,
                                  nombre varchar(45) DEFAULT '',
                                  opinion varchar(45) DEFAULT 'otros',
                                  respuesta int DEFAULT 0,
                                  PRIMARY KEY (id)
);

CREATE TABLE survey_db.opciones (
                                    id int NOT NULL AUTO_INCREMENT,
                                    nombre varchar(45) DEFAULT '',
                                    valor varchar(45) DEFAULT '',
                                    PRIMARY KEY (id)
);