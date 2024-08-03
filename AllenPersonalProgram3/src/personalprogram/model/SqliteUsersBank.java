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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID; 

//todo might need to sha the titles and balance to keep it encrypted

public class SqliteUsersBank {
	//find a way to connect userto this class and have it hold it
	public static Bank bank;
	private static ArrayList<Bank> bankList;
	//public static User user;
	
	private static String urlDirectory = "jdbc:sqlite:Place sqlite db file path here";
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
    public static void addingBalancesMenu(User user) {
    	Scanner line = new Scanner(System.in);
    	System.out.println("Enter the name/title of your balance");
    	String title = line.nextLine();
    	
    	System.out.println("Please enter how much you would like to deposit in this balance");
    	double balance = line.nextInt();
    	line.nextLine();
    	bank = new Bank(title, balance);
		BankList.addBank(title, balance);
    	
    	addingBalance(user, title, balance);
    }
    public static void addingBalance(User user, String title, double balance) {
    	
    	 String sql = "INSERT INTO \""+user.getUUID()+"\"(Title,"
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
    //Same as printingBankAccount Function Except it for javafx to make a ArrayList to be printable
    public static ArrayList<Bank> BankAccount(User user) {
    	BankList banks = BankList.getInstance();
    	bankList = banks.getBankList();
    	
    	
    	 String sql = "SELECT * FROM \""+user.getUUID()+"\";";
    	 
    	
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		 ResultSet result = pstmt.executeQuery();
    		 //System.out.println("Title\t\tBalance");
    		
    		 while(result.next()) {
    			 String title = result.getString("Title");
    			 double balance = result.getDouble("Balance");
    			 //System.out.println(title+"\t\t"+balance);
    			 bank = new Bank(title, balance);
    			 BankList.addBank(title, balance);
    		 }
    		 return bankList;
    		  
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
    	 return null;
    }
    public static void printingBankAccount(User user2) {
    	BankList banks = BankList.getInstance();
    	bankList = banks.getBankList();
    	
    	
    	 String sql = "SELECT * FROM \""+user2.getUUID()+"\";";
    	 
    	
    	 try (var conn = DriverManager.getConnection(urlDirectory);
                 var pstmt = conn.prepareStatement(sql)) {
    		 ResultSet result = pstmt.executeQuery();
    		 System.out.println("Title\t\tBalance");
    		
    		 while(result.next()) {
    			 String title = result.getString("Title");
    			 double balance = result.getDouble("Balance");
    			 System.out.println(title+"\t\t"+balance);
    			 bank = new Bank(title, balance);
    			 BankList.addBank(title, balance);
    		 }
    		  
    	 }catch (SQLException e) {
             System.err.println(e.getMessage());
         }
    	
    }
    public static void updatingBalanceData(User user, Bank bank) {
    	var sql = "UPDATE \""+user.getUUID()+"\""
    			+ " SET Balance = ? "
    			+ "WHERE Title = ?;";
    	try(var conn = DriverManager.getConnection(urlDirectory);
                var pstmt = conn.prepareStatement(sql)) {
    		pstmt.setDouble(1,bank.getBalance());
    		pstmt.setString(2, bank.getTitle());
    		pstmt.executeUpdate();
    	} catch (SQLException e) {
    		System.err.println(e.getMessage());
    	}
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
    //Note Might be a int option
    private static int SelectedBalancesOptions() {
    	Scanner line = new Scanner(System.in);
    	int option;
    	int i = 1;
    	System.out.println("Please choose the following banks you wish to selectx");
    	for(Bank bank : bankList) {
    		System.out.println(i +":"+bank.getTitle()+" "+bank.getBalance());
    		i++;
    	}
    	option = line.nextInt();
    	if(option <= i && option != 0) {
    		return option - 1;
    	} else {
    		System.out.println("incorrect option please try again");
    		return SelectedBalancesOptions();//recursive for when user inputs wrong options
    	}
    }
    private static void Addfunds(User user) {
    	Scanner line = new Scanner(System.in);
    	int option = SelectedBalancesOptions();
    	
    	System.out.println("How much money would you like to deposit");
    	double deposit =  line.nextDouble();
    	line.nextLine();
    	
    	Bank selectedBalance = bankList.get(option);
    	System.out.println("Before"+selectedBalance.getBalance());
    	selectedBalance.addDeposit(deposit);
    	System.out.println("After"+selectedBalance.getBalance());
    	updatingBalanceData(user, selectedBalance);
    	
    	
    	
		
	}
    private static void Withdrawfunds(User user) {
    	Scanner line = new Scanner(System.in);
    	int option = SelectedBalancesOptions();
    	
    	System.out.println("How much money would you like to Withdraw");
    	double withdraw =  line.nextDouble();
    	line.nextLine();
    	
    	Bank selectedBalance = bankList.get(option);
    	System.out.println("Before"+selectedBalance.getBalance());
    	selectedBalance.Withdraw(withdraw);
    	System.out.println("After"+selectedBalance.getBalance());
    	updatingBalanceData(user, selectedBalance);

	}
    private static void TransferBalances(User user) {
    	Scanner line = new Scanner(System.in);
    	System.out.println("From");
    	int option1 = SelectedBalancesOptions();
    	System.out.println("To");
    	int option2 = SelectedBalancesOptions();
    	
    	System.out.println("How much money would you like to Transfer");
    	double Transfered =  line.nextDouble();
    	line.nextLine();
    	
    	Bank selectedBalance1 = bankList.get(option1);
    	Bank selectedBalance2 = bankList.get(option2);
    	System.out.println("Before"+selectedBalance1.getBalance());
    	selectedBalance1.Withdraw(Transfered);
    	selectedBalance2.addDeposit(Transfered);
    	System.out.println("After"+selectedBalance1.getBalance());
    	updatingBalanceData(user, selectedBalance1);
    	updatingBalanceData(user, selectedBalance2);

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
					addingBalancesMenu(user);
					break;
				case 2:
					TransferBalances(user);
					break;
				case 3:
					Addfunds(user);
					break;
				case 4:
					Withdrawfunds(user);
					break;
				case 5:
					loggedIn = false;
					break;
				default:
					System.out.println("incorrect input please try again");
			}
			
		}
		
		
	}
	
	
	public static void PrintOptions() {
		System.out.println("What would you like to do today\n"
				+ "1. Create a new banking\n"
				+ "2. Transfer funds between banking\n"
				+ "3. Deposit\n"
				+ "4. Withdraw Fund\n"
				+ "5. Logout");
	}
}