CREATE TABLE USERS (
    ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    FIRSTNAME VARCHAR(20),
    LASTNAME VARCHAR(20),
    AGE INTEGER,
    SEX CHAR,
    CHECK (SEX IN ('f', 'm')),
    PHONE VARCHAR(16)
);