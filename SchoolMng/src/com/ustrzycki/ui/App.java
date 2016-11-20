package com.ustrzycki.ui;

import com.ustrzycki.persistance.DAOFactory;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.ui.menus.UserInterface;

public class App {

	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SchoolMng";
	public static final String DISABLE_SLL = "?autoReconnect=true&useSSL=false";
	public static final String USER = "root";
	public static final String PASS = "jhtp_Java8";
	public static final DAOFactory DAO_FACTORY = new MySqlDAOFactory(DATABASE_URL + DISABLE_SLL, USER, PASS);
	private static UserInterface userInterface = new ConsoleUI();

	public static void main(String[] args) {

		userInterface.showUI();

	}

}
