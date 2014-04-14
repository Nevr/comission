package by.leshiy.db;

import java.util.List;

public class Faculty extends Entity {

    private int id;
    private String name;
    private float checkRating;
    private int recruitPlan;
    private List<Subjects> subjects;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the subjects
     */
    public List<Subjects> getSubjects() {
	return subjects;
    }

    /**
     * @param subjects
     *            the subjects to set
     */
    public void setSubjects(List<Subjects> subjects) {
	this.subjects = subjects;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the checkRating
     */
    public float getCheckRating() {
	return checkRating;
    }

    /**
     * @param checkRating
     *            the checkRating to set
     */
    public void setCheckRating(float checkRating) {
	this.checkRating = checkRating;
    }

    /**
     * @return the recruitPlan
     */
    public int getRecruitPlan() {
	return recruitPlan;
    }

    /**
     * @param recruitPlan
     *            the recruitPlan to set
     */
    public void setRecruitPlan(int recruitPlan) {
	this.recruitPlan = recruitPlan;
    }
}
