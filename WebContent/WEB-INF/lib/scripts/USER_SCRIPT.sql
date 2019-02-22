DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
drop user 'admin'@'localhost';
flush privileges;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'P@ssword1';
GRANT ALL ON COMP3095.* TO 'admin'@'localhost';

CREATE TABLE USERS 
( 
	id int(11) AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255),
	lastname varchar(255),
    address varchar(255), 
    email varchar(255), 
    `password` varchar(255),
	`role` varchar(20),
	created timestamp default current_timestamp
);

INSERT INTO USERS (firstname, lastname, address, email,  `password`,`role`) VALUES('Sergio','Santilli','160 Kendal Ave','admin@localhost', 'P@ssword1','administrator');
INSERT INTO USERS (firstname, lastname, address, email,  `password`,`role`) VALUES('Max','Paxton','Toronto','maxpax@gmail.com', 'P@ssword1','user');
INSERT INTO USERS (firstname, lastname, address, email,  `password`,`role`) VALUES('Neehal','Shaikh','Toronto','nehal@gmail.com', 'P@ssword1','user');
INSERT INTO USERS (firstname, lastname, address, email,  `password`,`role`) VALUES('Nick','Entecott','Toronto','nicent@gmail.com', 'P@ssword1','user');
INSERT INTO USERS (firstname, lastname, address, email,  `password`,`role`) VALUES('Patrick','Murphy','Toronto','patrickmurphy@gmail.com', 'P@ssword1','user');

/*Use to view your USER table
SELECT * from USERS;
*/

