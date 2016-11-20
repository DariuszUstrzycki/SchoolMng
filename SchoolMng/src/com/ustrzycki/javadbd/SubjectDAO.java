package com.ustrzycki.javadbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.persistance.DbUtils;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.persistance.SubjectDAOInterface;

public class SubjectDAO implements SubjectDAOInterface {

	@Override
	public boolean insertSubject(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubject(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Subject selectSubjectByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Subject> selectAllSubjectsTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Subject> selectSubjectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
