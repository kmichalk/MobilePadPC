package mobilepad.app.ui;

import com.intel.bluetooth.DebugLog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import mobilepad.app.ApplicationComponent;
import mobilepad.io.bluetooth.ConnectionHandler;
import mobilepad.io.bluetooth.message.control.KeyEvent;

import javax.bluetooth.BluetoothStateException;
import java.util.List;
import java.util.logging.Level;


public class Controller extends ApplicationComponent
{
	@FXML
	public VBox mainBox;
	@FXML
	public Button connectButton;
	@FXML
	public Button refreshButton;
	@FXML
	public Button loadButton;
	@FXML
	public Button saveButton;
	@FXML
	public Button settingsButton;
	@FXML
	public Button applyButton;
	@FXML
	public ComboBox<ConnectionHandler.RemoteDeviceData> mobileDevicesDropBox;
	@FXML
	public ListView<ControlEventItem> availableControlEventsListView;

	private ConnectionHandler connectionHandler;


	public Controller() {
		this.connectionHandler = new ConnectionHandler();
		DebugLog.setDebugEnabled(true);
	}


	public void connectButtonPressed(ActionEvent e) {
		connectButton.setText("Disconnect");
	}


	public void refreshButtonPressed(ActionEvent e) {
		try {
			//connectionHandler.setUpLocalDevice();
			List<ConnectionHandler.RemoteDeviceData> remoteDevices = connectionHandler.refresh();
			mobileDevicesDropBox.setItems(FXCollections.observableList(remoteDevices));
		}
		catch (BluetoothStateException ex) {
			log(Level.INFO, ex);
		}
	}


	public void loadButtonPressed(ActionEvent e) {
		availableControlEventsListView
				.getItems()
				.add(new ControlEventItem(
						new KeyEvent(java.awt.event.KeyEvent.VK_A),
						"Event",
						"..."));
	}

	public void saveButtonPressed(ActionEvent e) {
	}


	public void settingsButtonPressed(ActionEvent e) {
	}


	public void applyButtonPressed(ActionEvent e) {
	}
}