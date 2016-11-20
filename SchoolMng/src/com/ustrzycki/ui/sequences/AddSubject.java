package com.ustrzycki.ui.sequences;

import java.util.Collection;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.ui.App;
import com.ustrzycki.ui.ConsoleUI;
import com.ustrzycki.ui.Screen.RunnableInput;

public class AddSubject extends AbstractAddSequence {
	
	private Subject subject;
	
	public AddSubject(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		promptList.add("(Q : Abort the operation and quit to the previous menu.)\n"
				+ "Enter the name of the subject:");
		actionsList.add(new RunnableInput(() -> processName(), true));
		
		// prompt nie jest wyswietlana jesli 2nd argument jest false, a jesli jest true to trzeba
		// czekac na input, czyli reakcje userta
		
		promptList.add("About to add:");
		actionsList.add(new RunnableInput(() -> displaySubject(), false));

		promptList.add("\n" +
				CONFIRMATION_KEY.toUpperCase() + " : Confirm the operation." + "\n" + 
				PREVIOUS_MENU_KEY.toUpperCase()	+ " : Abort the operation and quit to the previous menu.");
		actionsList.add(new RunnableInput(() -> confirmAdded("The school subject"), true));
		
		
	}
	

	private final void processName() {

		if(nameIsDuplicate()){
			System.out.println("There is already a record with '" + userInput + "'\n"
					+ "You can't add a duplicate. Try again.\n");
			restartAddingSubject();
		} else {
			setNameOnSubject();
		}
	}
	
	private boolean nameIsDuplicate(){
		
		Collection records = App.DAO_FACTORY.getSubjectDAO().selectSubjectByName(userInput);
		int numberOFDuplicates = records.size();
		
		if(numberOFDuplicates == 0)
			return false;
		else 
			return true;
	}
	
	private void restartAddingSubject(){
		app.setActiveScreen(app.getAddSubjectSequence());
	}
	
	
	private final void setNameOnSubject() {
		subject = new Subject();
		subject.setName(userInput);
	}
	
	private final void displaySubject() {
		System.out.print(subject.toString() + "\n");
	}



	@Override
	public boolean addToDb() {
		
		/*PersonDao personDao = daoFactory.createPersonDao();
		Person person = personDao.readPerson(666);
		...
		personDao.close();*/
		// analogicznie powinno byc: daoFactory.getSubjectDAO().insertSubject(subject);
		
		//return DaoManager.getSubjectDAO().insertSubject(subject); 
		// PO ZMIANIE:
		return App.DAO_FACTORY.getSubjectDAO().insertSubject(subject);
	}

}
