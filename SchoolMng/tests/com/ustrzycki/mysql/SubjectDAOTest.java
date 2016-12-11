package com.ustrzycki.mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ustrzycki.domain.Subject;
import com.ustrzycki.persistance.DAOException;
import com.ustrzycki.persistance.DAOFactory;
import com.ustrzycki.persistance.DaoManager;
import com.ustrzycki.persistance.DbUtils;
import com.ustrzycki.persistance.MySqlDAOFactory;



public class SubjectDAOTest {
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/TestDatabase";
	private static final String DISABLE_SSL = "?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "javaMySql2016";
	
	static final String TABLE = "Subjects";	
	private static SubjectDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// obtain DAO manager
		DaoManager daoManager = DaoManager.getInstance();

		// obtain a DAOFactory and set it on the DAOManager
		DAOFactory sqlDAOfactory = new MySqlDAOFactory(DATABASE_URL + DISABLE_SSL, USER, PASS);
		daoManager.setDAOFactory(sqlDAOfactory);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
		
	}

	@Before
	public void setUp() throws Exception {
		dao = new SubjectDAO();
	}

	@After
	public void tearDown() throws Exception {
		
		dao = null;
		deleteAll(); 
	}

	@Test
	public void insertSubject__ShouldInsert_SubjectsWithDifferentNames() {
		
		int initialNoOfSubjects = 0;
		assertEquals(initialNoOfSubjects, selectAll().size());
		
		dao.insertSubject(createSubject("Music"));
	
		assertEquals(initialNoOfSubjects + 1, selectAll().size());
		
		
		dao.insertSubject(createSubject("Social Science"));
		
		assertEquals(initialNoOfSubjects + 2, selectAll().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void insertSubject_ShouldThrowException_WhenArgumentHasId() {
		
		Subject s1 = createSubject("Polish");
		s1.setId((long)23);
		
		dao.insertSubject(s1);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void insertSubject_ShouldThrowException_WhenInsertingArgumentWithSameName() {
		
		dao.insertSubject(createSubject("Bio"));
		
		dao.insertSubject(createSubject("Bio"));
	}
	
	
	
	
	@Test
	public void selectSubjectByID_ShouldFindTheRightSubject_WhenIdProvided() {
		
		Subject notInserted = createSubject("German");
		Subject expected = createSubject("Polish");
		dao.insertSubject(expected);
		
		Subject actual = dao.selectSubjectByID(expected.getId());
		
		assertEquals(expected, actual);
		assertNotEquals(notInserted, actual);
	}
	

	@Test
	public void selectSubjectByName_ShouldFindTheRightSubject_WhenNameProvided() {

		Subject notInserted = createSubject("German");
		Subject expected = createSubject("Polish");
		dao.insertSubject(expected);
		
		Subject actual = dao.selectSubjectByName("Polish");

		assertEquals(expected, actual);
		assertNotEquals(notInserted, actual);
	}

	
	
	@Test
	public void updateSubject_ShouldUpdateSubject_WhenNewNameProvided() {

		Subject beforeUpdate = createSubject("Ger");
		dao.insertSubject(beforeUpdate);
		Subject update = createSubject("German");
		update.setId(beforeUpdate.getId());
		
		dao.updateSubject(update);
		
		assertNull(dao.selectSubjectByName("Ger"));
		assertEquals(update, dao.selectSubjectByName("German"));
	}
	
	@Test
	public void updateSubject_ShouldntCreateNewSubject_WhenUpdatingWithSameName() {
		
		Subject original = createSubject("Ger");
		dao.insertSubject(original);
		Subject pseudoUpdate = createSubject("Ger");
		pseudoUpdate.setId(original.getId());
				
		dao.updateSubject(pseudoUpdate);
		
		assertEquals(1, dao.selectAllSubjectsTO().size());
		Subject afterUpdate = dao.selectSubjectByName("Ger");
		assertEquals(original.getId(), afterUpdate.getId());
		
	}
	
	@Test (expected=DAOException.class)
	public void updateSubject_ShouldThrowException_WhenIdIsNotInDb() {
		
		Subject subject = createSubject("anything");
		Long idNotInDb = (long) 6477054;
		subject.setId(idNotInDb);
						
		dao.updateSubject(subject);
	}
	
	
	
	@Test
	public void deleteSubject_ShouldDelete_WhenInsertedAndDeletedObjectIsTheSame() {
		
		Subject insertedAndDeleted = createSubject("Spanish");
		dao.insertSubject(insertedAndDeleted);
		assertNotNull(dao.selectSubjectByName("Spanish"));
		
		dao.deleteSubject(insertedAndDeleted);
		
		assertNull(dao.selectSubjectByName("Spanish"));
		
	}
	
	@Test
	public void deleteSubject_ShouldDelete_WhenInsertedAndDeletedAreDifferentObjectWithSameNameAndId_() {
		
		String sharedName = "Physice";
		Subject first = createSubject(sharedName);
		dao.insertSubject(first);
		assertNotNull(dao.selectSubjectByName(sharedName));
		Long sharedId = first.getId();
		Subject second = createSubject(sharedName);
		second.setId(sharedId);
		
		dao.deleteSubject(second);
		
		assertNull(dao.selectSubjectByName(sharedName));
		
	}
	
	@Test (expected=DAOException.class)
	public void deleteSubject_ShouldThrowException_WhenDeletingArgumentWithoutId_() {
		
		Subject subject = createSubject("Art");
		dao.insertSubject(subject);
		Subject onlyNameSetIDNotSet = createSubject("Art");
		
		dao.deleteSubject(onlyNameSetIDNotSet);
		
	}
	
	@Test
	public void selectAllSubjectsTO_ShouldReturnAllPersistedSubjects() {

		dao.insertSubject(createSubject("Music"));
		dao.insertSubject(createSubject("Indian"));
		dao.insertSubject(createSubject("Polish"));
		
		String returnedSubjects = (dao.selectAllSubjectsTO()).toString();
		
		assertEquals(3, dao.selectAllSubjectsTO().size());
		assertTrue(returnedSubjects.contains("Music"));
		assertTrue(returnedSubjects.contains("Indian"));
		assertTrue(returnedSubjects.contains("Polish"));
		
		
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
	 
	 private Subject createSubject(String name){
		 
		 Subject s = new Subject();
		 s.setName(name);
		 return s;
	 }
	 
	 private void deleteAll() {

		 String sqlDeleteAll = "DELETE FROM " + TABLE;

		 try (Connection connection = MySqlDAOFactory.createConnection();
				 PreparedStatement statement = connection.prepareStatement(sqlDeleteAll);) {

			 statement.executeUpdate();

		 } catch (SQLException e) {
			 throw new DAOException(e);
		 }

	 }

	 private Collection<Subject> selectAll(){

		 final Collection<Subject> subjectsInDb = new ArrayList<>(); 
		 final String sqlSelectAll = "SELECT * FROM " + TABLE ;

		 try (Connection connection = MySqlDAOFactory.createConnection();
				 PreparedStatement statement = connection.prepareStatement(sqlSelectAll);
				 ResultSet resultSet = statement.executeQuery();) {
			 while (resultSet.next()) {
				 subjectsInDb.add(map(resultSet));
			 }
		 } catch (SQLException e) {
			 throw new DAOException(e);
		 }

		 return subjectsInDb;
	 }
	 
	

}
