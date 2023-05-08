package ua.foxminded.javaspring.model;

public class Student {

	private int studentId;
	private int groupId;
	private String firstName;
	private String lastName;

	public Student(int studentId) {
		this.studentId = studentId;
	}

	public Student(int groupId, String firstName, String lastName) {
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(int studentId, int groupId, String firstName, String lastName) {
		this(groupId, firstName, lastName);
		this.studentId = studentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
