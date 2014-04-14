USE AdmissionCommittee;

CREATE TABLE IF NOT EXISTS User (
    id INTEGER PRIMARY KEY not null,
    login VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    role TINYINT NOT NULL CHECK (role IN (0 , 1, 2, 3))
);

USE AdmissionCommittee;

CREATE TABLE IF NOT EXISTS Subjects (
    id INTEGER PRIMARY KEY not null AUTO_INCREMENT,
    name CHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Faculty (
    id INTEGER PRIMARY KEY not null AUTO_INCREMENT,
    name CHAR(255) NOT NULL UNIQUE,
    check_rating FLOAT UNSIGNED NOT NULL,
    recruit_plan INTEGER UNSIGNED NOT NULL
);

USE AdmissionCommittee;

CREATE TABLE IF NOT EXISTS Abiturient (
    id INTEGER PRIMARY KEY not null,
    name CHAR(255) NOT NULL,
    patronomic CHAR(255) NOT NULL,
    surname CHAR(255) NOT NULL,
    diplom_rating FLOAT UNSIGNED NOT NULL,
    faculty_id INTEGER NOT NULL
);
ALTER TABLE Abiturient
ADD CONSTRAINT FOREIGN KEY (faculty_id) REFERENCES Faculty (id)
ON UPDATE CASCADE
ON DELETE CASCADE;

USE AdmissionCommittee;

CREATE TABLE IF NOT EXISTS Rating (
    id INTEGER AUTO_INCREMENT not null UNIQUE,
    subject_id INTEGER NOT NULL,
    abiturient_id INTEGER NOT NULL,
    mark INTEGER UNSIGNED NOT NULL default 0,
    CONSTRAINT PRIMARY KEY (subject_id, abiturient_id),
    CONSTRAINT FOREIGN KEY (subject_id)
        REFERENCES Subjects (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (abiturient_id)
        REFERENCES Abiturient (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

USE AdmissionCommittee;

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
);