CREATE TABLE gifts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gift_name VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255),
    price DECIMAL(8, 2) NOT NULL,
    external_link VARCHAR(1000),
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

INSERT INTO gifts (id, gift_name, thumbnail, price, external_link, created_by, created_at, updated_at)VALUES
(1, 'Raspberry Pi', 'https://picsum.photos/500/300', 130.99, 'https://example.com/gift1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Teddy', 'https://picsum.photos/500/300', 29.99, 'https://example.com/gift2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Orchidee', 'https://picsum.photos/500/300', 39.99, 'https://example.com/gift3', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
