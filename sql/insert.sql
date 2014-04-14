USE AdmissionCommittee;

INSERT subjects (name) 
VALUES 
	("Математика"), 
	("Физика"), 
	("Русский язык"), 
	("Белорусский язык"), 
	("Химия"),
	("Биология");
INSERT faculty (name, check_rating, recruit_plan) 
VALUES 
	("Механико-технологический", 22,55), 
	("Конструкторско-технологический", 24,40), 
	("Художественный", 20,25), 
	("Экономический", 29,80);
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
INSERT abiturient (faculty_id, diplom_rating, surname, name, patronomic) 
VALUES 
	(2, 100500, "Лещев", "Павел", "Игоревич"),
	(2, 8.1, "Веселов", "Иван", "Васильевич"),
	(4, 5.5, "Мендель", "Жанна", "Петровна"),
	(1, 7.4, "Хорстман", "Петр", "Иванович"),
	(3, 6, "Синичкина", "Инна", "Захаровна"),
	(2, 4.1, "Агеев", "Олег", "Дмитриевич");
	
INSERT rating (abiturient_id,subject_id, mark) 
VALUES
	(1, 1, 8),
	(1, 2, 7),
	(1, 4, 4),
	(1, 5, 2),
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
	(1, "admin", "21232F297A57A5A743894A0E4A801FC3", 0), /* MD5 хэш пароля "user" */
	(2, "2@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(3, "3@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(4, "4@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(5, "5@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1), /* MD5 хэш пароля "user" */
	(6, "6@mail.ru", "EE11CBB19052E40B07AAC0CA060C23EE", 1); /* MD5 хэш пароля "user" */