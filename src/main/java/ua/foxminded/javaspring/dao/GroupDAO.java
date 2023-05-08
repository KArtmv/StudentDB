package ua.foxminded.javaspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.foxminded.javaspring.connection.ConnectionProvider;
import ua.foxminded.javaspring.model.Group;

public class GroupDAO {

	private final ConnectionProvider connectionProvider;

	public GroupDAO(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public List<Group> listOfGroups() throws SQLException {
		String sql = "select * from groups";

		List<Group> groups = new ArrayList<>();

		try (Connection jdbcConnection = connectionProvider.connect();
				Statement statement = jdbcConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int groupId = resultSet.getInt("group_id");
				String groupName = resultSet.getString("group_name");

				groups.add(new Group(groupId, groupName));
			}
		}
		return groups;
	}

	public Map<Integer, Group> counterStudentsAtGroups(int count) throws SQLException {
		String sql = "SELECT g.group_id, g.group_name, COUNT(s.student_id) AS student_count\n"
				+ "FROM groups g\n"
				+ "LEFT JOIN students s ON g.group_id = s.group_id\n"
				+ "GROUP BY g.group_id\n"
				+ "HAVING COUNT(s.student_id) <=?";

		Map<Integer, Group> countOfStudentsatCourse = new HashMap<>();
		
		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, count);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int idOfGroup = resultSet.getInt("group_id");
				String groupName = resultSet.getString("group_name");
				Integer countOfStudents = resultSet.getInt("student_count");

				countOfStudentsatCourse.put(countOfStudents, new Group(idOfGroup, groupName));
			}
			return countOfStudentsatCourse;
		}
	}
}
