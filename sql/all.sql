DROP DATABASE IF EXISTS AdmissionCommittee;

CREATE database IF NOT EXISTS  AdmissionCommittee DEFAULT CHARACTER SET utf8;
USE AdmissionCommittee;

CREATE TABLE IF NOT EXISTS User (
    id INTEGER PRIMARY KEY not null,
    login VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    role TINYINT NOT NULL CHECK (role IN (0 , 1))
);

CREATE TABLE IF NOT EXISTS Subjects (
    id INTEGER PRIMARY KEY not null AUTO_INCREMENT,
    name CHAR(255) NOT NULL UNIQUE
) DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS Faculty (
    id INTEGER PRIMARY KEY not null AUTO_INCREMENT,
    name CHAR(255) NOT NULL UNIQUE,
    recruit_plan INTEGER NOT NULL
)DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS Abiturient (
    id INTEGER PRIMARY KEY not null,
    name CHAR(255) NOT NULL,
    patronomic CHAR(255) NOT NULL,
    surname CHAR(255) NOT NULL,
    diplom_rating FLOAT NOT NULL,
    faculty_id INTEGER NOT NULL,
    registrationMark boolean not null default false,
    CONSTRAINT FOREIGN KEY (faculty_id) 
    	REFERENCES Faculty (id)
		ON UPDATE CASCADE ON DELETE CASCADE
)DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS Rating (
    id INTEGER AUTO_INCREMENT not null UNIQUE,
    subject_id INTEGER NOT NULL,
    abiturient_id INTEGER NOT NULL,
    mark INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY (subject_id, abiturient_id),
    CONSTRAINT FOREIGN KEY (subject_id)
        REFERENCES Subjects (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (abiturient_id)
        REFERENCES Abiturient (id)
        ON UPDATE CASCADE ON DELETE CASCADE
)DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS SubjectsList (
    id INTEGER not null AUTO_INCREMENT UNIQUE,
    faculty_id INTEGER NOT NULL,
    subject_id INTEGER NOT NULL,
    CONSTRAINT PRIMARY KEY (faculty_id, subject_id),
    CONSTRAINT FOREIGN KEY (faculty_id)
        REFERENCES Faculty (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (subject_id)
        REFERENCES Subjects (id)
        ON UPDATE CASCADE ON DELETE CASCADE
)DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE, RELOAD, SHUTDOWN, PROCESS, FILE, SUPER ON *.* 
TO 'Administrator'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;

GRANT SELECT ON AdmissionCommittee.Faculty
TO 'user'@'%' IDENTIFIED BY 'user';

GRANT SELECT ON AdmissionCommittee.Subjects 
TO 'user'@'%' IDENTIFIED BY 'user';

set names utf8;

USE AdmissionCommittee;

INSERT subjects (name) 
VALUES 
	("Математика"), 
	("Физика"), 
	("Русский язык"), 
	("Белорусский язык"), 
	("Химия"),
	("Биология");
INSERT faculty (name, recruit_plan) 
VALUES 
	("Механико-технологический", 55), 
	("Конструкторско-технологический", 40), 
	("Художественный", 25), 
	("Экономический", 80);
INSERT subjectslist (faculty_id, subject_id) 
VALUES
	(1, 1), 
	(1, 2), 
	(1, 4), 
	(2, 1), 
	(2, 2), 
	(2, 3), 
	(3, 3), 
	(3, 4), 
	(3, 5), 
	(4, 3), 
	(4, 4), 
	(4, 6);
INSERT abiturient (id, faculty_id, diplom_rating, surname, name, patronomic) 
VALUES 
	(2, 2, 8.1, "Веселов", "Иван", "Васильевич"),
	(3, 4, 5.5, "Мендель", "Жанна", "Петровна"),
	(4, 1, 7.4, "Хорстман", "Петр", "Иванович"),
	(5, 3, 6, "Синичкина", "Инна", "Захаровна"),
	(6, 2, 4.1, "Агеев", "Олег", "Дмитриевич");
	
INSERT rating (abiturient_id,subject_id, mark) 
VALUES
	(2, 1, 6),
	(2, 2, 7),
	(2, 3, 7),
	(3, 1, 5),
	(3, 2, 3),
	(3, 3, 7),
	(4, 3, 1),
	(4, 4, 3),
	(4, 5, 8),
	(4, 6, 7),
	(5, 6, 5),
	(5, 3, 4),
	(5, 4, 8),
	(6, 1, 7),
	(6, 2, 6),
	(6, 3, 5),
	(6, 4, 7),
	(6, 5, 6),
	(6, 6, 2);
	
/* id = abiturient.id*/	
INSERT INTO user (id, login, password, role) 
VALUES
	(1, "admin", "21232F297A57A5A743894A0E4A801FC3", 0), /* MD5 хэш пароля "admin" */
	(2, "2@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(3, "3@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(4, "4@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(5, "5@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(6, "6@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1); /* MD5 хэш пароля "user" */