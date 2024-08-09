package personalprogram.model;

public class Bank {
	private String title;
	private double balance;
	
	public Bank(String title, double balance) {
		this.title = title;
		this.balance = balance;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void addDeposit(double amount) {
		balance += amount;
	}
	public void withdraw(double amount) {
		balance -= amount;
	}
	
	
	public void transferBalances(double balance1, double balance2) {
		
	}


}
