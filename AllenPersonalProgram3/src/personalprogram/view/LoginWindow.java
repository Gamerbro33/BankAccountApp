package personalprogram.view;


import java.util.Optional;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import personalprogram.model.SqliteTester;
import personalprogram.model.SqliteUsersBank;
import personalprogram.model.User;

public class LoginWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private SqliteTester sql;
	private TextField usernameField = new TextField();
	private TextField passwordField = new TextField();
	private static SqliteUsersBank userBank;
	public static UUID id;
	public static User user;

	private Button confirmBtn = new Button("Confirm");
	private Button cancelBtn = new Button("Cancel");
	public void show() {
		// TODO Auto-generated method stub
		stage.show();
		
	}

	public LoginWindow(User u)
	{
		user = u;
		stage = new Stage();
		GridPane pane = new GridPane();

		Scene scene = new Scene(pane, 450, 300);
		stage.setTitle("Purchase Window");

		Label UsernameLbl = new Label("Username:");

		Label PasswordLbl = new Label("Password:");
		pane.add(UsernameLbl, 0, 1);
		pane.add(usernameField, 0, 2);
		pane.add(PasswordLbl, 0, 3);
		pane.add(passwordField, 0, 4);

		
		pane.add(confirmBtn, 0, 5);
		pane.add(cancelBtn, 2, 5);
		confirmBtn.setOnAction(this);
		cancelBtn.setOnAction(this);

		stage.setScene(scene);
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		String username = usernameField.getText();
		String password = passwordField.getText();
		if (confirmBtn == event.getSource()) {
			if(sql.searchDatabase(username, password)) {
				user = sql.grabDatabase(user, username, password);
        		
				//userBank.BankMenu(user);
				//System.out.println("Testing userclass"+user.getUsername());
				
				System.out.println("window with login should open");
				UserMainMenuWindow userWindow = new UserMainMenuWindow(user);
				userWindow.show();
				stage.close();
			} else {
				System.out.println("run failed please try again");
			}
				
			
		} 
		else {
			stage.close();
		}
	}

}
