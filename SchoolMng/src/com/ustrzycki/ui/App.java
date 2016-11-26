package com.ustrzycki.ui;

import com.ustrzycki.persistance.DAOFactory;
import com.ustrzycki.persistance.DaoManager;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.ui.menus.UserInterface;

public class App {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SchoolMng";
	private static final String DISABLE_SSL = "?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "javaMySql2016";

	public static void main(String[] args) {
			
        //obtain DAO manager
		DaoManager daoManager = DaoManager.getInstance(); 
		
		//obtain a DAOFactory and set it on the DAOManager
		DAOFactory sqlDAOfactory  = new MySqlDAOFactory(DATABASE_URL + DISABLE_SSL, USER, PASS);
		daoManager.setDAOFactory(sqlDAOfactory);
		
		//obtain UI and start it
		UserInterface userInterface = new ConsoleUI();
		userInterface.showUI();
		
	}
	
}
