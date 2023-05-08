package ua.foxminded.javaspring.input;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.model.Student;

public class InputStudentId {

	private final StudentDAO studentDAO;

	public InputStudentId(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	Student studentId;

	public Student inputStudentId(Scanner scanner) throws SQLException {

		boolean exist = false;

		while (!exist) {
			System.out.println("Enter the id of the student");
			InputNumbers inputNumbers = new InputNumbers();
			studentId = new Student(inputNumbers.inputNumbers(scanner));

			if (!studentDAO.isValidStudentId(studentId)) {
				System.out.println("Invalid student ID");
			} else {
				exist = true;
			}
		}
		return studentId;
	}
}
