CREATE ROLE root WITH LOGIN PASSWORD 'postgres';
CREATE DATABASE root OWNER root;

CREATE TABLE user_info (
  login_id VARCHAR PRIMARY KEY,
  password VARCHAR NOT NULL,
  role VARCHAR NOT NULL
);
INSERT INTO user_info VALUES ('admin', 'admin123', 'ADMIN');
INSERT INTO user_info (login_id, password, role) VALUES ('sato', 'password123', 'ADMIN');

CREATE TABLE product (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  price NUMERIC(10, 2) NOT NULL,
  stock INTEGER DEFAULT 0,
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


