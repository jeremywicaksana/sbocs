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

INSERT INTO lost_item (id, name, quantity, place) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'Laptop', 1, 'Office'),
('123e4567-e89b-12d3-a456-426614174001', 'Phone', 2, 'Home'),
('123e4567-e89b-12d3-a456-426614174002', 'Wallet', 1, 'Car');

INSERT INTO person (id, name) VALUES
(1, 'John Doe'),
(2, 'Jane Smith'),
(3, 'Alice Johnson');

INSERT INTO activity (id, item_id, person_id, quantity, activity, performed_on) VALUES
('123e4567-e89b-12d3-a456-426614174003', '123e4567-e89b-12d3-a456-426614174000', 1, 1, 'CLAIMED', '2025-03-14 12:00:00'),
('123e4567-e89b-12d3-a456-426614174004', '123e4567-e89b-12d3-a456-426614174001', 2, 1, 'ADDED', '2025-03-14 13:00:00'),
('123e4567-e89b-12d3-a456-426614174005', '123e4567-e89b-12d3-a456-426614174002', 3, 1, 'CLAIMED', '2025-03-14 14:00:00');