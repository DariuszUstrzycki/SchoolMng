

package com.ustrzycki.ui;

import com.ustrzycki.persistance.DAOFactory;
import com.ustrzycki.persistance.DaoManager;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.ui.menus.UserInterface;


/**
 * This is a non-web application with a command-line UI, which helps to manage a school. 
 * In its first phase the user should be able to add, delete, update and find a teacher, a student, a school subject and a form(class). 
 * This phase is not finished yet.
 * 
 * See the attached readme.txt for preconditions.
 * 
 * @author Dariusz Ustrzycki
 *
 */


public class App {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SchoolMng";
	private static final String DISABLE_SSL = "?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "javaMySql2016";

	public static void main(String[] args) {
			
        //get DAO manager
		DaoManager daoManager = DaoManager.getInstance(); 
		
		//get a DAOFactory and set it on the DAOManager
		DAOFactory sqlDAOfactory  = new MySqlDAOFactory(DATABASE_URL + DISABLE_SSL, USER, PASS);
		daoManager.setDAOFactory(sqlDAOfactory);
		
		//get UI and start it
		UserInterface userInterface = new ConsoleUI();
		userInterface.showUI();
		
	}
	
}
