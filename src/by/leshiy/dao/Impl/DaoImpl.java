package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.leshiy.dao.Dao;
import by.leshiy.db.Entity;

public abstract class DaoImpl implements Dao {

    protected String SELECT_SQL = "";
    protected String SELECT_BY_ID_SQL = "";
    protected String SELECT_BY_FIELD_SQL = "";
    protected String UPDATE_SQL = "";
    protected String DELETE_SQL = "";
    protected String ADD_SQL = "";
    protected Connection connection = null;

    public DaoImpl(Connection connection) {
	this.connection = connection;
    }

    @Override
    public List<Entity> getAll() {
	return getFromDb(SELECT_SQL);
    }

    @Override
    public List<Entity> getById(int id) {
	return getFromDb(SELECT_BY_ID_SQL, id);
    }

    @Override
    public List<String> getByField(String field, String id) {
	return getStringFromDb(SELECT_BY_FIELD_SQL, id);
    }

    @Override
    public void update(Entity db) {
	executeQuery(UPDATE_SQL, db);
    }

    @Override
    public abstract void add(Entity db);

    @Override
    public void delete(Entity db) {
	executeQuery(DELETE_SQL, db);
    }

    protected void executeQuery(String sql, Entity db) {
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setInt(1, db.getId());
	    ps.executeUpdate();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @param sql
     *            query
     * @return list of value by SQL query, using parse(ResultSet) for parsing ResultSet to List
     */
    protected List<Entity> getFromDb(String sql, int... id) {
	List<Entity> temp = new ArrayList<>();
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    if (id.length != 0) {
		ps.setInt(1, id[0]);
	    }
	    ResultSet rs = ps.executeQuery();
	    temp = parse(rs);
	    rs.close();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return temp;
    }

    /**
     * @param sql
     *            query
     * @return list of value of single column by SQL query
     */
    protected List<String> getStringFromDb(String sql, String id) {
	List<String> tempListString = new ArrayList<>();
	try {
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setString(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		tempListString.add(rs.getString(1));
	    }
	    rs.close();
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return tempListString;
    }

    /**
     * @param rs
     *            from query getAll, getById, getByField
     * @return list of objects of the DbInterface
     */
    protected abstract List<Entity> parse(ResultSet rs);

}
