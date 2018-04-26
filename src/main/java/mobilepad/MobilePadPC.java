package mobilepad;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MobilePadPC extends mobilepad.Application
{
	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
		primaryStage.setTitle("MobilePadPC");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
