-- liquibase formatted sql

-- changeset liquibase:1
create table requests (id serial PRIMARY KEY,
email VARCHAR(255) NOT NULL,
NAME VARCHAR(255) NOT NULL);