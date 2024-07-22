package personalprogram;


import javafx.application.Application;
import javafx.stage.Stage;
import personalprogram.view.StartupWindow;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StartupWindow window = new StartupWindow(primaryStage);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
