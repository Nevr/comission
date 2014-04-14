package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.SubjectsDao;
import by.leshiy.db.Entity;
import by.leshiy.db.Subjects;

public class SubjectsDaoImpl extends DaoImpl implements SubjectsDao {

    private final String SELECT_SQL = "SELECT id, name FROM Subjects";
    private final String SELECT_BY_ID_SQL = "SELECT id, name FROM Subjects WHERE id = ?";
    private final String UPDATE_SQL = "UPDATE Subjects WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM Subjects WHERE id = ?";
    private final String ADD_SQL = "INSERT Subjects (rating) VALUES (?)";

    public SubjectsDaoImpl(Connection connection) {
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
		Subjects subTemp = new Subjects();
		subTemp.setId(rs.getInt(1));
		subTemp.setName(rs.getString(2));
		result.add(subTemp);
	    }
	} catch (NumberFormatException | SQLException e) {
	    e.printStackTrace();
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.SubjectsDao#add(by.leshiy.db.Db)
     */
    @Override
    public void add(Entity db) {
	try {
	    PreparedStatement ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setString(1, ((Subjects) db).getName());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
