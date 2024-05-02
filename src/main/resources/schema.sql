CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);

CREATE TABLE todos
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description VARCHAR(1000),
    user_id BIGINT,
    CONSTRAINT fk_uid FOREIGN KEY (user_id) REFERENCES users (id)
);