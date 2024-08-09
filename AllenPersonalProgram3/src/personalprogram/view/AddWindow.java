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
import personalprogram.model.BankList;
import personalprogram.model.SqliteTester;
import personalprogram.model.SqliteUsersBank;
import personalprogram.model.User;

public class AddWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private SqliteTester sql;
	private SqliteUsersBank userSql;
	public static Bank bank;
	public static User user;
	private static ArrayList<Bank> bankList;

	private TextField titleField = new TextField();
	private TextField amountField = new TextField();
	private Button confirm = new Button("Confirm");
	private Button cancel = new Button("Cancel");
	public void show() {
		// TODO Auto-generated method stub
		stage.show();
		
	}

	public AddWindow(User u, ArrayList<Bank> b)
	{
		user = u;
		bankList = b;
		Label TitleLbl = new Label("Please Enter the title");
		Label BalanceLbl = new Label("Please enter how much you would like to deposit");
		//userSql.connectUsersBank(user);
		//userSql.BankAccount(user, bank, bankList);
		stage = new Stage();
		GridPane pane = new GridPane();

		
		
		Scene scene = new Scene(pane, 450, 300);
		stage.setTitle("Withdraw Window");
		userSql.connectUsersBank(u);
		bankList = userSql.bankAccount(u);
		
		pane.add(TitleLbl,0,1);
		pane.add(titleField,0,2);
		
		pane.add(BalanceLbl,0,3);
		pane.add(amountField,0,4);
		pane.add(confirm,0,5);
		pane.add(cancel,1,5);
		//System.out.println();

		titleField.setOnAction(this);
		amountField.setOnAction(this);
		confirm.setOnAction(this);
		cancel.setOnAction(this);
		stage.setScene(scene);
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		//System.out.println(comboBox.getValue());
		String title = titleField.getText();
		double deposit = Double.parseDouble(amountField.getText());
		if(confirm == event.getSource()) {
			userSql.addingBalance(user, title, deposit);
			UserMainMenuWindow mainMenu = new UserMainMenuWindow(user);
			mainMenu.show();
			stage.close();
		}else if(cancel == event.getSource()) {
			UserMainMenuWindow mainMenu = new UserMainMenuWindow(user);
			mainMenu.show();
			stage.close();
		}
		
		
	}

	private void updateAndSplitString(String option1, String option2, double transfer) {
		// TODO Auto-generated method stub
		String[] arrOfStr1 = option1.split(":", 2);
		String[] arrOfStr2 = option2.split(":", 2);
		String title1 = arrOfStr1[0];
		double balance1 = Double.parseDouble(arrOfStr1[1])-transfer;
		String title2 = arrOfStr2[0];
		double balance2 = Double.parseDouble(arrOfStr2[1])+transfer;
		userSql.frontEndUpdatingBalanceData(user, title1, balance1);
		userSql.frontEndUpdatingBalanceData(user, title2, balance2);
		//System.out.println(title+"\n"+balance);
	}

}
