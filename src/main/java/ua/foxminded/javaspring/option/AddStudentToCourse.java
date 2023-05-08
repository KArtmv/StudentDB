package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.input.InputNumbers;
import ua.foxminded.javaspring.input.InputStudentId;
import ua.foxminded.javaspring.model.Course;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.output.ShowListOfCourses;

public class AddStudentToCourse {

	private final StudentDAO studentDAO;
	private final CourseDAO сourseDAO;
	private final ShowListOfCourses listOfCourses;
	private final Scanner scanner;

	public AddStudentToCourse(StudentDAO studentDAO, CourseDAO сourseDAO, ShowListOfCourses listOfCourses,
			Scanner scanner) {
		this.studentDAO = studentDAO;
		this.сourseDAO = сourseDAO;
		this.listOfCourses = listOfCourses;
		this.scanner = scanner;
	}

	public void addStudentToCourse() throws SQLException {

		InputNumbers inputNumbers = new InputNumbers();

		InputStudentId insertStudentId = new InputStudentId(studentDAO);
		Student studentId = insertStudentId.inputStudentId(scanner);

		listOfCourses.showListofCourses();

		Course courseId = new Course(inputNumbers.inputNumbers(scanner));

		boolean added = сourseDAO.addStudentAtCourse(studentId, courseId);

		if (added) {
			System.out.println("Student have been added at course");
		}
	}
}
