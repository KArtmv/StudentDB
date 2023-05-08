package ua.foxminded.javaspring.output;

import java.sql.SQLException;
import java.util.List;

import ua.foxminded.javaspring.dao.GroupDAO;
import ua.foxminded.javaspring.model.Group;

public class ShowListOfGroups {

	private final GroupDAO groupDAO;

	public ShowListOfGroups(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void showListOfGroups() throws SQLException {
		List<Group> groups = groupDAO.listOfGroups();

		for (Group group : groups) {
			int groupId = group.getGroupId();
			String groupName = group.getGroupName();
			System.out.println(groupId + ". Group name- " + groupName);
		}
	}
}
