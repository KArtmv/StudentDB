package ua.foxminded.javaspring.input;

import java.util.Scanner;

public class InputData {

	public String inputData(Scanner scanner) {
		boolean isTextCorrect = false;

		String input = "";

		while (!isTextCorrect) {
			input = scanner.nextLine();
			if (input == null || input.equals("") || input.matches("\\s+") || input.matches("[^a-zA-Z]+")) {
				System.out.println("Illegal Argument try again");
			} else {
				isTextCorrect = true;
			}
		}
		return input;
	}
}
