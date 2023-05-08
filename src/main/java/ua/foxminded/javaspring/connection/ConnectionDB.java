
package ua.foxminded.javaspring.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	public Connection connect(DBConfig admin) throws SQLException {
		try {
			return DriverManager.getConnection(admin.getJdbcURL(), admin.getJdbcUsername(), admin.getJdbcPassword());
		} catch (SQLException e) {
			System.err.println("Failed to connect to database: " + e.getMessage());
			throw e;
		}
	}
}