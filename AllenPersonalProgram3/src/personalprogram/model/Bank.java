package personalprogram.model;

public class Bank {
	private String title;
	private double Balance;
	
	public Bank(String title, double Balance) {
		this.title = title;
		this.Balance = Balance;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getBalance() {
		return Balance;
	}
	
	public void addDeposit(double amount) {
		Balance += amount;
	}
	public void Withdraw(double amount) {
		Balance -= amount;
	}
	
	
	public void transferBalances(double balance1, double balance2) {
		
	}


}
