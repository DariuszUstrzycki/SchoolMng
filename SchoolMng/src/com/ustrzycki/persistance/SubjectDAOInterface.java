package com.ustrzycki.persistance;

import java.util.Collection;

import javax.sql.RowSet;

import com.ustrzycki.domain.Subject;

public interface SubjectDAOInterface {
	
	// Interface that all SubjectDAOs must support
	
	  public boolean insertSubject(Subject subject); 
	  public boolean deleteSubject(Subject subject);
	  public Subject selectSubjectByID(long id);
	  public boolean updateSubject(Subject subject);
	  public Collection<Subject> selectAllSubjectsTO();
	  public Collection<Subject> selectSubjectByName(String name);
	 
	  
	  ////////// pozosta³e metody do dodania póxniej
	  
	  /*
	 
	  
	 
	  public RowSet selectAllSubjectsRS();*/
	  
	  
	  
}
 