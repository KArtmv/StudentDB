package ua.foxminded.javaspring.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {

	public Connection connect() throws SQLException {
		DBConfig config = new DBConfig();
		ConnectionDB connection = new ConnectionDB();
		return connection.connect(config);
	}

}
