INSERT INTO survey_db.login (nombre, codigo) VALUES ('Aitor', 'KEY123');
INSERT INTO survey_db.login (nombre, codigo) VALUES ('Alba', 'KEY222');
INSERT INTO survey_db.login (nombre, codigo) VALUES ('Mikel', 'KEY333');
INSERT INTO survey_db.login (nombre, codigo) VALUES ('Javi', 'KEY444');
INSERT INTO survey_db.login (nombre, codigo) VALUES ('Edu', 'KEY555');
INSERT INTO survey_db.login (nombre, codigo) VALUES ('Spyros', 'KEY666');

INSERT INTO survey_db.votos (nombre, voto) VALUES ('Aitor', 'txantrea');
INSERT INTO survey_db.votos (nombre, voto) VALUES ('Alba', 'sanjuan');
INSERT INTO survey_db.votos (nombre, voto) VALUES ('Mikel', 'txantrea');
INSERT INTO survey_db.votos (nombre, voto) VALUES ('Javi', 'txantrea');
INSERT INTO survey_db.votos (nombre, voto) VALUES ('Edu', 'hiruherri');
INSERT INTO survey_db.votos (nombre, voto) VALUES ('Spyros', 'hiruherri');

INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Aitor', 'Aupa Txantrea, la mejor!', 0);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Alba', 'No tienes ni idea', 1);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Aitor', 'Ya lo veremos', 1);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Mikel', 'Falta Beste Iruña', 0);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Spyros', 'Ardoi es mejor', 0);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Edu', 'Ardoi txapeldun!', 0);
INSERT INTO survey_db.opiniones (nombre, opinion, respuesta)
VALUES ('Javi', 'Estoy de acuerdo', 4);

INSERT INTO survey_db.opciones (nombre, valor) VALUES ('Txantrea', 'txantrea');
INSERT INTO survey_db.opciones (nombre, valor) VALUES ('San Juan', 'sanjuan');
INSERT INTO survey_db.opciones (nombre, valor) VALUES ('Hiru Herri', 'hiruherri');
