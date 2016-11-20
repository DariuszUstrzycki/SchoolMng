package com.ustrzycki.ui.menus;

import com.ustrzycki.ui.ConsoleUI;

public class UpdateSubmenu extends AbstractMenu {

	public UpdateSubmenu(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		promptList.add("Update a Teacher");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getZzz_UnavailableSequence()), false));

		promptList.add("Update a Student");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getZzz_UnavailableSequence()), false));

		promptList.add("Update a Form");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getZzz_UnavailableSequence()), false));

		promptList.add("Update a Subject");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getZzz_UnavailableSequence()), false));

		promptList.add("Return to the Main Menu");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getMainMenu()), false));

		promptList.add("Quit the programm");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getQuitSubmenu()), false));
	}

	
}
