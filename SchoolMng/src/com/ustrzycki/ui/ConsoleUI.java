package com.ustrzycki.ui;

import java.util.Scanner;

import com.ustrzycki.ui.menus.AddSubmenu;
import com.ustrzycki.ui.menus.DeleteSubmenu;
import com.ustrzycki.ui.menus.MainMenu;
import com.ustrzycki.ui.menus.QuitSubmenu;
import com.ustrzycki.ui.menus.UpdateSubmenu;
import com.ustrzycki.ui.menus.UserInterface;
import com.ustrzycki.ui.sequences.AddForm;
import com.ustrzycki.ui.sequences.AddStudent;
import com.ustrzycki.ui.sequences.AddSubject;
import com.ustrzycki.ui.sequences.AddTeacher;
import com.ustrzycki.ui.sequences.ZZZ_UnavailableSequence;

public class ConsoleUI implements UserInterface, MenuConstants{
	
	
	//private static final DaoManager daoManager = DaoManager.getInstance();
	
	private final Scanner scanner = new Scanner(System.in); //kazde menu pobiera z aplikacja tego Scanner
	                           // deklaracj scanner musi byc pierwsza bo inaczej bedzie null w menus
	private Screen activeScreen; 
			
	private Screen mainSubmenu = new MainMenu(this, "MAIN MENU.", "Choose an option. "
			+ "\n(Each choice must be confirmed by ENTER)");
	//submenus
	private Screen deleteSubmenu = new DeleteSubmenu(this, "DELETE SUBMENU.", "Choose item to delete:");
	private Screen quitSubmenu = new QuitSubmenu(this, "", "-----Thank you for using the program!-----");
	private Screen addSubmenu = new AddSubmenu(this, "ADD SUBMENU." , "Choose item to add");
	private Screen updateSubmenu = new UpdateSubmenu(this, "UPDATE SUBMENU.", "Choose item to update");
	
	// sequence screens - take data input one by one form the user
	private Screen addTeacherSequence= new AddTeacher(this, "ADDING A TEACHER", "no instruction yet");
	private Screen addStudentSequence= new AddStudent(this,  "ADDING A STUDENT", "no instruction yet");
	private Screen addSubjectSequence= new AddSubject(this, "ADDING A SUBJECT", "no instruction yet");
	private Screen addFormSequence= new AddForm(this,  "ADDING A FORM", "no instruction yet");
	
	private Screen zzz_UnavailableSequence= new ZZZ_UnavailableSequence(this, "TEMPORARY SEQUENCE SCREEN", "no instruction yet");
	//TODO remove when done
		
	public ConsoleUI (){             //(Factory persistanceType) {
		
		//daoManager.setDAOFactory(persistanceType);
		activeScreen = mainSubmenu;
		//showUI();
	}
	
	public void setActiveScreen(Screen newScreen){
		this.activeScreen = newScreen;
		showUI();
	}
	
	@Override
	public void showUI(){
		this.activeScreen.showConsole();
	}
	
	public void closeApp() {
		if (scanner != null)
			scanner.close();

		System.exit(0);
	}
	
	public Scanner getScanner(){
		return scanner;
	} // can't remove the method - scanner is used by Screen
	
	
	// ----------main menu and menu getters below---------------	
	public Screen getActiveScreen() {
		return activeScreen;
	}
	//main menu getter
	public Screen getMainMenu() {
		return mainSubmenu;
	}
	//submenu getters
	public Screen getDeleteSubmenu() {
		return deleteSubmenu;
	}

	public Screen getQuitSubmenu() {
		return quitSubmenu;
	}

	public Screen getAddSubmenu() {
		return addSubmenu;
	}

	public Screen getUpdateSubmenu() {
		return updateSubmenu;
	}
	//sequence screen getters
	public Screen getAddTeacherSequence() {
		return addTeacherSequence;
	}

	public Screen getAddStudentSequence() {
		return addStudentSequence;
	}

	public Screen getAddSubjectSequence() {
		return addSubjectSequence;
	}

	public Screen getAddFormSequence() {
		return addFormSequence;
	}

	//TODO remove when done
	public Screen getZzz_UnavailableSequence() {
		return zzz_UnavailableSequence;
	}

}
