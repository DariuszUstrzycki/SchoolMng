package com.ustrzycki.ui.sequences;

import com.ustrzycki.ui.ConsoleUI;
import com.ustrzycki.ui.menus.AbstractMenu;

public abstract class AbstractAddSequence extends AbstractSequence {

	

	public AbstractAddSequence(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		parentSubmenu = (AbstractMenu) app.getAddSubmenu();
	}

	final void confirmAdded(String objectName) {
		if (userInput.equalsIgnoreCase(CONFIRMATION_KEY)) {
			boolean added = addToDb();
			
			
			if(added)
				System.out.println("---- " + objectName + " has been added to the database! ----\n");
			else
				System.out.println(objectName + " NOT added to the database.\n");
				
		} else {
			throw new IllegalArgumentException("Ignoring menu choice! Only Q/q and C/c are valid!");
		}
	}
	
	public abstract boolean addToDb();

}
