package ua.foxminded.javaspring.model;

public class StudentsToCourse {

	private int enrollmentId;
	private String firstName;
	private String lastName;
	private String courseName;
	private String courseDescription;

	public StudentsToCourse(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public StudentsToCourse(String firstName, String lastName, String courseName, String courseDescription) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
	}

	public StudentsToCourse(int enrollmentId, String firstName, String lastName, String courseName,
			String courseDescription) {
		this.enrollmentId = enrollmentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}
}