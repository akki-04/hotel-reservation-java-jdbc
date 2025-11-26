CREATE DATABASE IF NOT EXISTS hotel_db;
USE hotel_db;

CREATE TABLE reservation (
    res_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    room_no INT,
    contact_no VARCHAR(20),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
