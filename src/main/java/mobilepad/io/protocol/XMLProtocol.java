package mobilepad.io.protocol;

import mobilepad.io.protocol.exception.ProtocolDecodeException;
import mobilepad.io.bluetooth.message.Message;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class XMLProtocol implements Protocol
{
	@Override
	public Message decode(byte[] data) throws ProtocolDecodeException {
		Protocol.assertNotNull(data);
		Protocol.assertNonZero(data);

		ByteArrayInputStream in = new ByteArrayInputStream(data);
		XMLDecoder decoder = new XMLDecoder(in);
		try{
			return (Message) decoder.readObject();
		}
		catch (ClassCastException e){
			throw new ProtocolDecodeException("Received object could not be decoded as Message.");
		}
	}


	@Override
	public byte[] encode(Message message) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(message);
		encoder.flush();
		return out.toByteArray();
	}
}
