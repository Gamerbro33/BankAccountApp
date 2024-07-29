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

//todo might need to sha the titles and balance to keep it encrypted

public class SqliteUsersBank {
	//find a way to connect userto this class and have it hold it
	//public static User user;
	
	private static String urlDirectory = "Place file path here";
	  /**
     * Connect to a sample database
     */
    public static void connectUsersBank(User user) {
            // db parameters
            // create a connection to the database
            var sql = "CREATE TABLE IF NOT EXISTS \""+user.getUUID()+"\" (" 
            +"Title TEXT NOT NULL,"
            +"Balance REAL NOT NULL"
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
   /* private static String bytesToHex(byte[] hash) {
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
    public static String messageDigest(String info) {
    	MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA3-256");
			final byte[] hashbytes = digest.digest(
			info.getBytes(StandardCharsets.UTF_8));
			String sha3Hex = bytesToHex(hashbytes);
			return sha3Hex;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return info;
    }*/
    
    public static void addingData(User user, String title, Double balance) {
    	
    	 String sql = "INSERT INTO "+user.getUUID()+"(Title,"
    	 		+ "Balance) VALUES(?,?);";
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1,title);
    		pstmt.setDouble(2,balance);
    		 pstmt.executeUpdate();
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
    }
    public static void printingBankAccount(User user2) {
    	
    	
    	 String sql = "SELECT * FROM \""+user2.getUUID()+"\";";
    	 
    	
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		 ResultSet result = pstmt.executeQuery();
    		 System.out.println("Title\t\tBalance");
    		
    		 while(result.next()) {
    			 String title = result.getString("Title");
    			 double balance = result.getDouble("Balance");
    			 System.out.println(title+"\t\t"+balance);
    		 }
    		  
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
    	
    }
    public static void updatingBalanceData(String data) {
    	
    }
    public static boolean searchdatabase(String username, String password) {
    	
    	PreparedStatement p = null;
    	ResultSet rs = null;
    	String sql = "";
    	 try (var conn = DriverManager.getConnection(urlDirectory); 
    			 var pstmt = conn.prepareStatement(sql)) {
    		
    		 ResultSet result = pstmt.executeQuery();
    		 if(result.next()) {
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
    private static void Addfunds(User user) {
		// TODO Auto-generated method stub
    	PreparedStatement p = null;
    	ResultSet rs = null;
    	String sql = "";
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		 ResultSet result = pstmt.executeQuery();
    		 System.out.println("Title\t\tBalance");
    		
    		 while(result.next()) {
    			 String title = result.getString("Title");
    			 double balance = result.getDouble("Balance");
    			 System.out.println(title+"\t\t"+balance);
    		 }
    		  
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
		
	}
    /**
     * @param args the command line arguments
     */
	public static void BankMenu(User user) {
		Scanner line = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("Welcome"+user.getUsername());
		connectUsersBank(user);
		printingBankAccount(user);
		boolean loggedIn = true;
		while(loggedIn) {
			PrintOptions();
			int options = line.nextInt();
			line.nextLine();
			
			switch(options) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					Addfunds(user);
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					System.out.println("incorrect input please try again");
			}
			
		}
		
		
	}
	
	
	public static void PrintOptions() {
		System.out.println("What would you like to do today\n"
				+ "1. Create a new banking\n"
				+ "2. Transfer funds between banking"
				+ "3. Deposit"
				+ "4. Withdraw Funds"
				+ "5. Logout");
	}
}