CREATE TABLE hospitalcentral
(
  nrohospital integer NOT NULL,
  nombre character varying(1000),
  CONSTRAINT pk_nrohospital PRIMARY KEY (nrohospital)
);

CREATE TABLE hospital
(
  nrocama integer NOT NULL,
  estado integer,
  nrohospital integer NOT NULL,
  CONSTRAINT pk_nrocama PRIMARY KEY (nrocama),
  CONSTRAINT fk_nrohospital FOREIGN KEY (nrohospital) REFERENCES hospitalcentral(nrohospital)
);