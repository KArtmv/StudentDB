package ua.foxminded.javaspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.javaspring.connection.ConnectionProvider;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.model.StudentsToCourse;

public class StudentDAO {

	private final ConnectionProvider connectionProvider;

	public StudentDAO(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public boolean insertStudent(Student student) throws SQLException {
		String sql = "INSERT INTO students (First_name, Last_name, Group_id) VALUES (?, ?, ?)";

		boolean rowInserted;

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setInt(3, student.getGroupId());

			rowInserted = statement.executeUpdate() > 0;
		}
		return rowInserted;
	}

	public boolean deleteStudent(Student studentId) throws SQLException {
		String sql = "delete from students where student_id = ?";

		boolean rowDeleted;

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, studentId.getStudentId());

			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public List<Student> listOfStudent() throws SQLException {
		String sql = "select student_id from public.students";

		List<Student> students = new ArrayList<>();
		try (Connection jdbcConnection = connectionProvider.connect();
				Statement statement = jdbcConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int studentId = resultSet.getInt("student_id");
				students.add(new Student(studentId));
			}
		}
		return students;
	}

	public List<StudentsToCourse> listOfCoursesOfStudent(Student studentId) throws SQLException {
		List<StudentsToCourse> courses = new ArrayList<>();

		String sql = "select sc.enrollment_id, s.first_name, s.last_name, c.course_name, c.course_description\n"
				+ "from students s join studenttocourse sc on s.student_id = sc.student_id\n"
				+ "join courses c on sc.course_id = c.course_id\n" 
				+ "where s.student_id=?";

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, studentId.getStudentId());

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int enrollmentId = resultSet.getInt("enrollment_id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String courseName = resultSet.getString("course_name");
				String courseDescription = resultSet.getString("course_description");

				courses.add(new StudentsToCourse(enrollmentId, firstName, lastName, courseName, courseDescription));
			}
			return courses;
		}
	}

	public boolean isValidStudentId(Student studentId) throws SQLException {
		String sql = "SELECT student_id FROM students WHERE student_id = ?";

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, studentId.getStudentId());

			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		}
	}
}
