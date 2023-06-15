CREATE DATABASE songs;
USE songs;
-- Create the Producer table
CREATE TABLE producer (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  address VARCHAR(255),
  city VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  state VARCHAR(255)
);

-- Create the Singer table
CREATE TABLE singer (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  address VARCHAR(255),
  city VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  state VARCHAR(255)
);
-- Create the Song table
CREATE TABLE song (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  length FLOAT(53),
  producer_id BIGINT,
  name VARCHAR(255),
  FOREIGN KEY (producer_id) REFERENCES producer (id)
);

-- Create the Singer_Song table
CREATE TABLE singer_song (
  singer_id BIGINT NOT NULL,
  song_id BIGINT NOT NULL,
  PRIMARY KEY (singer_id, song_id),
  FOREIGN KEY (singer_id) REFERENCES singer (id),
  FOREIGN KEY (song_id) REFERENCES song (id)
);


