package ua.foxminded.javaspring.output;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.model.StudentsToCourse;

public class ShowListOfCoursesOfStudent {
	private final StudentDAO studentDAO;

	public ShowListOfCoursesOfStudent(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void showListOfCoursesOfStudent(Student studentId) throws SQLException {

		List<StudentsToCourse> courses = studentDAO.listOfCoursesOfStudent(studentId);
		int counter = 1;

		for (StudentsToCourse studentsOfCourse : courses) {
			int enrollmentId = studentsOfCourse.getEnrollmentId();
			String firstName = studentsOfCourse.getFirstName();
			String lastName = studentsOfCourse.getLastName();
			String courseName = studentsOfCourse.getCourseName();
			String courseDescription = studentsOfCourse.getCourseDescription();
			if (counter == 1) {
				System.out.println("Student " + firstName + " " + lastName + " relate to next courses");
			}
			System.out.println(enrollmentId + ". " + "Course- " + courseName + "\n Description- " + courseDescription);
			counter++;
		}
	}
}
