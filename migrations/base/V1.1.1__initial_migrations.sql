CREATE TABLE users (
	id serial PRIMARY KEY,
	date_created timestamp DEFAULT current_timestamp,
	email_address varchar NOT NULL,
	first_name varchar,
	last_name varchar,
	password varchar NOT NULL,
	username varchar NOT NULL
);