package by.leshiy.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import by.leshiy.dao.AbiturientDao;
import by.leshiy.dao.Dao;
import by.leshiy.dao.DaoManager;
import by.leshiy.dao.FacultyDao;
import by.leshiy.dao.RatingDao;
import by.leshiy.dao.SubjectsDao;
import by.leshiy.dao.SubjectsListDao;
import by.leshiy.dbHelp.ConnectionPool;

public class DaoManagerImpl implements DaoManager {

    private static Map<Class<? extends Dao>, Class<? extends DaoImpl>> factoryDao = new HashMap<>();
    private Connection connection;
    int i = 0;

    protected DaoManagerImpl() {
	connection = ConnectionPool.getConnPool().getConnection();
    }

    static {
	factoryDao.put(AbiturientDao.class, AbiturientDaoImpl.class);
	factoryDao.put(FacultyDao.class, FacultyDaoImpl.class);
	factoryDao.put(SubjectsDao.class, SubjectsDaoImpl.class);
	factoryDao.put(RatingDao.class, RatingDaoImpl.class);
	factoryDao.put(SubjectsListDao.class, SubjectsListDaoImpl.class);
    }

    public DaoImpl getDao(Class<? extends Dao> key) {
	try {
	    return factoryDao.get(key).getConstructor(Connection.class).newInstance(connection);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return null;
    }

    public void transactionCommit() {
	try {
	    connection.commit();
	    System.out.println(connection + " коммитим Connection в DaoManagerImpl");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void transactionRollback() {
	try {
	    connection.rollback();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void transactionClose() {
	ConnectionPool.getConnPool().releaseConnection(connection);
	System.out.println(connection + " закрываем (отдаем в пул) Connection в DaoManagerImpl");
    }
}
