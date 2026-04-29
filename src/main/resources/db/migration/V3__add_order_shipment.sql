CREATE TABLE beer_order_shipment (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    version INTEGER,
    beer_order_id INTEGER NOT NULL,
    shipment_date TIMESTAMP(6) NOT NULL,
    carrier VARCHAR(255),
    tracking_number VARCHAR(255),
    created_date TIMESTAMP(6),
    update_date TIMESTAMP(6),
    CONSTRAINT fk_beer_order_shipment FOREIGN KEY (beer_order_id) REFERENCES beer_order(id)
);
