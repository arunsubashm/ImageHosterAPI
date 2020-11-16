DROP TABLE IF EXISTS imagehoster.IMAGES CASCADE;

CREATE TABLE IF NOT EXISTS imagehoster.IMAGES(
	ID SERIAL PRIMARY KEY,
    UUID VARCHAR(36) NOT NULL,
    IMAGE VARCHAR(10000),
    NAME VARCHAR(200),
    DESCRIPTION VARCHAR (200),
    NO_OF_LIKES INTEGER ,
    USER_ID INTEGER,
    CREATED_AT TIMESTAMP,
    STATUS VARCHAR(26)
);

ALTER TABLE imagehoster.IMAGES ADD CONSTRAINT FK_IMAGES FOREIGN KEY(USER_ID) REFERENCES imagehoster.USERS(ID);
