package mobilepad.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mobilepad.app.exception.ApplicationInitializationException;
import mobilepad.io.bluetooth.PCBluetoothServer;

public class MobilePadPC extends Application
{
	private PCBluetoothServer bluetoothServer;
	private Thread bluetoothServerThread;

	public MobilePadPC() throws ApplicationInitializationException {
	}

//	private final void initializeInstance() throws SingletonException {
//		Application.setInstance(this);
//	}

	public static MobilePadPC getInstance() throws ClassCastException {
		return (MobilePadPC)Application.getInstance();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui.fxml"));
		primaryStage.setTitle("MobilePadPC");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		this.bluetoothServer = new PCBluetoothServer();
		this.bluetoothServerThread = new Thread(this.bluetoothServer);
		this.bluetoothServerThread.start();
	}


	@Override
	public void stop() throws Exception {
		System.out.println("STOP");
		this.bluetoothServer.stop();
		this.bluetoothServerThread.join();
		super.stop();
	}


	public static void main(String[] args) {
		launch(args);
	}


	public PCBluetoothServer getBluetoothServer() {
		return bluetoothServer;
	}
}
