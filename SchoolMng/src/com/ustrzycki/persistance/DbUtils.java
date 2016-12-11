package com.ustrzycki.persistance;
/**
 * Utility class for DAO's. This class contains commonly used DAO logic which hass been refactored in
 * single static methods. 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	
	private DbUtils() {
        // Utility class, hide constructor.
    }

	
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
	 
	 /**
	     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
	     * given parameter values.
	     * @param connection The Connection to create the PreparedStatement from.
	     * @param sql The SQL query to construct the PreparedStatement with.
	     * @param returnGeneratedKeys Set whether to return generated keys or not. 
	     * @param values The parameter values to be set in the created PreparedStatement.
	     * @throws SQLException If something fails during creating the PreparedStatement.
	     */
	    public static PreparedStatement prepareStatement
	        (Connection connection, String sql, boolean returnGeneratedKeys, Object... values) throws SQLException
	    {
	        PreparedStatement statement = connection.prepareStatement(sql,
	            returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
	        setValues(statement, values);  // A constant indicating that auto-generated keys should be made available
	        
	        return statement;
	    }

	    /**
	     * Set the given parameter values in the given PreparedStatement.
	     * @param connection The PreparedStatement to set the given parameter values in.
	     * @param values The parameter values to be set in the created PreparedStatement.
	     * @throws SQLException If something fails during setting the PreparedStatement values.
	     */
	    public static void setValues(PreparedStatement statement, Object... values)
	        throws SQLException
	    {
	        for (int i = 0; i < values.length; i++) {
	            statement.setObject(i + 1, values[i]);
	        }
	    }

	    /**
	     * Converts the given java.util.Date to java.sql.Date.
	     * @param date The java.util.Date to be converted to java.sql.Date.
	     * @return The converted java.sql.Date.
	     */
	    public static java.sql.Date toSqlDate(java.util.Date date) {
	     return (date != null) ? new java.sql.Date(date.getTime()) : null;
	    }


}
