package com.ustrzycki.persistance;

import java.util.Collection;

import javax.sql.RowSet;

import com.ustrzycki.domain.Subject;

public interface SubjectDAOInterface {
	
	// Interface that all SubjectDAOs must support
	
	  public void insertSubject(Subject subject); 
	  public void deleteSubject(Subject subject);
	  public Subject selectSubjectByID(Long id);
	  public Subject selectSubjectByName(String name); 
	  public void updateSubject(Subject subject);
	  public Collection<Subject> selectAllSubjectsTO();
	 
	  
	  
	  /*
	  public RowSet selectAllSubjectsRS();*/
	  
	  
	  
}
 