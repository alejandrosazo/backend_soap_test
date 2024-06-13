CREATE DATABASE soap;

select * from soap.tasa_cambio;
CREATE TABLE soap.tasa_cambio (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    tasa_venta DECIMAL(10, 5) NOT NULL,
    tasa_compra DECIMAL(10, 5) NOT NULL,
    num_peticion INTEGER
);

CREATE SEQUENCE soap.num_peticion_seq;

ALTER TABLE soap.tasa_cambio
ALTER COLUMN num_peticion
SET DEFAULT nextval('soap.num_peticion_seq');
