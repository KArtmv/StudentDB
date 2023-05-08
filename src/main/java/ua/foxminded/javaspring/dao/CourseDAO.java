package ua.foxminded.javaspring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.javaspring.connection.ConnectionProvider;
import ua.foxminded.javaspring.model.Course;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.model.StudentsToCourse;

public class CourseDAO {

	private final ConnectionProvider connectionProvider;

	public CourseDAO(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	public List<Course> listOfCourse() throws SQLException {
		String sql = "select * from courses";

		List<Course> courses = new ArrayList<>();
		try (Connection jdbcConnection = connectionProvider.connect();
				Statement statement = jdbcConnection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int courseId = resultSet.getInt("course_id");
				String courseName = resultSet.getString("course_name");
				String courseDescription = resultSet.getString("course_description");
				courses.add(new Course(courseId, courseName, courseDescription));
			}
		}
		return courses;
	}

	public List<StudentsToCourse> listOfStudentsAtCourse(Course courseId) throws SQLException {
		String slq = "select s.first_name, s.last_name, c.course_name, c.course_description\n"
				+ "from students s\n"
				+ "join studenttocourse st on s.student_id = st.student_id\n"
				+ "join courses c on st.course_id = c.course_id\n"
				+ "where c.course_id=?";

		List<StudentsToCourse> studentsOfCourse = new ArrayList<>();

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(slq)) {
			statement.setInt(1, courseId.getCourseId());

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String courseName = resultSet.getString("course_name");
				String courseDescription = resultSet.getString("course_description");

				studentsOfCourse.add(new StudentsToCourse(lastName, firstName, courseName, courseDescription));
			}
		}
		return studentsOfCourse;
	}

	public boolean deleteStudentFromTheirAllCourses(Student studentId) throws SQLException {
		String sql = "delete from studenttocourse where student_id =?";

		boolean rowInserted;
		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, studentId.getStudentId());

			rowInserted = statement.executeUpdate() > 0;
		}
		return rowInserted;
	}

	public boolean addStudentAtCourse(Student studentId, Course courseId) throws SQLException {
		String sql = "INSERT INTO studenttocourse (student_id, course_id) VALUES (?, ?)";

		boolean rowInserted;

		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, studentId.getStudentId());
			statement.setInt(2, courseId.getCourseId());

			rowInserted = statement.executeUpdate() > 0;

		}
		return rowInserted;
	}

	public boolean deleteStudentFromTheCourse(StudentsToCourse enrollmenId) throws SQLException {
		String sql = "delete from studenttocourse where enrollment_id = ?";

		boolean rowDeleted;
		try (Connection jdbcConnection = connectionProvider.connect();
				PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
			statement.setInt(1, enrollmenId.getEnrollmentId());

			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}
