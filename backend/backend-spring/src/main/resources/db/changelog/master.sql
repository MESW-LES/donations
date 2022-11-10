-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE CATEGORIES(CODE VARCHAR(15) primary key,
                        NAME VARCHAR(100) unique not null,
                        DESCRIPTION VARCHAR(300) not null,
                        ACTIVE BOOLEAN);

-- changeset liquibase:3
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

-- changeset liquibase:4
CREATE TABLE PERSONS(ID INT primary key,
                    FIRST_NAME VARCHAR(50) not null,
                    LAST_NAME VARCHAR(50) not null,
                    NIF VARCHAR(20) unique not null,
                    ADDRESS VARCHAR(150) not null,
                    EMAIL VARCHAR(100) unique not null,
                    PASSWORD VARCHAR(50) not null);

CREATE TABLE DONORS(ID BIGINT primary key,
                    PERSON_ID BIGINT NOT NULL,
                    FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID));

-- changeset liquibase:5
CREATE TABLE DONATIONS(ID INT primary key,
                    PRODUCT_NAME VARCHAR(100) not null,
                    PRODUCT_DESCRIPTION VARCHAR(300) not null,
                    CATEGORY_CODE VARCHAR(15) not null,
                    );

CREATE TABLE PHOTO(ID BIGINT primary key,
                    DONATION_ID BIGINT NOT NULL,
                    PHOTO_NAME VARCHAR(100) not null,
                    PHOTO_URL VARCHAR(400) not null,
                    IS_DELETED BOOLEAN,
                    FOREIGN KEY (DONATION_ID) REFERENCES DONATIONS(ID));