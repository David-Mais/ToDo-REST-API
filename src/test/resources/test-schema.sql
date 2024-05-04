CREATE TABLE IF NOT EXISTS users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);
ALTER TABLE users ALTER COLUMN id RESTART WITH 1;

CREATE TABLE IF NOT EXISTS todos
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(1000),
    user_id BIGINT,
    CONSTRAINT fk_uid FOREIGN KEY (user_id) REFERENCES users (id)
);
ALTER TABLE todos ALTER COLUMN id RESTART WITH 1;

DELETE FROM todos WHERE TRUE;
DELETE FROM users WHERE TRUE;

INSERT INTO users (username, first_name, last_name) VALUES
('jdoe', 'John', 'Doe'),
('asmith', 'Alice', 'Smith'),
('bwayne', 'Bruce', 'Wayne'),
('ckent', 'Clark', 'Kent'),
('pparker', 'Peter', 'Parker');

INSERT INTO todos (title, description, user_id) VALUES
('Task 1', 'Description for Task 1', 1),
('Task 2', 'Description for Task 2', 1),
('Task 3', 'Description for Task 3', 1),
('Task 4', 'Description for Task 4', 2),
('Task 5', 'Description for Task 5', 2),
('Task 6', 'Description for Task 6', 2),
('Task 7', 'Description for Task 7', 3),
('Task 8', 'Description for Task 8', 3),
('Task 9', 'Description for Task 9', 3),
('Task 10', 'Description for Task 10', 4),
('Task 11', 'Description for Task 11', 4),
('Task 12', 'Description for Task 12', 4),
('Task 13', 'Description for Task 13', 5),
('Task 14', 'Description for Task 14', 5),
('Task 15', 'Description for Task 15', 5);
