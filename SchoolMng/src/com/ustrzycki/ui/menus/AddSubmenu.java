package com.ustrzycki.ui.menus;

import com.ustrzycki.ui.ConsoleUI;

public class AddSubmenu extends AbstractMenu {

	public AddSubmenu(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		promptList.add("Add a Teacher");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getAddTeacherSequence()), true));

		promptList.add("Add a Student");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getAddStudentSequence()), true));

		promptList.add("Add a Form");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getAddFormSequence()), true));

		promptList.add("Add a Subject");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getAddSubjectSequence()), true));

		promptList.add("Return to the Main Menu");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getMainMenu()), false));

		promptList.add("Quit the programm");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getQuitSubmenu()), false));

	}

	

}
