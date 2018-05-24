package mobilepad.app.ui;

import com.intel.bluetooth.DebugLog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import mobilepad.app.ApplicationComponent;
import mobilepad.app.control.ApplicationController;
import mobilepad.io.bluetooth.ConnectionHandler;

import javax.bluetooth.BluetoothStateException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;


public class UIController extends ApplicationComponent
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
	public ListView<InputEventElem> inputEventElemListView;
	@FXML
	public ListView<ControlEventItem> controlEventsListView;

	private ConnectionHandler connectionHandler;
	private EventConnector eventConnector;

	public UIController() {
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


	private File openDialogToSelectConfigurationFile(String title){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML Files", "xml"));
		return fileChooser.showOpenDialog(parentApplication.getPrimaryStage());
	}


	public void loadButtonPressed(ActionEvent ae) {
		try {
			ApplicationController controller = parentApplication.getController();
			controller.applyConfiguration(
					controller.getMappingFileHandler().loadConfiguration(
									openDialogToSelectConfigurationFile(
											"Load configuration file"),
									controller.getConfigurationProtocol()
						)
			);
		}
		catch (IOException e) {
			log(e);
		}
		catch (NullPointerException ignored){}
//		controlEventsListView
//				.getItems()
//				.add(new ControlEventItem(
//						new KeyEvent(java.awt.event.KeyEvent.VK_A),
//						"Event",
//						"..."));
	}

	public void saveButtonPressed(ActionEvent ae) {
		try {
			ApplicationController controller = parentApplication.getController();
			controller.getMappingFileHandler()
					.saveConfiguration(
							openDialogToSelectConfigurationFile(
									"Save configuration file"),
							controller.getConfigurationProtocol(),
							controller.getEventMapper().getConfiguration()
					);
		}
		catch (IOException e) {
			log(e);
		}
		catch (NullPointerException ignored){}
	}


	public void settingsButtonPressed(ActionEvent e) {
	}

	public void applyButtonPressed(ActionEvent e) {
	}

	public void mouseClickedOnControlEventsListView(MouseEvent me){
		try {
			eventConnector.controlEventElemSelected(
					controlEventsListView.getSelectionModel().getSelectedItem());
		}
		catch (NullPointerException | IllegalStateException e){
			log(e);
		}

		System.out.println(
				"Clicked on: " +
				controlEventsListView.getSelectionModel().getSelectedItem().getName());
	}

	public void mouseClickedOnInputEventsListView(MouseEvent e){
		eventConnector.inputEventElemSelected(
				inputEventElemListView.getSelectionModel().getSelectedItem());

		System.out.println(
				"Clicked on: " +
						inputEventElemListView.getSelectionModel().getSelectedItem().getName());
	}
}
