CREATE DATABASE icm CHARACTER SET utf8 COLLATE utf8_general_ci;

USE icm;
CREATE USER 'icm'@'localhost' IDENTIFIED BY 'icm';
GRANT ALL PRIVILEGES ON icm.* TO 'icm'@'localhost';
