package by.leshiy.db;

public class Abiturient extends Entity {

    private String name;
    private String patronomic;
    private String surname;
    private float diplomRating;
    private Faculty faculty;
    private boolean registrationMark;

    public boolean isRegistrationMark() {
	return registrationMark;
    }

    public void setRegistrationMark(boolean registrationMark) {
	this.registrationMark = registrationMark;
    }

    public float getDiplomRating() {
	return diplomRating;
    }

    public void setDiplomRating(float diplomRating) {
	this.diplomRating = diplomRating;
    }

    public Faculty getFaculty() {
	return faculty;
    }

    public void setFaculty(Faculty faculty) {
	this.faculty = faculty;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPatronomic() {
	return patronomic;
    }

    public void setPatronomic(String patronomic) {
	this.patronomic = patronomic;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

}
