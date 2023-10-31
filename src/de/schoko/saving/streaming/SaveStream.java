package de.schoko.saving.streaming;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveStream {
	private DataOutputStream out;
	
	public SaveStream(String path) {
		try {
			out = new DataOutputStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assert false : "Couldn't create FileOutputStream to '" + path + "'";
		}
	}
	
	public SaveStream(DataOutputStream outputStream) {
		this.out = outputStream;
	}
	
	protected void close() throws IOException {
		out.flush();
		out.close();
	}
	
	public void writeBytes(byte[] b) {
		try {
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeBoolean(boolean b) {
		try {
			out.writeBoolean(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeDouble(double d) {
		try {
			out.writeDouble(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeInt(int i) {
		try {
			out.writeInt(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFloat(float f) {
		try {
			out.writeFloat(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeLong(long l) {
		try {
			out.writeLong(l);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeString(String s) {
		try {
			/**byte[] bytes = s.getBytes();
			out.writeInt(bytes.length);
			System.out.println("Bytes.length " + bytes.length);
			out.write(bytes);*/
			out.writeUTF(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
