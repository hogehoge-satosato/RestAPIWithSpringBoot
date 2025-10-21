CREATE ROLE root WITH LOGIN PASSWORD 'postgres';
CREATE DATABASE root OWNER root;

CREATE TABLE user_info (
  login_id VARCHAR PRIMARY KEY,
  password VARCHAR NOT NULL,
  role VARCHAR NOT NULL
);
INSERT INTO user_info VALUES ('admin', 'admin123', 'ADMIN');

CREATE TABLE product (
  id INTEGER PRIMARY KEY,
  name VARCHAR NOT NULL,
  price INTEGER NOT NULL,
  stock INTEGER NOT NULL,
  description VARCHAR
);

