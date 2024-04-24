SELECT * FROM login WHERE nombre = ? AND codigo = ? LIMIT 1;

SELECT nombre, voto, cuando FROM votos WHERE nombre = ? LIMIT 1;

SELECT nombre, voto, cuando FROM votos ORDER BY id ASC;

UPDATE votos SET voto = ?, cuando = current_timestamp WHERE nombre = ?;

INSERT INTO votos (nombre, voto, cuando) VALUES (?, ?, current_timestamp);

SELECT id, opinion, nombre FROM opiniones ORDER BY id ASC

SELECT id, opinion, nombre FROM opiniones WHERE respuesta = 0 ORDER BY id ASC

SELECT id, opinion, nombre FROM opiniones WHERE respuesta = ? ORDER BY id ASC

INSERT INTO opiniones (nombre, respuesta, opinion) VALUES (?, ?, ?);

SELECT nombre, valor FROM opciones ORDER BY id ASC;

SELECT o.id, o.nombre, o.valor, count(v.cuando) AS cuantos
FROM opciones o LEFT JOIN votos v ON o.valor = v.voto
GROUP BY o.id, o.nombre, o.valor ORDER BY o.id ASC;

