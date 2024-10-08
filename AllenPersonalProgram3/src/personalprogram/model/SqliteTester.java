package personalprogram.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*import javafx.application.Application;
import javafx.stage.Stage;
import personalprogram.model.Store;
import personalprogram.view.StartupWindow;
*/
import java.sql.*;
import java.util.Scanner;
import java.util.UUID; 


public class SqliteTester {

	private static String urlDirectory = "jdbc:sqlite:Place sqlite file path here";
	private static SqliteUsersBank userBank;
	private static UUID id;
	public static User user;
	   /**
     * Connect to a sample database
     */
	  /**
     * Connect to a sample database
     */
    public static void connect() {
            // db parameters
            // create a connection to the database
            var sql = "CREATE TABLE IF NOT EXISTS javaUniqueUser ( " 
            +"UUID TEXT NOT NULL,"
            +"username TEXT NOT NULL,"
            +"password TEXT NOT NULL,"
            +"email TEXT NOT NULL,"
            +"ssn INTEGER NOT NULL"
            +");";
            try (var conn = DriverManager.getConnection(urlDirectory);
                    var stmt = conn.createStatement()) {
                   // create a new table
                   stmt.execute(sql);
               } catch (SQLException e) {
                   System.out.println(e.getMessage());
               }  
    }
    //This will help encrypt the password into hash SHA3-256
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static String messageDigest(String password) {
    	MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA3-256");
			final byte[] hashbytes = digest.digest(
			password.getBytes(StandardCharsets.UTF_8));
			String sha3Hex = bytesToHex(hashbytes);
			return sha3Hex;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return password;
    }
    
    public static void addingData(String username, String password, String email, int ssn) {
    	 id = UUID.randomUUID();
    	 String newPassword = messageDigest(password);
    	 String sql = "INSERT INTO javaUniqueUser(UUID, username,"
    	 		+ " password,"
    	 		+ " email,"
    	 		+ " ssn) VALUES(?,?,?,?,?);";
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		 
    		 pstmt.setString(1,id.toString());
    		 pstmt.setString(2,username);
    		 pstmt.setString(3,newPassword);
    		 pstmt.setString(4,email);
    		 pstmt.setInt(5,ssn);
    		 user = new User(id, username);
    		 pstmt.executeUpdate();
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
    }
    //This is to help make the grabbed user for separate packages(Note: figure out why it doesn't work for boolean searchdatabase)
    public static User grabDatabase(User user, String username, String password) {
   	 String newPassword = messageDigest(password);
   	
   	PreparedStatement p = null;
   	ResultSet rs = null;
   	String sql = "SELECT UUID, username, password FROM javaUniqueUser WHERE username= ? AND password= ?";
   	 try (var conn = DriverManager.getConnection(urlDirectory); 
   			 var pstmt = conn.prepareStatement(sql)) {
   		 //System.out.println(newPassword);
   		 pstmt.setString(1,username);
   		 pstmt.setString(2,newPassword);
   		 ResultSet result = pstmt.executeQuery();
   		 if(result.next()) {
   			 String id = result.getString("UUID");
   			 
   			 UUID OriginalId = UUID.fromString(id);
   			 //burrowedId = OriginalId;
   			 //System.out.println(id);
   			 //System.out.println(burrowedId);
   			 
   			 user = new User(OriginalId, username);

   			 //System.out.println("Testing cloning users"+user2.getUsername());
   			 
   			 System.out.println("Login successful");
   			 return user;
   			 
   		 } else {
   			 System.out.println("Login failed incorrect username or password");
   			 return null;
   			 
   		 }
   	 }catch (SQLException e) {
            System.out.println("Username or password not correct");
        }
   	 return null;
   }
    public static boolean searchDatabase(String username, String password) {
    	 String newPassword = messageDigest(password);
    	
    	PreparedStatement p = null;
    	ResultSet rs = null;
    	String sql = "SELECT UUID, username, password FROM javaUniqueUser WHERE username= ? AND password= ?";
    	 try (var conn = DriverManager.getConnection(urlDirectory); 
    			 var pstmt = conn.prepareStatement(sql)) {
    		 //System.out.println(newPassword);
    		 pstmt.setString(1,username);
    		 pstmt.setString(2,newPassword);
    		 ResultSet result = pstmt.executeQuery();
    		 if(result.next()) {
    			 String id = result.getString("UUID");
    			 
    			 UUID OriginalId = UUID.fromString(id);
    			 //burrowedId = OriginalId;
    			 //System.out.println(id);
    			 //System.out.println(burrowedId);
    			 
    			 user = new User(OriginalId, username);

    			 //System.out.println("Testing cloning users"+user2.getUsername());
    			 
    			 System.out.println("Login successful");
    			 return true;
    			 
    		 } else {
    			 System.out.println("Login failed incorrect username or password");
    			 return false;
    			 
    		 }
    	 }catch (SQLException e) {
             System.out.println("Username or password not correct");
         }
    	 return false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	Scanner line = new Scanner(System.in);
    	
    	System.out.println("1:login\n2:Signup");
    	int input = line.nextInt();
    	line.nextLine();
    	if(input == 1) {
    		System.out.println("Please enter a username");
        	String username = line.nextLine();
        	System.out.println("Please enter a Password");
        	String password = line.nextLine();
        	if(searchDatabase(username, password)) {
        		System.out.println(user.getUsername());
        		userBank.BankMenu(user);
        	}
        	
    	} else if(input == 2) {
    		System.out.println("Please enter a username");
	    	String username = line.nextLine();
	    	System.out.println("Please enter a Password");
	    	String password = line.nextLine();
	    	System.out.println("Please enter a email");
	    	String email = line.nextLine();
	    	System.out.println("Please enter a ssn");
	    	int ssn = line.nextInt();
	        connect();
	        addingData(username, password, email, ssn);
    	}
    	
        System.out.println("Test done");
    }
}