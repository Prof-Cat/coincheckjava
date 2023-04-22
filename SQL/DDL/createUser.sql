CREATE USER 'mtdbuser'@'localhost' IDENTIFIED BY 'trades23';
GRANT ALL PRIVILEGES ON mtcrypto.* TO 'mtdbuser'@'localhost';
FLUSH PRIVILEGES;

