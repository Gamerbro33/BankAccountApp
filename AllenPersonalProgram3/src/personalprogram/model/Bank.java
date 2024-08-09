package personalprogram.model;

public class Bank {
	private String title;
	private double balance;
	
	public Bank(String title, double balance) {
		this.title = title;
		String formatBalance = String.format("%.2f",balance);
		this.balance = Double.parseDouble(formatBalance);
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
