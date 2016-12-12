package com.ustrzycki.ui.menus;

import java.util.NoSuchElementException;

import com.ustrzycki.ui.ConsoleUI;
import com.ustrzycki.ui.Screen;

public abstract class AbstractMenu extends Screen {

	public AbstractMenu(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
	}
	
	private final void executeAction(int actionNumber) {

		int actualIndex = actionNumber - 1;
		if (actualIndex < 0 || actualIndex >= actionsList.size()) {
			System.out.println("Enter a corect menu option!");
		} else {
			(actionsList.get(actualIndex).getRunnable()).run();
		}
	}

	public String generateText() {

		StringBuilder sb = new StringBuilder();
		sb.append(menuName).append(" ");
		sb.append(menuInstruction).append(":\n");

		for (int i = 0; i < promptList.size(); i++) {
			sb.append(String.format(" %d: %s%n", i + 1, promptList.get(i)));
		}

		return sb.toString();

	}
	
	public void showConsole() {
		
		 boolean activeLoop = true;

			while (activeLoop) {
				
				System.out.println(generateText());
				int actionNumber = -1;
				
				try {
					actionNumber = scanner.nextInt();
					scanner.nextLine(); // clear scanner
					executeAction(actionNumber);

				} catch (NoSuchElementException e) {
					System.out.println("Enter a corect menu option!");
					scanner.nextLine();
				} 

			}
			
			activeLoop = true; // restores looping for the main menu
	
		}

}
