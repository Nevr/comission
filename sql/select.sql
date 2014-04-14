USE AdmissionCommittee;

SELECT * FROM abiturient;
SELECT * FROM faculty;
SELECT * FROM subjects;
SELECT * FROM subjectsList;
SELECT * FROM rating;
SELECT * FROM user;

SELECT 
	faculty.name,
    abiturient.surname,
    abiturient.name,
    subjects.name,
    rating.mark
FROM
    abiturient
        inner join
    rating ON (abiturient.id = rating.abiturient_id)
        right join
    subjects ON (rating.subject_id = subjects.id)
            left join
    faculty ON (abiturient.faculty_id = faculty.id)
order by abiturient.surname asc;