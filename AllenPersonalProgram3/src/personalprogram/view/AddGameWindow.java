package personalprogram.view;


import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personalprogram.model.Game;
import personalprogram.model.Store;
import personalprogram.model.Transaction;

public class AddGameWindow  implements EventHandler<ActionEvent>{
	private Stage stage;
	private Game game;
	private Store store;
	private Text nameTxt = new Text("");
	private Text priceTxt = new Text("");
	private Text copiesTxt = new Text("");
	private Text formatTxt = new Text("");
	private Button confirmBtn = new Button("Confirm order");
	private Button cancelBtn = new Button("Cancel Order");
	public void show(Store s, Game o) {
		// TODO Auto-generated method stub
		store = s;
		game = o;
		stage.show();
		nameTxt.setText(game.getName());
		formatTxt.setText(game.getFormat());
		copiesTxt.setText(Integer.toString(game.getCopies()));
		priceTxt.setText(Double.toString(game.getPrice()));
	}

	public AddGameWindow()
	{
		stage = new Stage();
		GridPane pane = new GridPane();

		Scene scene = new Scene(pane, 150, 100);
		stage.setTitle("Purchase Window");

		Label nameLbl = new Label("Title");
		Label priceLbl = new Label("Price");
		Label formatLbl = new Label("Format");
		Label copiesLbl = new Label("Copies left");
		pane.add(formatLbl, 1, 1);
		pane.add(copiesLbl, 2, 1);
		pane.add(priceLbl, 3,1);
		pane.add(nameLbl, 0,1);
		pane.add(priceTxt, 3,2);
		
		pane.add(nameTxt, 0,2);
		pane.add(copiesTxt, 2,2);
		pane.add(formatTxt, 1,2);
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
			int copies = game.getCopies();
			/*if(copies != 0) {
				
			}*/
			
			//Transaction r = store.createRental(item, format, price);
			if(copies == 0){
				

				Alert quitConfimation = new Alert(Alert.AlertType.WARNING, "Sorry but we appear to be sold out please try again another time");
				Optional<ButtonType> response = quitConfimation.showAndWait();
				if (response.isPresent()) {
					if (response.get() == ButtonType.OK)
						stage.close();
				}
			}
			else {
				
				store.addItem(game);
				copies = game.subCopies();
				stage.close();
			} 
			
				
			
		} 
		else {
			stage.close();
		}
	}

}
