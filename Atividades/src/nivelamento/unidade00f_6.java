package nivelamento;

import java.io.RandomAccessFile;

public class unidade00f_6 {
	
		public static void main(String[] args) throws Exception {
			
			RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw");
			
			raf.writeInt(10);
			raf.writeDouble(13.13);
			raf.writeChar('g');
			raf.writeBoolean(false);
			raf.writeBytes("galo");
			
			raf.close();
			}
	
	}
