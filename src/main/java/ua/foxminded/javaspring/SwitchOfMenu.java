package ua.foxminded.javaspring;

import java.util.Scanner;

import ua.foxminded.javaspring.option.AddStudent;
import ua.foxminded.javaspring.option.AddStudentToCourse;
import ua.foxminded.javaspring.option.DeleteStudent;
import ua.foxminded.javaspring.option.DeleteStudentFromCourse;
import ua.foxminded.javaspring.option.FindAllStudentsAtCourse;
import ua.foxminded.javaspring.option.FindGroupsByStudentCount;

public class SwitchOfMenu {

	private final Scanner scanner;
	private final FindGroupsByStudentCount findGroups;
	private final AddStudent addStudent;
	private final DeleteStudent deleteStudent;
	private final FindAllStudentsAtCourse allStudentsAtCourse;
	private final DeleteStudentFromCourse deleteStudentFromCourse;
	private boolean exit;
	private final StartMenu startMenu;
	private final AddStudentToCourse studentToCourse;

	public SwitchOfMenu(Scanner scanner, FindGroupsByStudentCount findGroups, AddStudent addStudent,
			DeleteStudent deleteStudent, FindAllStudentsAtCourse allStudentsAtCourse,
			DeleteStudentFromCourse deleteStudentFromCourse, boolean exit, StartMenu startMenu,
			AddStudentToCourse studentToCourse) {
		this.scanner = scanner;
		this.findGroups = findGroups;
		this.addStudent = addStudent;
		this.deleteStudent = deleteStudent;
		this.allStudentsAtCourse = allStudentsAtCourse;
		this.deleteStudentFromCourse = deleteStudentFromCourse;
		this.exit = exit;
		this.startMenu = startMenu;
		this.studentToCourse = studentToCourse;
	}

	public boolean switchOfMenu() {

		try {
			String input = scanner.nextLine();
			switch (input) {
			case "1": {
				findGroups.findGroupsByStudentCount();
				break;
			}
			case "2": {
				allStudentsAtCourse.findAllStudentsAtCourse();
				break;
			}
			case "3": {
				addStudent.addStudent();
				break;
			}
			case "4": {
				deleteStudent.deleteStudent();
				break;
			}
			case "5": {
				studentToCourse.addStudentToCourse();
				break;
			}
			case "6": {
				deleteStudentFromCourse.deleteStudentFromCourse();
				break;
			}
			case "x": {
				exit = true;
				break;
			}
			default:
				startMenu.startMenu();
				System.out.println("Enter number of option you want");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exit;
	}
}
