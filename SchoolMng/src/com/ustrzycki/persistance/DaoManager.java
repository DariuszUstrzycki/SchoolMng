package com.ustrzycki.persistance;

public class DaoManager {
	
	private static DaoManager instance = null;
	private DAOFactory chosenFactory = null;
	
	private static SubjectDAOInterface subjectDAO = null;
	private static TeacherDAOInterface teacherDAO = null;
	private static StudentDAOInterface studentDAO = null;
	private static FormDAOInterface formDAO = null;
	
	private DaoManager(){}
	
	public static DaoManager getInstance() {
	      if(instance == null) {
	         instance = new DaoManager();
	      }
	      return instance;
	   }
	
	public void setDAOFactory(DAOFactory type){
		if(chosenFactory == null){
			chosenFactory = type;
			
			subjectDAO = chosenFactory.getSubjectDAO();
			teacherDAO = chosenFactory.getTeacherDAO();
			studentDAO = chosenFactory.getStudentDAO();
			formDAO = chosenFactory.getFormDAO();
		}
	}
	
	public static SubjectDAOInterface getSubjectDAO() {
		return subjectDAO;
	}
	public static TeacherDAOInterface getTeacherDAO() {
		return teacherDAO;
	}
	public static StudentDAOInterface getStudentDAO() {
		return studentDAO;
	}
	public static FormDAOInterface getFormDAO() {
		return formDAO;
	}
	
	
	  
	   

}
