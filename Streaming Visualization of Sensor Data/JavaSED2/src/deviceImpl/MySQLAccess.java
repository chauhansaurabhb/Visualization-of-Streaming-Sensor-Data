package deviceImpl;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLAccess {
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  
	  
	  public void readDataBase(Double value, String unitOfMeasurement) throws Exception {
		    try {
		      // this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      
		      // setup the connection with the DB.
		      connect = DriverManager
		              .getConnection("jdbc:mysql://localhost/feedback?"
		                  + "user=root&password=root");
	     

		      // statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // resultSet gets the result of the SQL query
		      //resultSet = statement.executeQuery("select * from FEEDBACK.SENSOR");
		     // writeResultSet(resultSet);
		      
		      String sql = "INSERT INTO FEEDBACK.demo2 values (?, ?)";
		      PreparedStatement statement = connect.prepareStatement(sql);
		      statement.setDouble(1, value);
	          statement.setString(2, unitOfMeasurement);
	          statement.executeUpdate();
	          connect.close();
		      
		      //preparedStatement = connect.prepareStatement("insert into  FEEDBACK.Sensor values (value,'c')");
		      //preparedStatement.executeUpdate();
		      
		     // preparedStatement = connect.prepareStatement("SELECT value, uom from FEEDBACK.Sensor");
			//      resultSet = preparedStatement.executeQuery();
			//      writeResultSet(resultSet);
		      
		      
		     /* 

		      // preparedStatements can use variables and are more efficient
		      preparedStatement = connect
		          .prepareStatement("insert into  FEEDBACK.Sensor values (420,C)");
		      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
		      // parameters start with 1
		      preparedStatement.setString(1, "value");
		      preparedStatement.setString(2, "uom");
		      //preparedStatement.setString(3, "TestWebpage");
		      //preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
		     // preparedStatement.setString(5, "TestSummary");
		      //preparedStatement.setString(6, "TestComment");
		      preparedStatement.executeUpdate();

		      preparedStatement = connect
		          .prepareStatement("SELECT value, uom from FEEDBACK.Sensor");
		      resultSet = preparedStatement.executeQuery();
		      writeResultSet(resultSet);

		      // remove again the insert comment
		      preparedStatement = connect
		      .prepareStatement("delete from FEEDBACK.Sensor where value= ? ; ");
		      preparedStatement.setString(1, "Test");
		      preparedStatement.executeUpdate();
		      
		      resultSet = statement
		      .executeQuery("select * from FEEDBACK.Sensor");
		      writeMetaData(resultSet);  */
		      
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    }  

		  } 
	  
	  private void writeMetaData(ResultSet resultSet) throws SQLException {
		    // now get some metadata from the database
		    System.out.println("The columns in the table are: ");
		    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
		      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		    }
		  }

		  private void writeResultSet(ResultSet resultSet) throws SQLException {
		    // resultSet is initialised before the first data set
		    while (resultSet.next()) {
		      // it is possible to get the columns via name
		      // also possible to get the columns via the column number
		      // which starts at 1
		      // e.g., resultSet.getSTring(2);
		      Integer value = resultSet.getInt("value");
		      String uom = resultSet.getString("uom");
		      /*String summary = resultSet.getString("summary");
		      Date date = resultSet.getDate("datum");
		      String comment = resultSet.getString("comments");*/
		      System.out.println("Value: " + value);
		      System.out.println("Uom: " + uom);
		      /*System.out.println("Summary: " + summary);
		      System.out.println("Date: " + date);
		      System.out.println("Comment: " + comment); */
		    }
		  }

		  // you need to close all three to make sure
		  private void close() {
		   // close(resultSet);
		   // close(statement);
		  //  close(connect);
		  }
		  private void close(Closeable c) {
		    try {
		      if (c != null) {
		        c.close();
		      }
		    } catch (Exception e) {
		    // don't throw now as it might leave following closables in undefined state
		    }
		  }
	  
	  


}
