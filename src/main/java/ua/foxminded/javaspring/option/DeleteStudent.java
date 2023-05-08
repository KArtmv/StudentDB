package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.input.InputStudentId;
import ua.foxminded.javaspring.model.Student;

public class DeleteStudent {

	private final StudentDAO studentDAO;
	private final CourseDAO courseDAO;
	private final Scanner scanner;

	public DeleteStudent(StudentDAO studentDAO, CourseDAO courseDAO, Scanner scanner) {
		this.studentDAO = studentDAO;
		this.courseDAO = courseDAO;
		this.scanner = scanner;
	}

	public void deleteStudent() throws SQLException {
		InputStudentId id = new InputStudentId(studentDAO);
		Student student = id.inputStudentId(scanner);

		boolean deleted = courseDAO.deleteStudentFromTheirAllCourses(student);

		if (deleted) {
			studentDAO.deleteStudent(student);
		}
		System.out.println("Student is deleted");
	}
}
