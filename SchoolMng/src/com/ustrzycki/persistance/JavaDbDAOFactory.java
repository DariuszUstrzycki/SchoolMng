package com.ustrzycki.persistance;


public class JavaDbDAOFactory extends DAOFactory {

	public JavaDbDAOFactory(String databaseUrl, String user, String password){
		super(databaseUrl, user, password);
	}
	
	@Override
	public SubjectDAOInterface getSubjectDAO() {
		return new com.ustrzycki.javadbd.SubjectDAO(); 
	}

	@Override
	public StudentDAOInterface getStudentDAO() {
		return new com.ustrzycki.javadbd.StudentDAO();
	}

	@Override
	public TeacherDAOInterface getTeacherDAO() {
		return new com.ustrzycki.javadbd.TeacherDAO();
	}

	@Override
	public FormDAOInterface getFormDAO() {
		return new com.ustrzycki.javadbd.FormDAO();
	}

}
