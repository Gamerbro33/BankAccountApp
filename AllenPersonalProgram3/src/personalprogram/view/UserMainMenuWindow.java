package personalprogram.view;


import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class UserMainMenuWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private SqliteTester sql;
	private SqliteUsersBank userSql;
	public static Bank b;
	public static User u;
	private static ArrayList<Bank> bankList;

	private Button DepositBtn = new Button("Deposit");
	private Button WithdrawBtn = new Button("Withdraw");
	private Button TransferBtn = new Button("Transfer Funds");
	private Button addBtn = new Button("Add new Balance");
	private Button logoutBtn = new Button("Logout");
	public void show() {
		// TODO Auto-generated method stub
		stage.show();
		
	}

	public UserMainMenuWindow(User user)
	{
		u = user;
		//userSql.connectUsersBank(user);
		//userSql.BankAccount(user, bank, bankList);
		stage = new Stage();
		GridPane pane = new GridPane();
		
		Scene scene = new Scene(pane, 450, 300);
		stage.setTitle("Welcome"+user.getUsername());
		userSql.connectUsersBank(u);
		bankList = userSql.BankAccount(u);
		

		Label TitleLbl = new Label("Title:");

		Label BalanceLbl = new Label("Balance:");
		pane.add(TitleLbl, 0, 1);
		pane.add(BalanceLbl, 1, 1);
		int i = 2;
		for(Bank bank : bankList) {
			Label usersTitleLbl = new Label(bank.getTitle());
			Label  usersBalanceLbl = new Label(Double.toString(bank.getBalance()));
			pane.add(usersTitleLbl, 0,i);
			pane.add(usersBalanceLbl, 1,i);
			i++;
		}
		
		pane.add(DepositBtn, 0, i+1);
		pane.add(WithdrawBtn, 1, i+1);
		pane.add(TransferBtn, 2, i+1);
		pane.add(addBtn, 3, i+1);
		pane.add(logoutBtn, 4, i+1);
		DepositBtn.setOnAction(this);
		WithdrawBtn.setOnAction(this);
		TransferBtn.setOnAction(this);
		addBtn.setOnAction(this);
		logoutBtn.setOnAction(this);

		stage.setScene(scene);
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (DepositBtn == event.getSource()) {
			DepositWindow deposit = new DepositWindow(u, bankList);
			bankList.clear();
			deposit.show();
			stage.close();
		} else if (WithdrawBtn == event.getSource()) {
			WithdrawWindow withdraw = new WithdrawWindow(u, bankList);
			bankList.clear();
			withdraw.show();
			stage.close();
		} else if(TransferBtn == event.getSource()) {
			TransferWindow tranfer = new TransferWindow(u, bankList);
			bankList.clear();
			tranfer.show();
			stage.close();
		} else if(addBtn == event.getSource()) {
			AddWindow add = new AddWindow(u, bankList);
			bankList.clear();
			add.show();
			stage.close();
		}  else if(logoutBtn == event.getSource()) {
			
			bankList.clear();
			stage.close();
		} else {
			stage.close();
		}
	}
	


}
