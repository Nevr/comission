package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.AbiturientDao;
import by.leshiy.db.Entity;
import by.leshiy.db.Role;
import by.leshiy.db.User;

public class UserDaoImpl extends DaoImpl implements AbiturientDao {
    private final String SELECT_SQL = "SELECT id, login, password, role FROM User";
    private final String SELECT_BY_ID_SQL = "SELECT id, login, password, role FROM User WHERE id = ?";
    private final String UPDATE_SQL = "UPDATE User WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM User WHERE id = ?";
    private final String ADD_SQL = "INSERT User (id, login, password, role) VALUES (?, ?, ?, ?)";

    public UserDaoImpl(Connection connection) {
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
		User userTemp = new User();
		userTemp.setId(rs.getInt(1));
		userTemp.setLogin(rs.getString(2));
		userTemp.setPassword(rs.getString(3));
		userTemp.setRole(Role.getRole(rs.getInt(4)));
		result.add(userTemp);
	    }
	} catch (SQLException e) {
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
	PreparedStatement ps;
	try {
	    ps = super.connection.prepareStatement(ADD_SQL);
	    ps.setInt(1, ((User) db).getId());
	    ps.setString(2, ((User) db).getLogin());
	    ps.setString(3, ((User) db).getPassword());
	    ps.setInt(4, ((User) db).getRole().ordinal());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
