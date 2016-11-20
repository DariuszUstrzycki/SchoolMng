package com.ustrzycki.ui.menus;

import com.ustrzycki.ui.ConsoleUI;

public class MainMenu extends AbstractMenu {

	public MainMenu(ConsoleUI app, String menuName, String menuInstruction) {
		super(app, menuName, menuInstruction);
		
		promptList.add("Add to the database");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getAddSubmenu()), false));

		promptList.add("Delete from the database");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getDeleteSubmenu()), false));

		promptList.add("Update the database");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getUpdateSubmenu()), false));

		promptList.add("Quit the programm");
		actionsList.add(new RunnableInput(() -> app.setActiveScreen(app.getQuitSubmenu()), false));
	}

	

}
