-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE CATEGORIES(CODE VARCHAR(15) primary key,
                        NAME VARCHAR(100) unique not null,
                        DESCRIPTION VARCHAR(300) not null,
                        ACTIVE BOOLEAN);

-- changeset liquibase:2
CREATE TABLE COMPANIES(ID BIGINT GENERATED BY DEFAULT AS IDENTITY primary key,
                       NAME VARCHAR(50) NOT NULL,
                       DESCRIPTION VARCHAR(200) NOT NULL,
                       TAX_NUMBER VARCHAR(20) NOT NULL UNIQUE,
                       PHONE BIGINT NOT NULL,
                       EMAIL VARCHAR(100) NOT NULL UNIQUE,
                       ACTIVE BOOLEAN,
                       REGISTER_DATE DATE,
                       REMOVE_DATE DATE);

CREATE TABLE DONEES(ID BIGINT GENERATED BY DEFAULT AS IDENTITY primary key,
                    COMPANY_ID BIGINT NOT NULL,
                    FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES(ID));

-- changeset liquibase:3
CREATE TABLE PERSONS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY primary key,
                    FIRST_NAME VARCHAR(50) not null,
                    LAST_NAME VARCHAR(50) not null,
                    NIF VARCHAR(20) unique not null,
                    ADDRESS VARCHAR(150) not null,
                    EMAIL VARCHAR(100) unique not null,
                    ACTIVE BOOLEAN);

CREATE TABLE DONORS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY primary key,
                    PERSON_ID BIGINT NOT NULL,
                    FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID));

-- changeset liquibase:4

ALTER TABLE DONEES ADD COLUMN PASSWORD VARCHAR(20) NOT NULL;
ALTER TABLE DONEES ADD COLUMN ACTIVE BOOLEAN NOT NULL;

-- changeset liquibase:5
CREATE TABLE DONEES_CATEGORIES(DONEE_ID BIGINT NOT NULL,
                               CATEGORY_CODE VARCHAR(15) NOT NULL,
                               FOREIGN KEY (DONEE_ID) REFERENCES DONEES(ID),
                               FOREIGN KEY (CATEGORY_CODE) REFERENCES CATEGORIES(CODE),
                               PRIMARY KEY (DONEE_ID, CATEGORY_CODE));

-- changeset liquibase:6
CREATE TABLE DONATIONS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       TITLE VARCHAR(50) NOT NULL,
                       DESCRIPTION VARCHAR(500) NOT NULL,
                       CREATED_DATE TIMESTAMP NOT NULL,
                       ACTIVE BOOLEAN NOT NULL);

CREATE TABLE DONATIONS_CATEGORIES(DONATION_ID BIGINT NOT NULL,
                                  CATEGORY_CODE VARCHAR(15) NOT NULL,
                                  FOREIGN KEY (DONATION_ID) REFERENCES DONATIONS(ID),
                                  FOREIGN KEY (CATEGORY_CODE) REFERENCES CATEGORIES(CODE),
                                  PRIMARY KEY(DONATION_ID, CATEGORY_CODE));

-- changeset liquibase:7
CREATE TABLE DONATION_IMAGES(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             ORIGINAL_NAME VARCHAR(100) NOT NULL,
                             FILE_NAME VARCHAR(100) NOT NULL,
                             DIRECTORY_PATH VARCHAR(300) NOT NULL,
                             FILE_EXTENSION VARCHAR(100) NOT NULL,
                              FILE_SIZE NUMERIC NOT NULL,
                             DONATION_ID BIGINT NOT NULL,
                             ACTIVE BOOLEAN NOT NULL,
                             FOREIGN KEY (DONATION_ID) REFERENCES DONATIONS(ID));

CREATE TABLE DONATIONS_PROCESS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                              DONATION_ID BIGINT NOT NULL,
                              STATUS VARCHAR(10) NOT NULL,
                              ONGOING_DATE TIMESTAMP,
                              DONEE_ID BIGINT,
                              FOREIGN KEY (DONATION_ID) REFERENCES DONATIONS(ID),
                              FOREIGN KEY (DONEE_ID) REFERENCES DONEES(ID))

-- changeset liquibase:8

CREATE TABLE GEOGRAPHIC_AREAS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                             CITY VARCHAR(100) NOT NULL,
                             PARISH VARCHAR(100) NOT NULL,
                             MUNICIPALITY VARCHAR(100) NOT NULL);

-- changeset liquibase:9
CREATE TABLE GEOGRAPHIC_AREAS_DONEES(GEOGRAPHIC_AREA_ID BIGINT NOT NULL,
                                     DONEE_ID BIGINT NOT NULL,
                                     FOREIGN KEY (GEOGRAPHIC_AREA_ID) REFERENCES GEOGRAPHIC_AREAS(ID),
                                     FOREIGN KEY (DONEE_ID) REFERENCES DONEES(ID),
                                     PRIMARY KEY(GEOGRAPHIC_AREA_ID, DONEE_ID));

-- changeset liquibase:10
CREATE TABLE ADDRESSES(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       STREET VARCHAR(100) NOT NULL,
                       HOUSE_NUMBER BIGINT NOT NULL,
                       POSTAL_CODE VARCHAR(100) NOT NULL,
                       GEOGRAPHIC_AREA_ID BIGINT NOT NULL,
                       FOREIGN KEY (GEOGRAPHIC_AREA_ID) REFERENCES GEOGRAPHIC_AREAS(ID));

ALTER TABLE DONORS ADD COLUMN ADDRESS_ID BIGINT NOT NULL;
ALTER TABLE DONORS ADD FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESSES(ID);


ALTER TABLE DONEES DROP COLUMN PASSWORD;
ALTER TABLE DONEES DROP COLUMN ACTIVE;

-- changeset liquibase:11
CREATE TABLE ADMINISTRATORS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                            FIRST_NAME VARCHAR(50) not null,
                            LAST_NAME VARCHAR(50) not null,
                            EMAIL VARCHAR(100) unique not null,
                            ACTIVE BOOLEAN);
INSERT INTO ADMINISTRATORS(ID, FIRST_NAME, LAST_NAME, EMAIL, ACTIVE) VALUES(1, 'Luis', 'Ferreira', 'lukyferr@gmail.com', true);