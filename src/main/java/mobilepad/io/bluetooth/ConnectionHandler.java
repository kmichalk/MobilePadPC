package mobilepad.io.bluetooth;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.util.ArrayList;
import java.util.List;


public class ConnectionHandler
{
	public static class RemoteDeviceData
	{
		public RemoteDevice device;
		public DeviceClass deviceClass;


		public RemoteDeviceData(RemoteDevice device, DeviceClass deviceClass) {
			this.device = device;
			this.deviceClass = deviceClass;
		}
	}

	List<RemoteDeviceData> remoteDevices;
	private LocalDevice localDevice;
	private DiscoveryAgent discoveryAgent;
	Object lock;


	void setRemoteDevice(RemoteDevice remoteDevice, DeviceClass deviceClass) {
		remoteDevices.add(new RemoteDeviceData(remoteDevice, deviceClass));
	}


	public ConnectionHandler() {
		this.localDevice = null;
		this.discoveryAgent = null;
		this.remoteDevices = new ArrayList<RemoteDeviceData>();
		this.lock = new Object();
	}


	public void setUpLocalDevice() throws BluetoothStateException {
		this.localDevice = LocalDevice.getLocalDevice();
		this.discoveryAgent = localDevice.getDiscoveryAgent();
	}


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
			localDevice.setDiscoverable(DiscoveryAgent.GIAC);

			UUID uuid = new UUID("d0c722b07e1511e1b0c40800200c9a66", false);
			System.out.println(uuid.toString());

			String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
			notifier = (StreamConnectionNotifier) Connector.open(url);
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
