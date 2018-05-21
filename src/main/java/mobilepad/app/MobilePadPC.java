package mobilepad.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mobilepad.app.exception.ApplicationInitializationException;

public class MobilePadPC extends Application
{
	public MobilePadPC() throws ApplicationInitializationException {
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
		primaryStage.setTitle("MobilePadPC");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}


	public static void main(String[] args) {
//		Message message = new InfoMessage("TEST");
//		XMLEncoder encoder=new XMLEncoder(System.out);
//		Consumer<Integer> consumer = i-> System.out.println(i);
//		encoder.writeObject(message);
//		encoder.close();

		launch(args);
	}
}
