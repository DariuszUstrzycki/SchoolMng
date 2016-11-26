package com.ustrzycki.ui.sequences;

import com.ustrzycki.domain.Person;
import com.ustrzycki.domain.Teacher;
import com.ustrzycki.ui.ConsoleUI;

public class AddTeacher extends AbstractAddSequence {
	
	private Teacher.TeacherBuilder teacherBuilder;
	private Teacher teacher;

	public AddTeacher(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
	}
	// Overridden methods ------------------------------------------------------------------------------------
	
	@Override
	protected void matchPromptAction() {
		promptList.add("");
		actionsList.add(new RunnableInput(() -> inputPersonInfo(), false)); 
		
		// -------- start teacher-specific methods -------- //
		promptList.add("Enter graduation information (degree, university):\n"
				+ "(press ENTER if you don't want to set this value now):");
		actionsList.add(new RunnableInput(() -> inputGraduationInfo(), true));
		// -------- end teacher-specific methods ----------//
		
		promptList.add("");
		actionsList.add(new RunnableInput(() -> createTeacher(), false)); 
		
		promptList.add("Do you want to add the teacher? \n" 
				+ CONFIRMATION_KEY.toUpperCase() + " : Confirm the operation." + "\n" 
				+ PREVIOUS_MENU_KEY.toUpperCase() + " : Abort the operation and quit to the previous menu.");
		actionsList.add(new RunnableInput(() -> confirmAdded("Teacher"), true));
		
	}

	@Override
	public void addToDb() {
	
	}
	
	// Methods ------------------------------------------------------------------------------------

	// AddPersonSequence nie powinien miec takiego konstruktora
	private final void inputPersonInfo() {
		System.out.println("Creating Person...");
		AddPerson addPersonSeq = new AddPerson(app, "ADDING A TEACHER", "");
		app.setActiveScreen(addPersonSeq);
		//addPersonSeq.showConsole();
		Person person = addPersonSeq.getPerson(); 
		teacherBuilder = new Teacher.TeacherBuilder(person);
	}
	
	private final void inputGraduationInfo() {
		teacherBuilder.graduationInfo(getInputValue());
	}
	
	private final void createTeacher() {
		teacher = teacherBuilder.build();
		System.out.println(teacher.toString());
	}
	
	


	

	

}
