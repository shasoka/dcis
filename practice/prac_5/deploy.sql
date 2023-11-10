CREATE DATABASE fridgedb;

\c fridgedb;

CREATE TABLE IF NOT EXISTS fridges (
    id serial PRIMARY KEY,
    model character varying(255),
    prod character varying(255),
    country_prod character varying(255),
    cost numeric(10, 2),
    volume integer
);
