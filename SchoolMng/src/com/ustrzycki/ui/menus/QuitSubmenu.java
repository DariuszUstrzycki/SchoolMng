package com.ustrzycki.ui.menus;

import com.ustrzycki.ui.ConsoleUI;

public class QuitSubmenu extends AbstractMenu {

	public QuitSubmenu(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		promptList.add("----------USTRZYCKI SOFTWARE CO.----------");
		//actionsList.add(new RunnableInput(() -> System.out.println("Do the closing"), false));
		actionsList.add(new RunnableInput(() -> app.closeApp(), false));
	}
	
		
	
	 public final void showConsole(){
		System.out.println(generateText());
		app.closeApp();
	}

	public final String generateText() {

		StringBuilder sb = new StringBuilder();
		sb.append(menuInstruction).append("\n");

		for (int i = 0; i < promptList.size(); i++) {
			sb.append(String.format("%s%n", promptList.get(i)));
		}

		return sb.toString();

	}
}
