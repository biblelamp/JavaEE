package simplewebapp.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnUtils {

	public static Connection getSQLiteConnection()
	        throws ClassNotFoundException, SQLException {

	    String fileName = "E:\\Java\\JavaEE\\SimpleWebApp\\WebContent\\sqlite.db";
	    return getSQLiteConnection(fileName);
	}

	public static Connection getSQLiteConnection(String fileName)
	    throws SQLException, ClassNotFoundException {

	    // Declare the class Driver for SQLite DB
	    Class.forName("org.sqlite.JDBC");

	    // URL Connection for SQLite
	    // Example: jdbc:sqlite:file_sqlite.db
	    String connectionURL = "jdbc:sqlite:" + fileName;

	    Connection conn = DriverManager.getConnection(connectionURL);
	    return conn;
	}
}