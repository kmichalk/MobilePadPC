//package mobilepad.io.util;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class ByteArrayInputStreamReader
//{
//	private static final int DEFAULT_BUFFER_SIZE = 1024;
//	private InputStream in;
//	private int bufferSize;
//
//	public ByteArrayInputStreamReader(InputStream in) {
//		this.in = in;
//		this.bufferSize = DEFAULT_BUFFER_SIZE;
//	}
//
//
//	public ByteArrayInputStreamReader(InputStream in, int bufferSize) {
//		this.in = in;
//		this.bufferSize = bufferSize;
//	}
//
//
//	public byte[] readAll(){
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		byte[] buffer = new byte[bufferSize];
//		int nRead;
//		while (true){
//			try{
//				nRead = in.read(buffer);
//				if (nRead != -1)
//					out.write(buffer, 0, nRead);
//				if (nRead != -1 && nRead < buffer.length) {
//					return out.toByteArray();
//				}
//			}
//			catch (IOException e) {
//				return null;
//			}
//		}
//	}
//}
