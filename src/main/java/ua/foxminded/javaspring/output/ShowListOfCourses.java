package ua.foxminded.javaspring.output;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.model.Course;

public class ShowListOfCourses {

	private final CourseDAO courseDAO;

	public ShowListOfCourses(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void showListofCourses() throws SQLException {
		List<Course> courses = courseDAO.listOfCourse();

		for (Course course : courses) {
			int courseId = course.getCourseId();
			String courseName = course.getCourseName();
			String courseDescription = course.getCourseDescription();
			System.out.println(courseId + ". Course- " + courseName + "\nDescription- " + courseDescription);
			System.out.println();
		}
	}
}
