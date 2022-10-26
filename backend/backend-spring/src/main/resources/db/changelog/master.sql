-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE CATEGORIES(CODE VARCHAR(15) primary key,
                        NAME VARCHAR(100) unique not null,
                        DESCRIPTION VARCHAR(300) not null,
                        ACTIVE BOOLEAN);

-- changeset liquibase:2
CREATE TABLE DONORS(ID INT primary key,
                    FIRST_NAME VARCHAR(50) not null,
                    LAST_NAME VARCHAR(50) not null,
                    NIF VARCHAR(20) unique not null,
                    ADDRESS VARCHAR(150) not null,
                    EMAIL VARCHAR(100) unique not null,
                    PASSWORD VARCHAR(50) not null);