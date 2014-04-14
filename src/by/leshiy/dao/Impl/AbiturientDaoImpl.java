package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.AbiturientDao;
import by.leshiy.db.Abiturient;
import by.leshiy.db.Entity;
import by.leshiy.db.Faculty;

public class AbiturientDaoImpl extends DaoImpl implements AbiturientDao {
    private final String SELECT_SQL = "SELECT id, name, patronomic, surname, diplom_rating, faculty_id, registrationMark FROM Abiturient";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Abiturient WHERE id = ?";
    private final String UPDATE_SQL = "UPDATE Abiturient WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM Abiturient WHERE id = ?";
    private final String ADD_SQL = "INSERT Abiturient (faculty_id, diplom_rating, surname, name, patronomic, registrationMark) VALUES (?, ?, ?, ?, ?)";

    public AbiturientDaoImpl(Connection connection) {
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
		Abiturient abitTemp = new Abiturient();
		abitTemp = new Abiturient();
		abitTemp.setId(rs.getInt(1));
		abitTemp.setName(rs.getString(2));
		abitTemp.setPatronomic(rs.getString(3));
		abitTemp.setSurname(rs.getString(4));
		abitTemp.setDiplomRating(rs.getInt(5));

		Faculty facTemp = new Faculty();
		facTemp.setId((rs.getInt(6)));
		abitTemp.setFaculty(facTemp);

		abitTemp.setRegistrationMark(rs.getBoolean(7));

		result.add(abitTemp);
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
     * @see by.leshiy.dao.Impl.AbiturientDao#add(by.leshiy.db.Db)
     */
    @Override
    public void add(Entity db) {
	try {
	    PreparedStatement ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setString(4, ((Abiturient) db).getName());
	    ps.setString(5, ((Abiturient) db).getPatronomic());
	    ps.setString(3, ((Abiturient) db).getSurname());
	    ps.setFloat(2, ((Abiturient) db).getDiplomRating());
	    ps.setInt(1, ((Abiturient) db).getFaculty().getId());
	    ps.setBoolean(6, ((Abiturient) db).isRegistrationMark());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
