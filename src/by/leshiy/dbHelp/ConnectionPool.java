package by.leshiy.dbHelp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

    private String driver;
    private String login;
    private String password;
    private String db;
    private final String dbPropertyFile = "by.leshiy.property.dbConnectProperty";
    private static ConnectionPool connect;
    private final int maxConn = 10;
    private Connection[] ConnArray = new Connection[maxConn];
    private int connectInUse = 0;
    private Connection con;

    private ConnectionPool() {
	for (int i = 0; i < maxConn; i++) {
	    ConnArray[i] = setConnection();
	}
    }

    private Connection setConnection() {
	ResourceBundle dbProperty = ResourceBundle.getBundle(dbPropertyFile);
	db = dbProperty.getString("url");
	driver = dbProperty.getString("driver");
	login = dbProperty.getString("login");
	password = dbProperty.getString("password");
	try {
	    Class.forName(driver);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	try {
	    con = DriverManager.getConnection(db, login, password);
	    con.setAutoCommit(false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return con;
    }

    static public ConnectionPool getConnPool() {
	if (connect == null) {
	    connect = new ConnectionPool();
	}
	return connect;
    }

    synchronized public Connection getConnection() {
	Connection conn = null;
	while (connectInUse == 10) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}
	connectInUse++;
	for (int i = 0; i < maxConn; i++) {
	    if (ConnArray[i] != null) {
		conn = ConnArray[i];
		ConnArray[i] = null;
		break;
	    }
	}
	System.out.println(conn + " выдача Connection в ConnectionPool");
	notify();
	return conn;
    }

    synchronized public void releaseConnection(Connection conn) {
	connectInUse--;
	try {
	    conn.rollback();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	for (int i = 0; i < maxConn; i++) {
	    if (ConnArray[i] == null) {
		ConnArray[i] = conn;
		break;
	    }
	}
	notify();
    }

    synchronized public void closeAll() {
	for (int i = 0; i < maxConn; i++) {
	    if (ConnArray[i] != null) {
		try {
		    ConnArray[i].rollback();
		    ConnArray[i].close();
		} catch (Exception e) {
		}
	    }
	}
	ConnArray = null;
    }
}
