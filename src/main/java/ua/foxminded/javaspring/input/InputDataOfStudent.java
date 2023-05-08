package ua.foxminded.javaspring.input;

import java.sql.SQLException;
import java.util.Scanner;

import ua.foxminded.javaspring.model.Student;
import ua.foxminded.javaspring.output.ShowListOfGroups;

public class InputDataOfStudent {

	private final ShowListOfGroups listOfGroups;
	private final Scanner scanner;

	public InputDataOfStudent(ShowListOfGroups listOfGroups, Scanner scanner) {
		this.listOfGroups = listOfGroups;
		this.scanner = scanner;
	}

	public Student inputDataOfStudent() throws SQLException {

		InputData inputData = new InputData();
		InputNumbers numbers = new InputNumbers();

		System.out.println("Enter first name of student");
		String firstName = inputData.inputData(scanner);

		System.out.println("Enter last name of student");
		String lastName = inputData.inputData(scanner);

		listOfGroups.showListOfGroups();

		System.out.println("Enter the group number for the student from the list");
		int groupId = numbers.inputNumbers(scanner);

		return new Student(groupId, firstName, lastName);
	}
}
