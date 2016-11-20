package com.ustrzycki.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public abstract class DAOFactory {

	private static Connection connection;
	private static String databaseUrl;
	private static String user;
	private static String password;

	public DAOFactory(String databaseUrl, String user, String password) {
		DAOFactory.databaseUrl = databaseUrl;
		DAOFactory.user = user;
		DAOFactory.password = password;
	}

	public static Connection createConnection() {

		try {
			connection = DriverManager.getConnection(databaseUrl, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	// available DAOs
	public abstract SubjectDAOInterface getSubjectDAO();

	public abstract StudentDAOInterface getStudentDAO();

	public abstract TeacherDAOInterface getTeacherDAO();

	public abstract FormDAOInterface getFormDAO();
}
