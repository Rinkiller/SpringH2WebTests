CREATE TABLE IF NOT EXISTS "USERTABLE" (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(50) NOT NULL
);
INSERT INTO "USERTABLE" ( firstName, lastName, age, email) VALUES ('Igor', 'Ivanov',40,'Niget@mail.ru');
INSERT INTO "USERTABLE" ( firstName, lastName, age, email) VALUES ('Oleg', 'Jorin',45,'jgmfr@mail.ru');
INSERT INTO "USERTABLE" ( firstName, lastName, age, email) VALUES ('Slava', 'Kominer',17,'SlKom@mail.ru');