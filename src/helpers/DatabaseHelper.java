/*
 * Project:				COMP3095_Curly_Boys
 * Assignment:			Assignment 1
 * Author(s):			| Patrick Murphy | Maxim Paxton | Nicholas Entecott | Nehaal Shaikh |
 * Student Number:		|   101103097    |  101064370   |     101090483     |   101095479   |
 * Date:				October 26, 2018
 * Description:			File that interacts directly with the database attached to this application.
 * 						Contains methods for developers to use.
 */

package helpers;

import java.sql.*;

public class DatabaseHelper {
	
	private static String username = "admin";;
	private static String password = "P@ssword1";
	private static String database = "COMP3095";
	
	private static Connection connect = null;
	
	// This Method Is for Testing Only  
	public static Connection connectDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password);
			return connect;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	}
	// Use this method when validation is taken care of and email confirmation is sent TO ADD NEW USER
	public static void addUserToDB(String Uname, String USurname, String Uaddress, String Umail, String Upassword ) throws Exception {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password); 
		    // the mySql insert statement
		    String query = " insert into USERS (firstname, lastname,address, email,password,role)"
		        + " values (?, ?, ?, ?, ?, ?)";
		    // create the mySql insert
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, Uname);
		    preparedStmt.setString (2, USurname);
		    preparedStmt.setString(3, Uaddress);
		    preparedStmt.setString(4, Umail);
		    preparedStmt.setString(5, Upassword);
		    preparedStmt.setString(6, "user");

		    // execute the prepared statement
		    preparedStmt.execute();
		      
		    conn.close(); 
			}catch(Exception e){ System.out.println(e);}  
	}
	
	// Use this method for Login only
	public static boolean checkIfUserIsMemberDB(String Umail,String Upassword) throws Exception {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password); 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from USERS");  
			while(rs.next()) { 
				String email = rs.getString("email");
		        String password = rs.getString("password");
				if(Umail.equals(email) && Upassword.equals(password)) {
					con.close();
					return true;
				}
			}
			}catch(Exception e){ System.out.println(e);}
		return false;  
	}
	
	// Use this method TO HELP CHECK EMAIL for new User subscription
	public static boolean checkIfEmailIsTakenDB(String Umail) throws Exception {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password); 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from USERS");  
			while(rs.next()) { 
				String email = rs.getString("email");
				if(Umail.equals(email)) {
					con.close();
					return true;
				}
			}
			}catch(Exception e){ System.out.println(e);}
		return false;  
	}
	// gets a string array holding information about the user
	public static String[] getUserInfo(String Umail) throws Exception {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password); 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from USERS WHERE email='" + Umail + "'");
			rs.next();
			
			String[] userInfo = {rs.getString("firstname"), rs.getString("lastname"), 
								rs.getString("email"), rs.getString("password"), 
								rs.getString("role")};
			con.close();
			return userInfo;
			}catch(Exception e){ System.out.println(e);}
		return null;  
		}
}
