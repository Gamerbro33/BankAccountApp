package personalprogram.view;


import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personalprogram.model.Bank;
import personalprogram.model.SqliteTester;
import personalprogram.model.SqliteUsersBank;
import personalprogram.model.User;

public class WithdrawWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private SqliteTester sql;
	private SqliteUsersBank userSql;
	public static Bank bank;
	public static User user;
	private static ArrayList<Bank> bankList;

	private TextField amountField = new TextField();
	private Button confirm = new Button("Confirm");
	private Button cancel = new Button("Cancel");
	private final ComboBox comboBox;
	public void show() {
		// TODO Auto-generated method stub
		stage.show();
		
	}

	public WithdrawWindow(User u, ArrayList<Bank> b)
	{
		user = u;
		bankList = b;
		Label BalanceLbl = new Label("Balance:");
		Label AmountLbl = new Label("Withdraw amount:");
		//userSql.connectUsersBank(user);
		//userSql.BankAccount(user, bank, bankList);
		stage = new Stage();
		GridPane pane = new GridPane();

		comboBox = new ComboBox();
		for(Bank bank : b) {
			comboBox.getItems().add(
				bank.getTitle()+":"+bank.getBalance()
				);
		}
		
		Scene scene = new Scene(pane, 450, 300);
		stage.setTitle("Withdraw Window");
		userSql.connectUsersBank(u);
		bankList = userSql.BankAccount(u);
		
		pane.add(BalanceLbl,0,1);
		pane.add(comboBox,0, 2);
		pane.add(AmountLbl,0,3);
		pane.add(amountField,0,4);
		pane.add(confirm, 0, 5);
		pane.add(cancel, 1, 5);
		//System.out.println();

		comboBox.setOnAction(this);
		amountField.setOnAction(this);
		confirm.setOnAction(this);
		cancel.setOnAction(this);
		stage.setScene(scene);
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		//System.out.println(comboBox.getValue());
		double Withdraw =  Double.parseDouble(amountField.getText());
		if(confirm == event.getSource()) {
			UpdateAndsplitString(comboBox.getValue().toString(), Withdraw);
			UserMainMenuWindow mainMenu = new UserMainMenuWindow(user);
			mainMenu.show();
			stage.close();
		}else if(cancel == event.getSource()) {
			UserMainMenuWindow mainMenu = new UserMainMenuWindow(user);
			mainMenu.show();
			stage.close();
		}
		
		
	}

	private void UpdateAndsplitString(String object, double Withdraw) {
		// TODO Auto-generated method stub
		String[] arrOfStr = object.split(":", 2);
		String title = arrOfStr[0];
		double balance = Double.parseDouble(arrOfStr[1])-Withdraw;
		userSql.FronEndupdatingBalanceData(user, title, balance);
		//System.out.println(title+"\n"+balance);
	}

}
