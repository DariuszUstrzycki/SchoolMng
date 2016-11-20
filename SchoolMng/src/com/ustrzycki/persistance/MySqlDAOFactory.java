package com.ustrzycki.persistance;

public class MySqlDAOFactory extends DAOFactory {

	public MySqlDAOFactory(String databaseUrl, String user, String password){
		super(databaseUrl, user, password);
	}

	@Override
	public SubjectDAOInterface getSubjectDAO() {
		return new com.ustrzycki.mysql.SubjectDAO();
	}

	@Override
	public StudentDAOInterface getStudentDAO() {
		return new com.ustrzycki.mysql.StudentDAO();
	}

	@Override
	public TeacherDAOInterface getTeacherDAO() {
		return new com.ustrzycki.mysql.TeacherDAO();
	}

	@Override
	public FormDAOInterface getFormDAO() {
		return new com.ustrzycki.mysql.FormDAO();
	}

}
