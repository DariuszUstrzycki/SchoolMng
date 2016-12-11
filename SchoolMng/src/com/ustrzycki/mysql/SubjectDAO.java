package com.ustrzycki.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.persistance.DAOException;
import com.ustrzycki.persistance.DAOFactory;
import com.ustrzycki.persistance.DaoManager;
import com.ustrzycki.persistance.DbUtils;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.persistance.SubjectDAOInterface;

public class SubjectDAO implements SubjectDAOInterface {
	
	static final String TABLE = "Subjects";
	
	// Actions ------------------------------------------------------------------------------------

	@Override
	public void insertSubject(Subject subject) {

		if (subject.getId() != null) {
			throw new IllegalArgumentException("Subject is already created, the subject ID is not null.");
		}

		Object[] values = { subject.getName() };

		String SQL_INSERT = "INSERT INTO " + TABLE + " (idSubject, subjectName)" + " VALUES(null, ?)";

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement statement = DbUtils.prepareStatement(connection, SQL_INSERT, true, values);) {

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new DAOException("Creating subject failed, no rows affected.");
			}

			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					subject.setId(rs.getLong(1));
				} else {
					throw new DAOException("Creating subject failed, no generated key obtained.");
				}
			}
		} catch (SQLException e) {
			
			
			if (e.getErrorCode() == 1062) {
				throw new IllegalArgumentException("The subject is already in the database.", e);
			} else {
				throw new DAOException(e);
			}
		}

	}
	
	@Override
	public Subject selectSubjectByID(Long id) throws DAOException {

		String SQL_SELECT_BY_ID = "SELECT idSubject, subjectName FROM " + TABLE + " WHERE idSubject = ?";
		return select(SQL_SELECT_BY_ID, id);
	}

	@Override
	public Subject selectSubjectByName(String name) throws DAOException {

		String SQL_SELECT_BY_NAME = "SELECT idSubject, subjectName FROM " + TABLE + " WHERE subjectName = ?";
		return select(SQL_SELECT_BY_NAME, name);
	}

	/*
	 * /// PRZYK£AD JAK MO¯NA KILKJA VARTOŒCI PODRZUCIÆ DO TEJ SAMEJ METODY
	 * 
	 * @Override public User find(String email, String password) throws
	 * DAOException { return find(SQL_FIND_BY_EMAIL_AND_PASSWORD, email,
	 * password); }
	 */

	/**
	 * Returns the user from the database matching the given SQL query with the
	 * given values.
	 * 
	 * @param sql The SQL query to be executed in the database.
	 * @param values The PreparedStatement values to be set.
	 * @return The user from the database matching the given SQL query with the
	 *         given values. Null if there is no user meeting the criteria.
	 * @throws DAOException If something fails at database level.
	 */
	private Subject select(String sql, Object... values) throws DAOException {

		Subject subject = null;

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement statement = DbUtils.prepareStatement(connection, sql, false, values);
				ResultSet resultSet = statement.executeQuery();) {
			if (resultSet.next()) {
				subject = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return subject;
	}
    
    
	@Override
	public void updateSubject(Subject subject) throws DAOException {

		if (subject.getId() == null) {
			throw new IllegalArgumentException("Subject is not created yet, the subject ID is null.");
		}

		Object[] values = { subject.getName(), subject.getId() };

		String SQL_UPDATE = " UPDATE " + TABLE + 
							" SET subjectName = ? " + 
							" WHERE idSubject = ?";

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement statement = DbUtils.prepareStatement(connection, SQL_UPDATE, false, values);) {

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0)
				throw new DAOException("Updating subject failed, no rows affected.");

		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}
	
    
	@Override
	public void deleteSubject(Subject subject) throws DAOException {

		String SQL_DELETE = "DELETE FROM " + TABLE + " WHERE idSubject = ?";

		Object[] values = { subject.getId() };

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement statement = DbUtils.prepareStatement(connection, SQL_DELETE, false, values);) {

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new DAOException("Deleting subject failed, no rows affected.");
			} else {
				subject.setId(null);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}
	
  //---------------------- DONE
	
	@Override
	public Collection<Subject> selectAllSubjectsTO() throws DAOException {

		Collection<Subject> subjects = new ArrayList<>();

		String SQL_LIST_ORDER_BY_NAME = "SELECT idSubject, subjectName FROM " + TABLE + 
										" ORDER BY subjectName";

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_NAME);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				subjects.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return subjects;
	}
	
	
	
	// Helpers ------------------------------------------------------------------------------------

    /**
     * Map the current row of the given ResultSet to a Subject.
     * @param resultSet The ResultSet of which the current row is to be mapped to a Subject.
     * @return The mapped Subject from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static Subject map(ResultSet resultSet) throws SQLException {
    	
    	Subject subject = new Subject();
    	subject.setId(resultSet.getLong("idSubject"));
    	subject.setName(resultSet.getString("subjectName"));
        return subject;
    }
    
	// END CLASS ------------------------------------------------------------------------------------

	
	/*public static void main(String[] args) {
		
		String DATABASE_URL = "jdbc:mysql://localhost:3306/SchoolMng";
		String DISABLE_SSL = "?autoReconnect=true&useSSL=false";
		String USER = "root";
		String PASS = "javaMySql2016";
		DaoManager daoManager = DaoManager.getInstance(); 
		DAOFactory sqlDAOfactory  = new MySqlDAOFactory(DATABASE_URL + DISABLE_SSL, USER, PASS);
		daoManager.setDAOFactory(sqlDAOfactory);

		SubjectDAO dao = new SubjectDAO();
		Subject sub = new Subject();
		
		//// TESTING INSERT
		sub.setName("Zulu12");
		dao.insertSubject(sub);
		long bioID = sub.getId();
		System.out.println("1.Expected some id number: " + bioID);
		
		Subject sub2 = new Subject();
		sub2.setName("Portugese");
		dao.insertSubject(sub2);
		
		//// TESTING INSERT DUPLICATE
		Subject duplicate = new Subject();
		duplicate.setName("Bio");
		dao.insertSubject(duplicate);
		
		//// TESTING SELECT BY NAME
		System.out.println("2.Expected Bio: " + dao.selectSubjectByName("Bio"));
		System.out.println("3.Expected null: " + dao.selectSubjectByName("XYZ"));
		
		//// TESTING SELECT BY id
		System.out.println("4.Expected Bio: " + dao.selectSubjectByID(bioID));
		System.out.println("5.Expected null: " + dao.selectSubjectByID((long) 11123));
		
		// TESTING UPDATE
		Subject sbjct = new Subject();
		sbjct.setName("Niem");
		dao.insertSubject(sbjct);
		//sbjct = dao.selectSubjectByName("Niem");
		//System.out.println(sbjct);
		//long id = sbjct.getId();
		
		sbjct.setName("Niemiecki");
		dao.updateSubject(sbjct);
		System.out.println("Expected Niemiecki: " + sbjct);
		
		// TESTING DELETE
		Subject sb = dao.selectSubjectByName("Niemiecki");
		System.out.println("Expected Niemiecki: " + sb);
		dao.deleteSubject(sb);
		System.out.println("Expected null: " + dao.selectSubjectByName("Niemiecki"));
		//cleaning: delete Bio to make INSERT from the beginning of the method possible
		Subject sbBio = new Subject();
		sbBio.setId(bioID);
		dao.deleteSubject(sbBio);
		
		// TESTING SELECT ALL SUBJECTS
		Subject s1 = new Subject();
		s1.setName("Russian");
		dao.insertSubject(s1);
		
		Subject s2 = new Subject();
		s2.setName("Physics");
		dao.insertSubject(s2);
		
		System.out.println("Expected to see Russian, Physics and maybe others: " + 
				dao.selectAllSubjectsTO());
		
		// cleaning: delete Physics and Russsian to make INSERT possible
		Subject physics = dao.selectSubjectByName("Physics");
		dao.deleteSubject(physics);
		Subject russian = dao.selectSubjectByName("Russian");
		dao.deleteSubject(russian);
		System.out.println("Expected NOT to see Russian, Physics: " + 
				dao.selectAllSubjectsTO());
		
		
		
		System.out.println("--------------------END TESTING dao------------------------");
		
	}*/



	
	
}
