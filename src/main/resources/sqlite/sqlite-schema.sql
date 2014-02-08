DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS incident;
DROP TABLE IF EXISTS audit;

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
  description TEXT NOT NULL,
  created TEXT NOT NULL,
  status INTEGER NOT NULL,
  previous_status INTEGER NOT NULL
);