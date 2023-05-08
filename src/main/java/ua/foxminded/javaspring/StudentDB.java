package ua.foxminded.javaspring;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import liquibase.exception.LiquibaseException;
import ua.foxminded.javaspring.connection.ConnectionProvider;
import ua.foxminded.javaspring.util.ManageTables;

public class StudentDB {

	private static final String FILE_OF_SCRIPTS = "master.xml";
	private static final String schemaName = "public";

	public static void main(String[] args) throws SQLException, LiquibaseException {
		Logger.getLogger("liquibase").setLevel(Level.OFF);

		ConnectionProvider connectionProvider = new ConnectionProvider();

		ManageTables createTables = new ManageTables(connectionProvider);
		createTables.manageTables(FILE_OF_SCRIPTS, schemaName);

		Start start = new Start(connectionProvider);
		start.start();
	}
}