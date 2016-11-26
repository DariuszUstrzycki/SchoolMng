package com.ustrzycki.ui.sequences;

import com.ustrzycki.domain.Form;
import com.ustrzycki.ui.ConsoleUI;

public class AddForm extends AbstractAddSequence {
	
	private Form form;
	
	public AddForm(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
	}
	
	// Overridden methods ------------------------------------------------------------------------------------
	
	
	@Override
	protected void matchPromptAction() {
		promptList.add("Enter the name of the form (any spaces will be removed):");
		actionsList.add(new RunnableInput(() -> inputName(), true));
		
		/*promptList.add("Enter the first name and last name of the tutor:  "
				+ "\nor press ENTER if you don't want to do it now.");
		actionsList.add(new RunnableTakeInput(() -> inputTutor(), true)); */
		
		promptList.add("");
		actionsList.add(new RunnableInput(() -> displayForm(), false));

		promptList.add("Do you want to add the form? \n" + CONFIRMATION_KEY.toUpperCase() +
				       " : Confirm the operation." + "\n" + PREVIOUS_MENU_KEY.toUpperCase() + 
				       " : Abort the operation and quit to the previous menu.");
		actionsList.add(new RunnableInput(() -> confirmAdded("Form"), true));
		//TODO: inputing tutor is not included in the prompts/actions
		
	}
	
	@Override
	public void addToDb() {
		
	}
	
	// Methods ------------------------------------------------------------------------------------

	
	private final void inputName() {
		form = new Form();
		form.setName(getInputValue());
	}
	
	/*private void inputTutor() {
		form.setTutor(Teacher teacher); //TODO: implement
	}*/
	
	private final void displayForm() {
			System.out.println(form.toString());
	}



	



	

}
