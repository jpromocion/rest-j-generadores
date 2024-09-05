/*
Script de creacion de las tablas en memoria para H2.
Spring boot ejecutara a la entrada automaticamente este schema.sql
*/

CREATE TABLE nombres(
  id INTEGER NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  genero VARCHAR(1),
  PRIMARY KEY (id)
);


CREATE TABLE apellidos(
  id INTEGER NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);