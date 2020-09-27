DROP TABLE IF EXISTS bloggerek;
CREATE TABLE bloggerek(
id bigint NOT NULL,
name varchar(100),
age int,
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tortenetek;
CREATE TABLE tortenetek(
id bigint NOT NULL,
title varchar(100),
content varchar(400),
posted date,
blogger_id int,
PRIMARY KEY (id)
);