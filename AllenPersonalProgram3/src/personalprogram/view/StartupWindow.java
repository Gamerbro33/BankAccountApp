package personalprogram.view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import personalprogram.model.Game;
import personalprogram.model.Store;
import personalprogram.model.Transaction;

public class StartupWindow implements EventHandler<ActionEvent> {
	
	private Stage stage;
	private Transaction t;
	private Store store;
	private Button loginBtn = new Button("Login");
	private Button signUpBtn = new Button("Sign Up");
	private ListView<String> storeCB = new ListView<String>();
	
	public StartupWindow(Stage stage) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		//HBox buttonPanel = new HBox();
		VBox pane = new VBox();
		GridPane pane2 = new GridPane();
		
		//pane.getChildren().add(buttonPanel);
		Scene scene = new Scene(pane2, 450, 300);
		
		stage.setTitle("Main Screen");
		Label welcome = new Label("Welcome to placeHolder bank");
		pane2.add(welcome,0,0);
		pane2.add(loginBtn,0,1);
		//buttonPanel.getChildren().add(loginBtn);
		pane2.add(signUpBtn,0,2);
		//buttonPanel.getChildren().add(signUpBtn);
		
		/*Scene scene = new Scene(pane2, 450, 300);
		this.stage.setScene(scene);*/
		loginBtn.setOnAction(this);
		signUpBtn.setOnAction(this);
		this.stage.setScene(scene);
		
	}

	public void show() {
		// TODO Auto-generated method stub
		stage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(loginBtn == event.getSource())
		{
			LoginWindow addPurchase = new LoginWindow();
			addPurchase.show();
		
		}
		else if(signUpBtn == event.getSource())
		{
			
			//store.displayCart();
			CheckoutWindow cartPurchase = new CheckoutWindow();
			cartPurchase.show(store);
			
		}
		else
		{
			stage.close();
		}
	}

}
