package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.input.InputDataOfStudent;
import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.output.ShowListOfGroups;

public class AddStudent {

	private final StudentDAO studentDAO;
	private final ShowListOfGroups listOfGroups;
	private final Scanner scanner;

	public AddStudent(StudentDAO studentDAO, ShowListOfGroups listOfGroups, Scanner scanner) {
		this.studentDAO = studentDAO;
		this.listOfGroups = listOfGroups;
		this.scanner = scanner;
	}

	public void addStudent() throws SQLException {
		InputDataOfStudent dataOfstudent = new InputDataOfStudent(listOfGroups, scanner);
		Student student = dataOfstudent.inputDataOfStudent();

		boolean added = studentDAO.insertStudent(student);

		if (added) {
			System.out.println("Student have been added to database");
		}
	}
}
