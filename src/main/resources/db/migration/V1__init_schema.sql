CREATE TABLE beer (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INTEGER,
    beer_name VARCHAR(255),
    beer_style VARCHAR(255),
    upc VARCHAR(255),
    quantity_on_hand INTEGER,
    price DECIMAL(19, 2),
    created_date TIMESTAMP(6),
    update_date TIMESTAMP(6)
);

CREATE TABLE beer_order (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INTEGER,
    customer_ref VARCHAR(255),
    payment_amount DECIMAL(19, 2),
    status VARCHAR(255),
    created_date TIMESTAMP(6),
    update_date TIMESTAMP(6)
);

CREATE TABLE beer_order_line (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INTEGER,
    beer_order_id INTEGER,
    beer_id INTEGER,
    order_quantity INTEGER,
    quantity_allocated INTEGER,
    status VARCHAR(255),
    created_date TIMESTAMP(6),
    update_date TIMESTAMP(6),
    CONSTRAINT fk_beer_order FOREIGN KEY (beer_order_id) REFERENCES beer_order(id),
    CONSTRAINT fk_beer_orderLine_beer FOREIGN KEY (beer_id) REFERENCES beer(id)
);
