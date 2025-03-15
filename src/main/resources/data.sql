DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS lost_item;
DROP TABLE IF EXISTS person;

CREATE TABLE lost_item
(
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    quantity INTEGER,
    place VARCHAR(255)
);

CREATE TABLE person
(
    id INTEGER PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE activity
(
    id UUID PRIMARY KEY,
    item_id UUID,
    person_id INTEGER,
    quantity INTEGER,
    activity VARCHAR(255),
    performed_on TIMESTAMP
);

INSERT INTO person (id, name) VALUES
(1, 'John doe'),
(2, 'Foo Bar'),
(3, 'Jack the Ripper');
