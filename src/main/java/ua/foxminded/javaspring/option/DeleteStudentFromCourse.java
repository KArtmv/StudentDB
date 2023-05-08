package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.input.InputNumbers;
import ua.foxminded.javaspring.input.InputStudentId;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.model.StudentsToCourse;
import ua.foxminded.javaspring.output.ShowListOfCoursesOfStudent;

public class DeleteStudentFromCourse {

	private final StudentDAO studentDAO;
	private final CourseDAO courseDAO;
	private final Scanner scanner;

	public DeleteStudentFromCourse(StudentDAO studentDAO, CourseDAO courseDAO, Scanner scanner) {
		this.studentDAO = studentDAO;
		this.courseDAO = courseDAO;
		this.scanner = scanner;
	}

	public void deleteStudentFromCourse() throws SQLException {
		InputStudentId insertStudentId = new InputStudentId(studentDAO);

		Student student = insertStudentId.inputStudentId(scanner);

		ShowListOfCoursesOfStudent coursesOfStudent = new ShowListOfCoursesOfStudent(studentDAO);
		coursesOfStudent.showListOfCoursesOfStudent(student);

		System.out.println("Enter number of course of student which should be delete");

		InputNumbers inputNumbers = new InputNumbers();
		StudentsToCourse enrollmentId = new StudentsToCourse(inputNumbers.inputNumbers(scanner));

		boolean deleted = courseDAO.deleteStudentFromTheCourse(enrollmentId);
		if (deleted) {
			System.out.println("Student deleted from course");
		}
	}
}