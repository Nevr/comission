package by.leshiy.db;

public class SubjectsList extends Entity {

    private Faculty faculty;
    private Subjects subjects;

    public Faculty getFaculty() {
	return faculty;
    }

    public void setFaculty(Faculty faculty) {
	this.faculty = faculty;
    }

    public Subjects getSubjects() {
	return subjects;
    }

    public void setSubjects(Subjects subjects) {
	this.subjects = subjects;
    }

}
