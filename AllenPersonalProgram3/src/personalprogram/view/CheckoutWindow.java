package personalprogram.view;



import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personalprogram.model.Store;

public class CheckoutWindow implements EventHandler<ActionEvent>{

	private Store store;
	private Stage stage;
	private Button confirmBtn = new Button("Confirm order");
	private Button cancelBtn = new Button("Cancel Order");
	private	Text priceTxt = new Text("");
	private ListView<String> transactionCB = new ListView<String>();
	private List<String> tran;
	
	public void show(Store store) {
			stage.show();
			this.store = store;
			String total = "";
			
			tran = store.getTransactionName();
			total = String.format("Total: $"+store.getTransactionTotal());
			priceTxt.setText(total);
			//List<String> transactionList = store.getTransactionName();
			transactionCB.getItems().setAll(tran);
		}

	public CheckoutWindow() {
		// TODO Auto-generated constructor stub
		
		this.stage = new Stage();
		GridPane pane = new GridPane();
		stage.setTitle("");
		Scene scene = new Scene(pane, 150, 100);
		stage.setTitle("Receipt Window");
		pane.getChildren().add(transactionCB);

			pane.add(priceTxt, 0,2);
		
		pane.add(confirmBtn, 0, 4);
		pane.add(cancelBtn, 2, 4);
		confirmBtn.setOnAction(this);
		cancelBtn.setOnAction(this);

		stage.setScene(scene);
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (confirmBtn == event.getSource()) {
			
			Alert quitConfimation = new Alert(Alert.AlertType.CONFIRMATION, "Are you certain?");
			Optional<ButtonType> response = quitConfimation.showAndWait();
			if (response.isPresent()) {
				if (response.get() == ButtonType.OK)
					tran.clear();
					stage.close();
				

			} else {
				Alert wimpAlert = new Alert(AlertType.INFORMATION, "Come on man, chose something!");
				wimpAlert.showAndWait();
			}stage.close();
		}

		else {
			stage.close();
		}
	}	


}
