create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, name varchar(40));
CREATE TABLE IF NOT EXISTS ims.items(id int PRIMARY KEY auto_increment, name varchar(40), price DEC(11,2));
CREATE TABLE IF NOT EXISTS ims.orders(id int PRIMARY KEY auto_increment, customer_id_fk int NOT NULL, total_price DEC(11,2), FOREIGN KEY (customer_id_fk) REFERENCES customers(id));
CREATE TABLE IF NOT EXISTS ims.orders_items(order_id_fk int NOT NULL, item_id_fk int NOT NULL, quantity int NOT NULL, FOREIGN KEY (order_id_fk) REFERENCES orders(id), FOREIGN KEY (item_id_fk) REFERENCES items(id));
