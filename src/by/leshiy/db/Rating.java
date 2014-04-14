package by.leshiy.db;

public class Rating extends Entity {

    private Abiturient abiturient;
    private Subjects subjects;
    private int mark;

    public Abiturient getAbiturient() {
	return abiturient;
    }

    public void setAbiturient(Abiturient abiturient) {
	this.abiturient = abiturient;
    }

    public Subjects getSubjects() {
	return subjects;
    }

    public void setSubjects(Subjects subjects) {
	this.subjects = subjects;
    }

    public int getMark() {
	return mark;
    }

    public void setMark(int rating) {
	this.mark = rating;
    }

}
