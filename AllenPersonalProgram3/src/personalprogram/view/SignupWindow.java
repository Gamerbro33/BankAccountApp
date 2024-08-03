package personalprogram.view;


import java.util.Optional;

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

public class SignupWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private SqliteTester sql;
	private SqliteUsersBank userBank;
	private TextField usernameField = new TextField();
	private TextField passwordField = new TextField();
	private TextField emailField = new TextField();
	private TextField ssnField = new TextField();
	private Button confirmBtn = new Button("Confirm");
	private Button cancelBtn = new Button("Cancel");
	public void show() {
		// TODO Auto-generated method stub
		stage.show();
		
	}

	public SignupWindow()
	{
		stage = new Stage();
		GridPane pane = new GridPane();

		Scene scene = new Scene(pane, 450, 300);
		stage.setTitle("Purchase Window");

		Label UsernameLbl = new Label("Username:");
		Label PasswordLbl = new Label("Password:");
		Label EmailLbl = new Label("Email:");
		Label SSNLbl = new Label("SSN:");
		
		pane.add(UsernameLbl, 0, 1);
		pane.add(usernameField, 0, 2);
		pane.add(PasswordLbl, 0, 3);
		pane.add(passwordField, 0, 4);
		pane.add(EmailLbl, 0, 5);
		pane.add(emailField, 0, 6);
		pane.add(SSNLbl, 0, 7);
		pane.add(ssnField, 0, 8);
		
		pane.add(confirmBtn, 0, 9);
		pane.add(cancelBtn, 2, 9);
		confirmBtn.setOnAction(this);
		cancelBtn.setOnAction(this);

		stage.setScene(scene);
	}
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		String username = usernameField.getText();
		String password = passwordField.getText();
		String email = emailField.getText();
		int ssn = Integer.parseInt(ssnField.getText());
		if (confirmBtn == event.getSource()) {
			sql.addingData(username, password, email, ssn);
			
		} 
		else {
			stage.close();
		}
	}

}
