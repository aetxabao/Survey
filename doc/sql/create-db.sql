

CREATE USER 'proy'@'localhost' IDENTIFIED BY 'password';

CREATE DATABASE survey_db;

GRANT ALL PRIVILEGES ON survey_db.* TO 'proy'@'localhost';

USE survey_db;


