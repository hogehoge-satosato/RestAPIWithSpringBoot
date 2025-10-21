-- init.sql
CREATE TABLE user_info (
  login_id VARCHAR PRIMARY KEY,
  password VARCHAR NOT NULL,
  role VARCHAR NOT NULL
);
INSERT INTO user_info VALUES ('admin', 'admin123', 'ADMIN');
