package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.RatingDao;
import by.leshiy.db.Abiturient;
import by.leshiy.db.Entity;
import by.leshiy.db.Rating;
import by.leshiy.db.Subjects;

public class RatingDaoImpl extends DaoImpl implements RatingDao {

    private final String SELECT_SQL = "SELECT id, subject_id, abiturient_id, mark FROM Rating";
    private final String SELECT_BY_ID_SQL = "SELECT id, subject_id, abiturient_id, mark FROM Rating where id = ?";
    private final String SELECT_BY_ABITURIENT_SQL = "SELECT id FROM Rating WHERE abiturient_id = ?";
    private final String SELECT_BY_SUBJECT_SQL = "SELECT id FROM Rating WHERE subject_id = ?";
    private final String UPDATE_SQL = "UPDATE Rating WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM Rating WHERE id = ?";
    private final String ADD_SQL = "INSERT Rating (subject_id, abiturient_id, mark) VALUES (?, ?, ?)";

    public RatingDaoImpl(Connection connection) {
	super(connection);
	super.SELECT_SQL = SELECT_SQL;
	super.SELECT_BY_ID_SQL = SELECT_BY_ID_SQL;
	super.UPDATE_SQL = UPDATE_SQL;
	super.DELETE_SQL = DELETE_SQL;
    }

    @Override
    protected List<Entity> parse(ResultSet rs) {
	List<Entity> result = new ArrayList<>();
	try {
	    while (rs.next()) {
		Rating ratingTemp = new Rating();
		Abiturient abiturient = new Abiturient();
		Subjects subject = new Subjects();
		ratingTemp.setId(rs.getInt(1));
		subject.setId(rs.getInt(2));
		ratingTemp.setSubjects(subject);
		abiturient.setId(rs.getInt(3));
		ratingTemp.setAbiturient(abiturient);
		ratingTemp.setMark(rs.getInt(4));
		result.add(ratingTemp);
	    }
	} catch (NumberFormatException | SQLException e) {
	    e.printStackTrace();
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.RatingDao#getByField(java.lang.String, java.lang.String)
     */
    @Override
    public List<String> getByField(String field, String id) {
	switch (field) {
	case "abiturient_id":
	    return getStringFromDb(SELECT_BY_ABITURIENT_SQL, id);
	case "subject_id":
	    return getStringFromDb(SELECT_BY_SUBJECT_SQL, id);
	default: {
	    return null;
	}
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.RatingDao#add(by.leshiy.db.Db)
     */
    @Override
    public void add(Entity db) {
	try {
	    PreparedStatement ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setInt(1, (((Rating) db).getSubjects().getId()));
	    ps.setInt(2, ((Rating) db).getAbiturient().getId());
	    ps.setInt(3, ((Rating) db).getMark());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
