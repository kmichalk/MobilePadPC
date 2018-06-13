package mobilepad.io.bluetooth;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handling listing and connecting bluetooth devices
 */
public class ConnectionHandler
{
	/**
	 * Data class with remote device parameters
	 */
	public static class RemoteDeviceData
	{
		/**
		 * the remote device
		 */
		public RemoteDevice device;
		/**
		 * The class of the device
		 */
		public DeviceClass deviceClass;


		/**
		 * Constructor
		 * @param device the device
		 * @param deviceClass the class of device
		 */
		public RemoteDeviceData(RemoteDevice device, DeviceClass deviceClass) {
			this.device = device;
			this.deviceClass = deviceClass;
		}
	}

	private List<RemoteDeviceData> remoteDevices;
	private LocalDevice localDevice;
	private DiscoveryAgent discoveryAgent;
	Object lock;


	void setRemoteDevice(RemoteDevice remoteDevice, DeviceClass deviceClass) {
		remoteDevices.add(new RemoteDeviceData(remoteDevice, deviceClass));
	}


	/**
	 * Default constructor
	 */
	public ConnectionHandler() {
		this.localDevice = null;
		this.discoveryAgent = null;
		this.remoteDevices = new ArrayList<RemoteDeviceData>();
		this.lock = new Object();
	}


	/**
	 * Initializes the connector state by setting local device
	 * @throws BluetoothStateException
	 */
	public void setUpLocalDevice() throws BluetoothStateException {
		this.localDevice = LocalDevice.getLocalDevice();
		this.discoveryAgent = localDevice.getDiscoveryAgent();
	}


	/**
	 * Refreshes the list of available devices
	 * @return list of available devices
	 * @throws BluetoothStateException if a connection error occurred with bluetooth
	 */
	public List<RemoteDeviceData> refresh() throws BluetoothStateException {
		waitForConnection();
//		DiscoveryListener listener = new DiscoveryListener(this);
//		this.discoveryAgent.startInquiry(DiscoveryAgent.GIAC, listener);
//		try {
//			synchronized (lock) {
//				lock.wait();
//			}
//		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return remoteDevices;
	}


	private void waitForConnection() {
		StreamConnectionNotifier notifier;
		StreamConnection connection = null;

		try {
			localDevice = LocalDevice.getLocalDevice();
			//localDevice.setDiscoverable(DiscoveryAgent.GIAC);

			UUID uuid = new UUID("1101", true);
			System.out.println(uuid.toString());

			String url = "btspp://localhost:" + uuid.toString() + ";name=PCBluetoothServer";
			notifier = (StreamConnectionNotifier) Connector.open(url);
			connection = notifier.acceptAndOpen();
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
