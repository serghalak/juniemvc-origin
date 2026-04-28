CREATE TABLE customer (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INTEGER,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone_number VARCHAR(255),
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    created_date TIMESTAMP(6),
    update_date TIMESTAMP(6)
);

ALTER TABLE beer_order
ADD COLUMN customer_id INTEGER;

ALTER TABLE beer_order
ADD CONSTRAINT fk_beer_order_customer FOREIGN KEY (customer_id) REFERENCES customer(id);
