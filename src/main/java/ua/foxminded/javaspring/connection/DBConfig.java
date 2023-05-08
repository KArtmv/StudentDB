package ua.foxminded.javaspring.connection;

public class DBConfig {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/studentDB";
	private String jdbcUsername = "admin";
	private String jdbcPassword = "admin";

	public String getJdbcURL() {
		return jdbcURL;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}
}