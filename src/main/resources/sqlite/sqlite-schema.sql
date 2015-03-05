DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS incident;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS audit;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE account
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  phone TEXT,
  password TEXT NOT NULL,
  role TEXT NOT NULL
);

CREATE TABLE address
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  address_line TEXT,
  city_line TEXT
);

CREATE TABLE incident
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  incident_type TEXT NOT NULL,
  address_id INTEGER NOT NULL,
  creator_id INTEGER NOT NULL,
  assignee_id INTEGER,
  description TEXT NOT NULL,
  created TEXT NOT NULL,
  status INTEGER NOT NULL
);

CREATE TABLE audit
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  incident_id INTEGER NOT NULL,
  creator_id INTEGER NOT NULL,
  description TEXT,
  created TEXT NOT NULL,
  status INTEGER NOT NULL,
  previous_status INTEGER NOT NULL
);

CREATE TABLE files
(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  size INTEGER NOT NULL,
  content_type VARCHAR NOT NULL,
  object_id INTEGER NOT NULL,
  object_type VARCHAR NOT NULL,
  creator_id INTEGER NOT NULL,
  created TEXT NOT NULL,
  file BLOB
);

-- Dictionaries

CREATE TABLE status
(
  ordinal INTEGER PRIMARY KEY,
  value TEXT NOT NULL
);

insert into status (ordinal, value) values (0, 'NEW');
insert into status (ordinal, value) values (1, 'CONFIRMED');
insert into status (ordinal, value) values (2, 'NOT_CONFIRMED');
insert into status (ordinal, value) values (3, 'SOLVED');
insert into status (ordinal, value) values (4, 'CLOSED');

CREATE TABLE user_roles
(
  value TEXT PRIMARY KEY
);

insert into user_roles (value) values ("ROLE_ADMIN");
insert into user_roles (value) values ("ROLE_USER");
insert into user_roles (value) values ("ROLE_EMPLOYEE");