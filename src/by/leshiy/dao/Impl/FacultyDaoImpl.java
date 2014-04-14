package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.FacultyDao;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;

public class FacultyDaoImpl extends DaoImpl implements FacultyDao {

    private final String SELECT_SQL = "SELECT id, name, recruit_plan FROM Faculty";
    private final String SELECT_BY_ID_SQL = "SELECT id, name, recruit_plan FROM Faculty WHERE id = ?";
    private final String UPDATE_SQL = "UPDATE Faculty WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM Faculty WHERE id = ?";
    private final String ADD_SQL = "INSERT Faculty (name, recruit_plan) VALUES (?, ?, ?)";

    public FacultyDaoImpl(Connection connection) {
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
		Faculty facTemp = new Faculty();
		facTemp.setId(rs.getInt(1));
		facTemp.setName(rs.getString(2));
		facTemp.setRecruitPlan(rs.getInt(3));
		result.add(facTemp);
	    }
	} catch (NumberFormatException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see by.leshiy.dao.Impl.FacultyDao#add(by.leshiy.db.Db)
     */
    @Override
    public void add(Entity db) {
	try {
	    PreparedStatement ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setString(1, ((Faculty) db).getName());
	    ps.setInt(2, ((Faculty) db).getRecruitPlan());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}