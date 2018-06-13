package mobilepad.io.bluetooth;

import mobilepad.app.Application;
import mobilepad.app.ApplicationComponent;
import mobilepad.app.exception.UnexpectedException;
import mobilepad.io.message.Message;
import mobilepad.io.protocol.Protocol;
import mobilepad.io.protocol.exception.ProtocolDecodeException;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;


public class PCBluetoothServer extends ApplicationComponent implements Runnable
{
	private String UUID;
	private int discoveryMode;
	private int token;
	private volatile boolean running;
	private int processPeriod;
	private InputStream inputStream;
	private OutputStream outputStream;
	private StreamConnectionNotifier notifier;
	private Protocol protocol;
	private volatile boolean reading;
	private Thread readerThread;


	public PCBluetoothServer(){
		this.UUID = new UUID("1101", true).toString();
		this.discoveryMode = DiscoveryAgent.GIAC;
		this.token = 255;
		this.running = false;
		this.processPeriod = 200;
		this.inputStream = null;
		this.outputStream = null;
		this.notifier = null;
		this.protocol = Application.DEFAULT_SERIALIZATION_PROTOCOL;
		this.reading = false;
		this.readerThread = null;
	}

	private void serverLoop(final StreamConnectionNotifier notifier) {
		this.notifier = notifier;
		try {
			while (running) {
				log("Waiting for connection...");
				handleConnection(notifier.acceptAndOpen());
			}
			Thread.sleep(processPeriod);
		}
		catch (Exception e) {
			log(e);
		}
	}


	private void handleConnection(StreamConnection conn) throws IOException {
		outputStream = conn.openOutputStream();
		inputStream = conn.openInputStream();
		startReadThread(inputStream);
		log("Connection found...");
	}




	private void startReadThread(final InputStream in) {
		readerThread = new Thread(() -> {
			Message message;
			reading = true;
			while (reading){
				try {
					message = protocol.decode(in);
					try{
						if (message != null)
							parentApplication.getController().handleMessage(message);
						//else
							//log(Level.WARNING, "Received invalid data. Message was null.");
					}
					catch (Throwable t){
						//log(new UnexpectedException("Failed to execute received message", t));
					}
				}
				catch (ProtocolDecodeException e) {
					//log(e);
				}
				catch (IOException e){
					//log(e);
					reading = false;
					break;
				}
				catch (Throwable t){
					//log(new UnexpectedException("Failed to decode received data", t));
				}
			}
		});
		readerThread.start();
	}


	@Override
	public void run() {
		running = true;
		try {
			LocalDevice device = LocalDevice.getLocalDevice();
			device.setDiscoverable(DiscoveryAgent.GIAC);
			String url = "btspp://localhost:" + UUID + ";name=PCBluetoothServer";
			log(Level.INFO, "Created server by uri: " + url);
			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(url);
			serverLoop(notifier);
		}
		catch (Throwable t) {
			log(new UnexpectedException(t));
		}
	}


	public void stop(){
		reading = false;
		running = false;
		abortCurrentConnection();
	}


	public void abortCurrentConnection(){
		try {
			notifier.close();
		}
		catch (IOException ignored) { }
	}


	public void setProcessPeriod(int processPeriod) throws IllegalStateException {
		if (running)
			throw new IllegalStateException("Could not set process period. Server was already running.");
		this.processPeriod = processPeriod;
	}


	public void setProtocol(Protocol protocol) throws IllegalStateException {
		if (running)
			throw new IllegalStateException("Could not set protocol. Server was already running.");
		this.protocol = protocol;
	}
}
