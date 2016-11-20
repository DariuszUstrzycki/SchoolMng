package com.ustrzycki.persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	
	/*public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SchoolMng?autoReconnect=true&useSSL=false";
	public static final String USER = "root";
	public static final String PASS = "jhtp_Java8";*/
		
	public static void closeQuietly(ResultSet resultSet) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeQuietly(Statement statement) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeQuietly(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printSQLException(SQLException ex) {

	    for (Throwable e : ex) {
	        if (e instanceof SQLException) {
	            if (ignoreSQLException(  ((SQLException)e).getSQLState()) == false  ) {

	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " +
	                    ((SQLException)e).getSQLState());

	                System.err.println("Error Code: " +
	                    ((SQLException)e).getErrorCode());

	                System.err.println("Message: " + e.getMessage());

	                Throwable t = ex.getCause();
	                while(t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	}
	
	// Instead of outputting SQLException information, 
	//you could instead first retrieve the SQLState then process the SQLException accordingly. 
	
	 public static boolean ignoreSQLException(String sqlState) {
		    if (sqlState == null) {
		      System.out.println("The SQL state is not defined!");
		      return false;
		    }
		    // X0Y32: Jar file already exists in schema
		    if (sqlState.equalsIgnoreCase("X0Y32"))
		      return true;
		    // 42Y55: Table already exists in schema
		    if (sqlState.equalsIgnoreCase("42Y55"))
		      return true;
		    return false;
		}

}
