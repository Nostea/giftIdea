CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    avatar VARCHAR(255),
    user_role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO users (id, username, password, email, avatar, user_role, created_at, updated_at)VALUES
(1, 'Anna', 'password123', 'john.doe@example.com', 'https://picsum.photos/300/300', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Steve', 'password456', 'jane.smith@example.com', 'https://picsum.photos/300/300', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);