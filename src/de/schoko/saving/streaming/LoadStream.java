package de.schoko.saving.streaming;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LoadStream {
	private DataInputStream in;
	
	public LoadStream(String path) {
		try {
			in = new DataInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assert false : "Couldn't create FileInputStream to '" + path + "'";
		}
	}
	
	public LoadStream(InputStream inputStream) {
		in = new DataInputStream(inputStream);
	}
	
	public void close() throws IOException {
		in.close();
	}
	
	public byte readByte() {
		try {
			return in.readByte();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean readBoolean() {
		try {
			return in.readBoolean();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public double readDouble() {
		try {
			return in.readDouble();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int readInt() {
		try {
			return in.readInt();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public long readLong() {
		try {
			return in.readLong();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public float readFloat() {
		try {
			return in.readFloat();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/*
	public String readString() {
		try {
			return in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	public String readString() {
		try {
			/*int inte = in.readUnsignedShort();
			System.out.println("Bytes length: " + inte);
			String s = "";
			for (int i = 0; i < inte; i++) {
				s += String.valueOf(in.readShort());
				System.out.println(s);
			}
			return s;*/
			return in.readUTF();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @return An estimated length in bytes of the underlying stream
	 */
	public int getLength() {
		try {
			return in.available();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
