package ua.foxminded.javaspring.input;

import java.util.Scanner;

public class InputNumbers {

	public int inputNumbers(Scanner scanner) {
		boolean isInputCorrect = false;

		String input = "";

		while (!isInputCorrect) {
			input = scanner.nextLine();
			if (input == null || input.equals("") || input.matches("\\s+") || input.matches("\\D+")) {
				System.out.println("Illegal Argument try again");
			} else {
				isInputCorrect = true;
			}
		}
		return Integer.valueOf(input);
	}
}
