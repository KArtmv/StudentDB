package ua.foxminded.javaspring;

import java.sql.Connection;
import java.util.Scanner;

import ua.foxminded.javaspring.connection.ConnectionProvider;
import ua.foxminded.javaspring.dao.CourseDAO;
import ua.foxminded.javaspring.dao.GroupDAO;
import ua.foxminded.javaspring.dao.StudentDAO;
import ua.foxminded.javaspring.option.AddStudent;
import ua.foxminded.javaspring.option.AddStudentToCourse;
import ua.foxminded.javaspring.option.DeleteStudent;
import ua.foxminded.javaspring.option.DeleteStudentFromCourse;
import ua.foxminded.javaspring.option.FindAllStudentsAtCourse;
import ua.foxminded.javaspring.option.FindGroupsByStudentCount;
import ua.foxminded.javaspring.output.ShowListOfCourses;
import ua.foxminded.javaspring.output.ShowListOfGroups;

public class Start {

	private final ConnectionProvider connectionProvider;

	public Start(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	StartMenu startMenu = new StartMenu();

	public void start() {
		StudentDAO studentDAO = new StudentDAO(connectionProvider);
		GroupDAO groupDAO = new GroupDAO(connectionProvider);
		CourseDAO courseDAO = new CourseDAO(connectionProvider);

		ShowListOfCourses listOfCourses = new ShowListOfCourses(courseDAO);
		ShowListOfGroups listOfGroups = new ShowListOfGroups(groupDAO);
		Scanner scanner = new Scanner(System.in);

		FindGroupsByStudentCount findGroups = new FindGroupsByStudentCount(groupDAO, scanner);
		FindAllStudentsAtCourse allStudentsAtCourse = new FindAllStudentsAtCourse(courseDAO, listOfCourses, scanner);
		AddStudent addStudent = new AddStudent(studentDAO, listOfGroups, scanner);
		DeleteStudent deleteStudent = new DeleteStudent(studentDAO, courseDAO, scanner);
		AddStudentToCourse studentToCourse = new AddStudentToCourse(studentDAO, courseDAO, listOfCourses, scanner);
		DeleteStudentFromCourse deleteStudentFromCourse = new DeleteStudentFromCourse(studentDAO, courseDAO, scanner);

		boolean exit = false;

		SwitchOfMenu switchOfMenu = new SwitchOfMenu(scanner, findGroups, addStudent, deleteStudent,
				allStudentsAtCourse, deleteStudentFromCourse, exit, startMenu, studentToCourse);

		startMenu.startMenu();
		System.out.println("Enter number of option you want");
		while (!exit) {
			exit = switchOfMenu.switchOfMenu();
		}
		scanner.close();
	}
}
