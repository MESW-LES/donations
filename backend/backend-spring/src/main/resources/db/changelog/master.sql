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