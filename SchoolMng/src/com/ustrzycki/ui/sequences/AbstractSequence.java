package com.ustrzycki.ui.sequences;

import java.util.NoSuchElementException;

import com.ustrzycki.ui.ConsoleUI;
import com.ustrzycki.ui.Screen;
import com.ustrzycki.ui.menus.AbstractMenu;


public abstract class AbstractSequence extends Screen{
	
	private String inputValue;
	AbstractMenu parentSubmenu;
	
	public AbstractSequence(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		matchPromptAction();
	}
	
	protected abstract void matchPromptAction();
	
	
	public void showConsole(){
		for (int c = 0; c < promptList.size(); c++) {
			displayPanel(promptList.get(c), actionsList.get(c));
		}
	}
	
	protected void displayPanel(String prompt, RunnableInput method) {

		boolean repeatLoop = false;
		
		//System.out.println("--------METHO0D ENTRANCE-------------...");
		
		System.out.printf("\n" + generateText());

		do {
			
			//System.out.println("       ^^^ LOOP ENTRANCE^^^");
			
			
			if (method.isForInput()) {
				//System.out.println("tekstowy IF");
				
				repeatLoop = false;
			}
			
			System.out.println("\n" + prompt);

			try {

				if (method.isForInput()) {
							//System.out.println("Ready for input...");
					inputValue = readUserInput();
					if (inputValue.equalsIgnoreCase(PREVIOUS_MENU_KEY)) {
							//System.out.println("Check on PREVIOUS_MENU_KEY");
						activateParentMenu();
					}
				}
				
				//System.out.println("Runnable called...");
				
					method.getRunnable().run();
				
				

			} catch (NoSuchElementException | IllegalArgumentException e) {
				System.out.println(e.getMessage() + "\nPlease Try Again.");
				repeatLoop = true;
			} catch (IllegalStateException e) {
				System.out.println(FATAL_ERROR_MESSAGE);
				System.err.println("Possible problem with the scanner! "
									+ " Scanner is null: " + (scanner == null));
				e.printStackTrace();
				System.exit(1);
			} catch (Exception e) {
				System.out.println(FATAL_ERROR_MESSAGE);
				System.err.println(">>>>>>>>>>>>>>>>>>>>>\nException when taking input.\nPROMPT: \n''" + prompt 
									+ "''\nUSER INPUTt:\n''" + inputValue + "''\n<<<<<<<<<<<<<<<<<<<<<\n");
				e.printStackTrace();
				System.exit(1);
			}
			
			//System.out.println("End of LOOP...");

		} while (repeatLoop);

	}
	
	public void activateParentMenu(){
		app.setActiveScreen(parentSubmenu);
	}
	
	public String readUserInput() {
		return scanner.nextLine();
	}

	public String generateText() {
		return menuName;
	}
	
	public String getInputValue(){
		return inputValue;
	}
	

}
