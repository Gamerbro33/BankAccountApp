package personalprogram.model;

import java.util.ArrayList;

public class BankList {
	private static BankList bankList = null;
	private static ArrayList<Bank> banks = new ArrayList<>();
	
	public BankList() {
		banks = new ArrayList<Bank>();
	}

	public static BankList getInstance() {
		if(bankList == null) {
			bankList = new BankList();
        }
        return bankList;
	}
	
	 public static ArrayList<Bank> getBankList() {
	        return banks;
	    }

	public static void addBank(String title, double balance) {
		banks.add(new Bank(title, balance));
	}
	
	
}
