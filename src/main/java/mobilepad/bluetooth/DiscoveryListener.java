package mobilepad.bluetooth;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class DiscoveryListener implements javax.bluetooth.DiscoveryListener
{
	private final ConnectionHandler connectionHandler;


	public DiscoveryListener(ConnectionHandler handler) {
		this.connectionHandler = handler;
	}


	public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
		connectionHandler.setRemoteDevice(remoteDevice, deviceClass);
		connectionHandler.lock.notify();
	}


	public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {

	}


	public void serviceSearchCompleted(int i, int i1) {

	}


	public void inquiryCompleted(int i) {

	}
}
