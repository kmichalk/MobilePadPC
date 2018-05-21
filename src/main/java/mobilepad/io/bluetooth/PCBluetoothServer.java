package mobilepad.io.bluetooth;

import mobilepad.app.ApplicationComponent;
import mobilepad.io.bluetooth.message.Message;
import mobilepad.io.bluetooth.message.MessageHandler;
import mobilepad.io.protocol.Protocol;
import mobilepad.io.protocol.XMLProtocol;
import mobilepad.io.protocol.exception.ProtocolDecodeException;
import mobilepad.io.util.ByteArrayInputStreamReader;

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
	private static final Protocol DEFAULT_PROTOCOL = new XMLProtocol();

	private String UUID;
	private int discoveryMode;
	private int token;
	private volatile boolean running;
	private int processPeriod;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Protocol protocol;
	private MessageHandler messageHandler;


	public PCBluetoothServer(){
		this.UUID = new UUID("1101", true).toString();
		this.discoveryMode = DiscoveryAgent.GIAC;
		this.token = 255;
		this.running = false;
		this.processPeriod = 200;
		this.inputStream = null;
		this.outputStream = null;
		this.protocol = DEFAULT_PROTOCOL;
		this.messageHandler = new MessageHandler();
	}

	private void serverLoop(final StreamConnectionNotifier notifier) {
		try {
			while (running) {
				log("Waiting for connection...");
				handleConnection(notifier.acceptAndOpen());
			}
		}
		catch (Exception e) {
			log(e);
		}
		try {
			Thread.sleep(processPeriod);
		}
		catch (InterruptedException ex) {
			log(ex);
		}

	}


	private void handleConnection(StreamConnection conn) throws IOException {
		outputStream = conn.openOutputStream();
		inputStream = conn.openInputStream();
		startReadThread(inputStream);
		log("Connection found...");
	}



	private void startReadThread(final InputStream in) {
		Thread reader = new Thread(() -> {
			try {
				byte[] received = new ByteArrayInputStreamReader(in).readAll();
				Message message = protocol.decode(received);
				messageHandler.handle(message);
			}
			catch (ProtocolDecodeException e) {
				log(e);
			}
		});
		reader.start();
	}


	@Override
	public void run() {
		running = true;
		try {
			LocalDevice device = LocalDevice.getLocalDevice();
			device.setDiscoverable(DiscoveryAgent.GIAC);
			String url = "btspp://localhost:" + UUID + ";name=mobilepad.io.bluetooth.PCBluetoothServer";
			log(Level.INFO, "Create server by uri: " + url);
			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(url);
			serverLoop(notifier);
		}
		catch (Throwable t) {
			log(t);
		}
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
