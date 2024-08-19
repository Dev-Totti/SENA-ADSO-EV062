CREATE TABLE products
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price    DECIMAL(10, 2) NOT NULL,
    active   BOOLEAN DEFAULT TRUE
);