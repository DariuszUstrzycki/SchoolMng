package com.ustrzycki.ui.sequences;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.persistance.DaoManager;
import com.ustrzycki.ui.ConsoleUI;

public class AddSubject extends AbstractAddSequence {
	
	private Subject subjectToAdd = null;
	
	public AddSubject(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
	}
	
	// Overridden methods ------------------------------------------------------------------------------------
	
	@Override
	protected void matchPromptAction() {

		promptList.add("(Q : Abort the operation and quit to the previous menu.)\n" +
				"Enter the name of the subject:");
		actionsList.add(new RunnableInput(() -> processName(getInputValue()), true));

		// prompt nie jest wyswietlana jesli 2nd argument jest false, a jesli jest true to trzeba
		// czekac na input, czyli reakcje userta

		promptList.add("About to add:");
		actionsList.add(new RunnableInput(() -> displaySubject(), false));

		promptList.add("\n" +
				CONFIRMATION_KEY.toUpperCase() + " : Confirm the operation." + "\n" + 
				PREVIOUS_MENU_KEY.toUpperCase()	+ " : Abort the operation and quit to the previous menu.");
		actionsList.add(new RunnableInput(() -> confirmAdded("The school subject"), true));
	}
	
	@Override
	public void addToDb() {
		DaoManager.getSubjectDAO().insertSubject(subjectToAdd);
	}
	
	// Methods ------------------------------------------------------------------------------------

	private void processName(String subjectName) {

		if (nameAlreadyInDb(subjectName)) {
			throw new IllegalArgumentException(
					"There is already a record with '" + subjectName + "'\n" + "You can't add a duplicate.");
		} else {
			setNameOnSubject(subjectName);
		}
	}

	private boolean nameAlreadyInDb(String subjectName) {

		Subject subjectFromDAO = DaoManager.getSubjectDAO().selectSubjectByName(subjectName);
		return (subjectFromDAO == null) ? false : true;
	}

	private void setNameOnSubject(String subjectName) {

		subjectToAdd = new Subject();
		subjectToAdd.setName(subjectName);
	}

	private void displaySubject() {
		System.out.print(subjectToAdd.toString() + "\n");
	}

	

	

}
