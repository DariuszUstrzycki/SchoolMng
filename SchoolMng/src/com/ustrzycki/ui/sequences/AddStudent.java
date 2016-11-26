package com.ustrzycki.ui.sequences;

import com.ustrzycki.domain.Person;
import com.ustrzycki.domain.Student;
import com.ustrzycki.ui.ConsoleUI;

public class AddStudent extends AbstractAddSequence {

	private Student.StudentBuilder studentBuilder;
	private Student student;

	public AddStudent(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		
	}
	// Overridden methods ------------------------------------------------------------------------------------
	
	@Override
	protected void matchPromptAction() {
		promptList.add("");
		actionsList.add(new RunnableInput(() -> inputPersonInfo(), false));

		// -------- start student-specific methods -------- //
		promptList.add("Does the student have a scholarship? Press Y for YES, N for NO "
				+ "\n(press ENTER if you don't want to set this value now):");
		actionsList.add(new RunnableInput(() -> inputScholarship(), true));
		// -------- end student-specific methods ----------//

		promptList.add("");
		actionsList.add(new RunnableInput(() -> createStudent(), false));

		promptList.add("Do you want to add the student? \n" + CONFIRMATION_KEY.toUpperCase()
				+ " : Confirm the operation." + "\n" + PREVIOUS_MENU_KEY.toUpperCase()
				+ " : Abort the operation and quit to the previous menu.");
		actionsList.add(new RunnableInput(() -> confirmAdded("Student"), true));
		
	}

	@Override
	public void addToDb() {
	
	}

	// Methods ------------------------------------------------------------------------------------

	private final void inputPersonInfo() {

		AddPerson addPersonSeq = new AddPerson(app, "", "");
		app.setActiveScreen(addPersonSeq);
		Person person = addPersonSeq.getPerson();
		studentBuilder = new Student.StudentBuilder(person);
	}

	private final void inputScholarship() {

		// optional parameter
		if (!"".equals(getInputValue())) {
			if (getInputValue().equalsIgnoreCase("Y"))
				studentBuilder.scholarship(true);
			else if (getInputValue().equalsIgnoreCase("N"))
				studentBuilder.scholarship(false);
			else
				throw new IllegalArgumentException("Ignoring menu choice! Only Y/y or N/n is valid!");
		}
	}

	private final void createStudent() {
		student = studentBuilder.build();
		System.out.println(student.toString());

	}


}
