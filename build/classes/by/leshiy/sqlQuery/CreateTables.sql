CREATE TABLE IF NOT EXISTS  Subjects (id INTEGER PRIMARY KEY not null AUTO_INCREMENT, name CHAR(255) NOT NULL);
CREATE TABLE IF NOT EXISTS Faculty (id INTEGER PRIMARY KEY not null AUTO_INCREMENT, name CHAR(255) NOT NULL, check_rating INTEGER NOT NULL, recruit_plan INTEGER NOT NULL);
CREATE TABLE IF NOT EXISTS Abiturient (id INTEGER PRIMARY KEY not null AUTO_INCREMENT, name CHAR(255) NOT NULL, patronomic CHAR(255) NOT NULL, surname CHAR(255) NOT NULL, diplom_rating INTEGER NOT NULL, faculty_id INTEGER NOT NULL, FOREIGN KEY (faculty_id) REFERENCES Faculty (id));
CREATE TABLE IF NOT EXISTS SubjectsList (id INTEGER PRIMARY KEY not null AUTO_INCREMENT, faculty_id INTEGER NOT NULL, subject_id INTEGER NOT NULL, FOREIGN KEY (faculty_id) REFERENCES Faculty (id), FOREIGN KEY (subject_id) REFERENCES Subjects (id));
CREATE TABLE IF NOT EXISTS Statement (id INTEGER PRIMARY KEY not null AUTO_INCREMENT, faculty_id INTEGER NOT NULL, abiturient_id INTEGER NOT NULL, FOREIGN KEY (faculty_id) REFERENCES Faculty (id), FOREIGN KEY (abiturient_id) REFERENCES Abiturient (id));
CREATE TABLE IF NOT EXISTS Rating (id INTEGER not null AUTO_INCREMENT PRIMARY KEY, subject_id INTEGER NOT NULL, abiturient_id INTEGER NOT NULL, rating INTEGER NOT NULL  default 0, FOREIGN KEY (subject_id) REFERENCES Subjects (id), FOREIGN KEY (abiturient_id) REFERENCES Abiturient (id));
