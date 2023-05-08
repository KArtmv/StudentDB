package ua.foxminded.javaspring.option;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import ua.foxminded.javaspring.dao.GroupDAO;
import ua.foxminded.javaspring.input.InputNumbers;
import ua.foxminded.javaspring.model.Group;

public class FindGroupsByStudentCount {
	private final GroupDAO groupDAO;
	private final Scanner scanner;

	public FindGroupsByStudentCount(GroupDAO groupDAO, Scanner scanner) {
		this.groupDAO = groupDAO;
		this.scanner = scanner;
	}

	public void findGroupsByStudentCount() throws SQLException {
		System.out.println("Enter count students at group you want find");

		InputNumbers inputNumbers = new InputNumbers();
		int inputCount = inputNumbers.inputNumbers(scanner);

		Map<Integer, Group> groups = groupDAO.counterStudentsAtGroups(inputCount);
		for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
			Integer count = entry.getKey();
			Group val = entry.getValue();

			System.out.println(count + " are students at group: " + val.getGroupName());
		}
	}
}
