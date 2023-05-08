package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.input.InputNumbers;
import ua.foxminded.javaspring.model.Course;
import ua.foxminded.javaspring.model.StudentsToCourse;
import ua.foxminded.javaspring.output.ShowListOfCourses;

public class FindAllStudentsAtCourse {

	private final CourseDAO сourseDAO;
	private final ShowListOfCourses listOfCourses;
	private final Scanner scanner;

	public FindAllStudentsAtCourse(CourseDAO сourseDAO, ShowListOfCourses listOfCourses, Scanner scanner) {
		this.сourseDAO = сourseDAO;
		this.listOfCourses = listOfCourses;
		this.scanner = scanner;
	}

	public void findAllStudentsAtCourse() throws SQLException {
		listOfCourses.showListofCourses();

		System.out.println("Input number of course from the list for which it should show all students");
		InputNumbers inputNumbers = new InputNumbers();

		Course courseId = new Course(inputNumbers.inputNumbers(scanner));

		List<StudentsToCourse> studentsOfCourses = сourseDAO.listOfStudentsAtCourse(courseId);

		int counter = 1;
		for (StudentsToCourse studentsOfCourse : studentsOfCourses) {
			String firstName = studentsOfCourse.getFirstName();
			String lastName = studentsOfCourse.getLastName();
			String courseName = studentsOfCourse.getCourseName();
			String courseDescription = studentsOfCourse.getCourseDescription();

			if (counter == 1) {
				System.out.println("At course " + courseName + " study next students" + ",\n Descriptoin of course- "
						+ courseDescription);
				System.out.println("First name Last name:");
			}
			System.out.println(counter + ". " + firstName + " " + lastName);
			counter++;
		}
	}
}
