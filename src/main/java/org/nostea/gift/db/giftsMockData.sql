CREATE TABLE gifts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    giftName VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255),
    price DECIMAL(8, 2) NOT NULL,
    externalLink VARCHAR(1000),
    createdBy BIGINT NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP

);

INSERT INTO gifts (id, giftName, thumbnail, price, externalLink, createdBy, createdAt, updatedAt) VALUES
(1, 'Raspberry Pi', 'https://picsum.photos/500/300', 130.99, 'https://example.com/gift1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Teddy', 'https://picsum.photos/500/300', 29.99, 'https://example.com/gift2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Orchidee', 'https://picsum.photos/500/300', 39.99, 'https://example.com/gift3', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);