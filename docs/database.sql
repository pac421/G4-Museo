CREATE TABLE `WORK`
(
    id            VARCHAR(36)  NOT NULL,
    label         VARCHAR(128) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    period        VARCHAR(128) NOT NULL,
    height        FLOAT        NOT NULL,
    width         FLOAT        NOT NULL,
    depth         FLOAT        NOT NULL,
    weight        FLOAT        NOT NULL,
    category_id   VARCHAR(36)  NOT NULL,
    collection_id VARCHAR(36)  NULL,
    deleted_at    DATETIME     NULL,
    deleted_by_id VARCHAR(36)  NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `PROPERTY`
(
    work_id    VARCHAR(36)  NOT NULL,
    owned_at   DATE         NOT NULL,
    owned_from VARCHAR(128) NOT NULL,
    price      FLOAT        NULL,
    PRIMARY KEY (work_id)
);

CREATE TABLE `LEND`
(
	work_id VARCHAR(36)  NOT NULL,
    start   DATE         NOT NULL,
    end     DATE         NOT NULL,
    lender  VARCHAR(128) NOT NULL,
    PRIMARY KEY (work_id)
);

CREATE TABLE `PICTURE`
(
    id        VARCHAR(36) NOT NULL,
    extension VARCHAR(8)  NOT NULL,
    work_id   VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `COLLECTION`
(
    id     VARCHAR(36)  NOT NULL,
    label  VARCHAR(128) NOT NULL,
    period VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `CATEGORY`
(
    id     VARCHAR(36) NOT NULL,
    label  VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `ARTIST`
(
    id        VARCHAR(36)  NOT NULL,
    firstname VARCHAR(64)  NOT NULL,
    lastname  VARCHAR(64)  NOT NULL,
    period    VARCHAR(128) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `USER`
(
    id        VARCHAR(36)  NOT NULL,
    firstname VARCHAR(64)  NOT NULL,
    lastname  VARCHAR(64)  NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role_id	  VARCHAR(36)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `ROLE`
(
    id    VARCHAR(36)  NOT NULL,
    label VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `STATE`
(
    id    VARCHAR(36)  NOT NULL,
    label VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `PROPERTY_STATE`
(
	id          VARCHAR(36)  NOT NULL,
	start       DATETIME     NOT NULL,
	end         DATETIME     NOT NULL,
	comment     VARCHAR(255) NOT NULL,
	property_id VARCHAR(36)  NOT NULL,
	state_id    VARCHAR(36)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `ARTIST_WORK`
(
	artist_id VARCHAR(36) NOT NULL,
	work_id   VARCHAR(36) NOT NULL,
	PRIMARY KEY (artist_id, work_id)
);


ALTER TABLE `WORK`
ADD FOREIGN KEY (category_id)   REFERENCES `CATEGORY`(id),
ADD FOREIGN KEY (collection_id) REFERENCES `COLLECTION`(id),
ADD FOREIGN KEY (deleted_by_id) REFERENCES `USER`(id);

ALTER TABLE `PROPERTY`
ADD FOREIGN KEY (work_id) REFERENCES `WORK`(id);

ALTER TABLE `LEND`
ADD FOREIGN KEY (work_id) REFERENCES `WORK`(id);

ALTER TABLE `PICTURE`
ADD FOREIGN KEY (work_id) REFERENCES `WORK`(id);

ALTER TABLE `USER`
ADD FOREIGN KEY (role_id) REFERENCES `ROLE`(id);

ALTER TABLE `PROPERTY_STATE`
ADD FOREIGN KEY (property_id) REFERENCES `PROPERTY`(work_id),
ADD FOREIGN KEY (state_id)    REFERENCES `STATE`(id);

ALTER TABLE `ARTIST_WORK`
ADD FOREIGN KEY (artist_id) REFERENCES `ARTIST`(id),
ADD FOREIGN KEY (work_id)   REFERENCES `WORK`(id);

