package by.leshiy.db;

import java.util.List;

/**
 * @author Pavel Leshchov
 * 
 */
public class Subjects extends Entity {

    private String name;
    private List<Faculty> faculty;

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
     * @return the faculty
     */
    public List<Faculty> getFaculty() {
	return faculty;
    }

    /**
     * @param faculty
     *            the faculty to set
     */
    public void setFaculty(List<Faculty> faculty) {
	this.faculty = faculty;
    }

}
