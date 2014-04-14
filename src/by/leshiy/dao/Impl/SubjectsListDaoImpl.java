package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.SubjectsListDao;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;
import by.leshiy.db.Subjects;
import by.leshiy.db.SubjectsList;

public class SubjectsListDaoImpl extends DaoImpl implements SubjectsListDao {

    private final String SELECT_SQL = "SELECT id, faculty_id, subject_id  FROM SubjectsList";
    private final String SELECT_BY_ID_SQL = "Select id, faculty_id, subject_id FROM SubjectsList WHERE id = ?";
    private final String SELECT_BY_FACULTY_SQL = "Select subject_id FROM SubjectsList WHERE faculty_id = ?";
    private final String SELECT_BY_SUBJECT_SQL = "Select faculty_id FROM SubjectsList WHERE subject_id = ?";
    private final String UPDATE_SQL = "UPDATE SubjectsList WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM SubjectsList WHERE id = ?";
    private final String ADD_SQL = "INSERT SubjectsList (faculty_id, subject_id) VALUES (?, ?)";

    public SubjectsListDaoImpl(Connection connection) {
	super(connection);
	super.SELECT_SQL = SELECT_SQL;
	super.SELECT_BY_ID_SQL = SELECT_BY_ID_SQL;
	super.UPDATE_SQL = UPDATE_SQL;
	super.DELETE_SQL = DELETE_SQL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.SubjectsListDao#getByField(java.lang.String, java.lang.String)
     */
    @Override
    public List<String> getByField(String field, String id) {
	switch (field) {
	case "faculty_id": {
	    return getStringFromDb(SELECT_BY_FACULTY_SQL, id);
	}
	case "subject_id": {
	    return getStringFromDb(SELECT_BY_SUBJECT_SQL, id);
	}
	default: {
	    return null;
	}
	}
    }

    @Override
    protected List<Entity> parse(ResultSet rs) {
	List<Entity> result = new ArrayList<>();
	try {
	    while (rs.next()) {
		SubjectsList subListTemp = new SubjectsList();
		Faculty faculty = new Faculty();
		Subjects subject = new Subjects();
		subListTemp.setId(rs.getInt(1));
		faculty.setId(rs.getInt(2));
		subListTemp.setFaculty(faculty);
		subject.setId(rs.getInt(3));
		subListTemp.setSubjects(subject);
		result.add(subListTemp);
	    }
	} catch (NumberFormatException | SQLException e) {
	    e.printStackTrace();
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.SubjectsListDao#add(by.leshiy.db.Db)
     */
    @Override
    public void add(Entity db) {
	try {
	    PreparedStatement ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setInt(1, ((SubjectsList) db).getFaculty().getId());
	    ps.setInt(2, ((SubjectsList) db).getSubjects().getId());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}