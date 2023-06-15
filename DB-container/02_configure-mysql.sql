CREATE USER 'bo_dev'@'localhost' IDENTIFIED BY 'pass12345';
CREATE USER 'bo_dev'@'%' IDENTIFIED BY 'pass12345';
GRANT ALL PRIVILEGES ON songs.* TO 'bo_dev'@'localhost';
GRANT ALL PRIVILEGES ON songs.* TO 'bo_dev'@'%';
FLUSH PRIVILEGES;