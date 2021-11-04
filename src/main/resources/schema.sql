drop table if exists user;

create table user (
	id INT AUTO_INCREMENT PRIMARY KEY,
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	email VARCHAR(50),
	username VARCHAR(50)
);