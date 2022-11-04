-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE CATEGORIES(CODE VARCHAR(15) primary key,
                        NAME VARCHAR(100) unique not null,
                        DESCRIPTION VARCHAR(300) not null,
                        ACTIVE BOOLEAN);

CREATE TABLE COMPANIES(ID BIGINT primary key,
                       NAME VARCHAR(50) NOT NULL,
                       DESCRIPTION VARCHAR(200) NOT NULL,
                       TAX_NUMBER VARCHAR(20) NOT NULL UNIQUE,
                       PHONE BIGINT NOT NULL,
                       ACTIVE BOOLEAN,
                       REGISTER_DATE DATE,
                       REMOVE_DATE DATE);

CREATE TABLE DONEES(ID BIGINT primary key,
                    COMPANY_ID BIGINT NOT NULL,
                    FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES(ID));

-- changeset liquibase:2
CREATE TABLE DONORS(ID INT primary key,
                    FIRST_NAME VARCHAR(50) not null,
                    LAST_NAME VARCHAR(50) not null,
                    NIF VARCHAR(20) unique not null,
                    ADDRESS VARCHAR(150) not null,
                    EMAIL VARCHAR(100) unique not null,
                    PASSWORD VARCHAR(50) not null);