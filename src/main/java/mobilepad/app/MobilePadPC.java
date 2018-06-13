package mobilepad.app;

import com.intel.bluetooth.BlueCoveConfigProperties;
import com.intel.bluetooth.BlueCoveImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mobilepad.app.exception.ApplicationInitializationException;
import mobilepad.io.bluetooth.PCBluetoothServer;

/**
 * The main class of the program. Contains the entry point function main
 */
public class MobilePadPC extends Application
{
	private PCBluetoothServer bluetoothServer;
	private Thread bluetoothServerThread;


	/**
	 * Default constructor
	 * @throws ApplicationInitializationException when there is a problem with initializing application eg. singleton exception
	 */
	public MobilePadPC() throws ApplicationInitializationException {
	}

//	private final void initializeInstance() throws SingletonException {
//		Application.setInstance(this);
//	}


	/**
	 * Gets the instance of the application as the MobilePadPC object
	 * @return the instance
	 * @throws ClassCastException if the instance was not really MobilePadPC
	 */
	public static MobilePadPC getInstance() throws ClassCastException {
		return (MobilePadPC) Application.getInstance();
	}


	/**
	 * Method called when the application starts. Loads the GUI fxml and prepares the application interface and starts the bluetooth server.
	 * @param primaryStage the stage on which the application GUI runs
	 * @throws Exception if any uncaught exception occured in the application
	 */
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


	/**
	 * Stops the application by stopping bluetooth server and joining its thread before returning.
	 * This method should be called to close the application properly
	 * @throws Exception if an error occurs with the thread
	 */
	@Override
	public void stop() throws Exception {
		System.out.println("STOP");
		this.bluetoothServer.stop();
		this.bluetoothServerThread.join();
		super.stop();
	}


	/**
	 * The entry pont to application
	 * @param args arguments from command line
	 */
	public static void main(String[] args) {
		BlueCoveImpl.setConfigProperty(BlueCoveConfigProperties.PROPERTY_DEBUG_LOG4J, "0");
		BlueCoveImpl.setConfigProperty(BlueCoveConfigProperties.PROPERTY_DEBUG, "0");
		BlueCoveImpl.setConfigProperty(BlueCoveConfigProperties.PROPERTY_DEBUG_STDOUT, "0");
		launch(args);
	}


	/**
	 * Gets access to the bluetooth server
	 * @return the bluetooth server
	 */
	public PCBluetoothServer getBluetoothServer() {
		return bluetoothServer;
	}
}
