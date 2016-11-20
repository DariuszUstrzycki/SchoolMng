package com.ustrzycki.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.persistance.MySqlDAOFactory;
import com.ustrzycki.persistance.SubjectDAOInterface;

public class SubjectDAO implements SubjectDAOInterface {
	
	static final String TABLE = "Subjects";
	
	public SubjectDAO() {
		//	connection = MySqlDAOFactory.createConnection();
	}
		
	
	@Override
	public boolean insertSubject(Subject subject) {

		/*
		 * CZEK NA DECYZJE OD PIOTRKA //
		 * MySQLIntegrityConstraintViolationException: Duplicate entry 'Physics'
		 * for key 'subjectName' catch (com.mysql.jdbc.exceptions.jdbc4.
		 * MySQLIntegrityConstraintViolationException e){ new
		 * com.mysql.jdbc.exceptions.jdbc4.
		 * MySQLIntegrityConstraintViolationException(); }
		 */

		int rowsAffected = 0;
		String sql = "INSERT INTO " + TABLE + " (subjectName)" + 
					 " VALUES(?)";

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement insertStatement = connection.prepareStatement(sql);) {

			insertStatement.setString(1, subject.getName());
			rowsAffected = insertStatement.executeUpdate();

		} catch (SQLException sqlException) {
			// System.out.println("Error: " + sqlException.getErrorCode());
			sqlException.printStackTrace();
			//DbUtils.printSQLException(sqlException);
		}

		assert (rowsAffected <= 1); // TODO zostawic?

		return rowsAffected > 0 ? true : false;
	}

	@Override
	public Subject selectSubjectByID(long id) {
		
		String sql = "SELECT * FROM " + TABLE +
					 " WHERE idSubject = ?";
		Subject subject = null;
		
		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement selectByID = (connection.prepareStatement(sql));) {

			selectByID.setLong(1, id);
			
			try(ResultSet resultSet = selectByID.executeQuery();){
				
				// if  record found
					if (resultSet.isBeforeFirst()) {
						resultSet.next();					
						subject = new Subject();
						subject.setId((resultSet.getLong("idSubject")));
						subject.setName(resultSet.getString("subjectName"));
					}
				//}
				       
				
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return subject;
	}
	
	
	
	@Override
	public Collection<Subject> selectAllSubjectsTO() {
		
		String sql = "SELECT * FROM " + TABLE;
		Collection<Subject> collection = new ArrayList();
		
		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement selectAll = (connection.prepareStatement(sql));
				ResultSet resultSet = selectAll.executeQuery();) {

				while(resultSet.next()){
					
					Subject subject = new Subject();
					subject.setId((resultSet.getLong("idSubject")));
					subject.setName(resultSet.getString("subjectName"));
					
					collection.add(subject);
				}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return collection;
	}
	
	
	
	
	@Override
	public boolean deleteSubject(Subject subject) {

		int rowsAffected = 0;
		String sql = "DELETE FROM " + TABLE + 
								" WHERE subjectName = ?" +
								 "ORDER BY subjectName";

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement deleteStatement = connection.prepareStatement(sql);) {

			deleteStatement.setString(1, subject.getName());
			rowsAffected = deleteStatement.executeUpdate();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		assert (rowsAffected <= 1); // TODO zostawic?

		return rowsAffected > 0 ? true : false;
	}
	
	
	@Override
	public Collection<Subject> selectSubjectByName(String name) {
		
		String sql = "SELECT * FROM " + TABLE + 
					" WHERE subjectName LIKE ?" +
					" ORDER BY subjectName";
		
		Collection<Subject> collection = new ArrayList();
		
		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement selectByName = (connection.prepareStatement(sql));) {
			
			selectByName.setString(1,  "%" + name + "%");
			
			try(ResultSet resultSet = selectByName.executeQuery();){
				
				while (resultSet.next()) {

					Subject subject = new Subject();
					subject.setId((resultSet.getLong("idSubject")));
					subject.setName(resultSet.getString("subjectName"));
					collection.add(subject);
				}
				       
				
			}catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		
		return collection;
	}
	
	public boolean updateSubject(Subject subject) {
		
		String sql = 
					" UPDATE " + TABLE + 
					" SET subjectName = ? " + 
					" WHERE idSubject = ?";
		
		//ResultSet resultSet ;
		int rowsCount ;
	

		try (Connection connection = MySqlDAOFactory.createConnection();
				PreparedStatement updateStatement = connection.prepareStatement(sql);) {
			
			updateStatement.setString(1, subject.getName());
			updateStatement.setLong(2, subject.getId());
			
			
			rowsCount = updateStatement.executeUpdate();
			System.out.println("rows affected: " + rowsCount);
			

		} catch (SQLException sqlException) {
			// System.out.println("Error: " + sqlException.getErrorCode());
			sqlException.printStackTrace();
			//DbUtils.printSQLException(sqlException);
		}
		
		
		//connection.setAutoCommit(false); // albo daj do kazdej metody osobno
		
		/*// przepisalem to co by³o dla insert method
		// uzyc rollback i commit?!
		int rowCount = 0;

		try {

			insertNewSubject.setString(1, subject.getName());
			rowCount = insertNewSubject.executeUpdate();
			// System.out.println(rowCount);

			// commit after execute()
		    connection.commit();

		} catch (SQLException sqlException) {
			// System.out.println("Error: " + sqlException.getErrorCode());
			sqlException.printStackTrace();
			DbUtils.printSQLException(sqlException);

			// If there is an error then rollback the changes.
			try {
				if (connection != null)
					connection.rollback();
				
				 System.err.print("Transaction is being rolled back");
				 
			} catch (SQLException sqlException2) {
				sqlException2.printStackTrace();
			}
		} finally {
			
			// You avoid holding database locks for multiple statements, 
			// which increases the likelihood of conflicts with other users.
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			DbUtils.closeQuietly(insertNewSubject);
			DbUtils.closeQuietly(connection);
			
			
		}

		*/
		
		return false;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
			
		SubjectDAO dao = new SubjectDAO();
		Subject sub = new Subject();
		/*sub.setName("Biology");
		dao.insertSubject(sub);*/
		
		/*sub.setName("Maths");
		dao.insertSubject(sub);*/
		
		/*sub.setName("Physics");
		dao.insertSubject(sub);*/
		
		/*sub.setName("Biology");
		dao.deleteSubject(sub);*/
		
		/*sub.setName("Arabian");
		dao.insertSubject(sub);*/
		
		/*sub.setName("Croatian");
		dao.insertSubject(sub);*/
		
		/*sub.setName("Chemistry");
		dao.deleteSubject(sub);*/
		
		//dao.selectSubjectByID((long) 1);
		
		
		/*System.out.println(dao.selectSubjectByID((long) 1));
		System.out.println(dao.selectSubjectByID((long) 5));
		System.out.println(dao.selectSubjectByID((long) 7));*/
		
		/*System.out.println(dao.selectAllSubjectsTO());
		
		System.out.println(dao.selectSubjectByName("German"));
		System.out.println(dao.selectSubjectByName("german"));
		System.out.println(dao.selectSubjectByName("Ger"));
		System.out.println(dao.selectSubjectByName("man"));
		System.out.println(dao.selectSubjectByName("a"));
		System.out.println(dao.selectSubjectByName("an"));*/
		
		/*sub.setName("Arabian");
		dao.deleteSubject(sub);
		
		System.out.println(dao.selectAllSubjectsTO());
		
		sub.setName("Arabian");
		dao.insertSubject(sub);
		*/
		/*sub.setName("Suahili");
		dao.insertSubject(sub);*/
		
		Subject sbjct = new Subject(); // = dao.selectSubjectByID(12);
		//System.out.println("Initial subject: " + sbjct.getName() + " " + sbjct.getId());
		
		sbjct.setName("Niemiecki");
		sbjct.setId((long) 14);
		dao.updateSubject(sbjct);
		
		/*sbjct = dao.selectSubjectByID(12);
		System.out.println("Updated subject: " + sbjct.getName() + " " + sbjct.getId());*/
		
		System.out.println(dao.selectAllSubjectsTO());
		
		System.out.println("Gets here");
		
	}


	
}
