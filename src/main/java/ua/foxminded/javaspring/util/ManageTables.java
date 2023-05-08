package ua.foxminded.javaspring.util;

import java.sql.Connection;
import java.sql.SQLException;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import ua.foxminded.javaspring.connection.ConnectionProvider;

public class ManageTables {

	private final ConnectionProvider connectionProvider;

	public ManageTables(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public void manageTables(String fileOfScriptSchem, String schemaName) throws LiquibaseException, SQLException {

		try (Connection jdbcConnection = connectionProvider.connect()) {
			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(jdbcConnection));
			database.setDefaultSchemaName(schemaName);
			try (Liquibase liquibase = new Liquibase(fileOfScriptSchem, new ClassLoaderResourceAccessor(), database)) {
				liquibase.dropAll();
				liquibase.update(new Contexts());
			}
		}
	}
}
