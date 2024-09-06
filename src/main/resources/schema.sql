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



CREATE TABLE ccaa(
  id VARCHAR(2) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE provincias(
  id VARCHAR(2) NOT NULL,
  idccaa VARCHAR(2) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE municipios(
  id INTEGER NOT NULL,
  codigoine VARCHAR(3) NOT NULL,
  idprovincias VARCHAR(2) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE tipovias(
  id INTEGER NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE codpostales(
  id INTEGER NOT NULL,
  codpostal VARCHAR(5) NOT NULL,
  idprovincias VARCHAR(2) NOT NULL,
  inemunicipios VARCHAR(3) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE bancos(
  id INTEGER NOT NULL,
  codigo VARCHAR(4) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  bic VARCHAR(11) NOT NULL,
  PRIMARY KEY (id)
);

