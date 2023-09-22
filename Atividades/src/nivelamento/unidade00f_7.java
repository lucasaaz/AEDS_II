package nivelamento;

import java.io.RandomAccessFile;

public class unidade00f_7 {
	
		public static void main(String[] args) throws Exception {
			
			RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw");
			
			int inteiro = raf.readInt();
			double real = raf.readDouble();
			char caractere = raf.readChar();
			boolean boleano = raf.readBoolean();
			String str = raf.readLine();
			
			raf.close();
			
			System.out.println("inteiro: " + inteiro);
			System.out.println("real: " + real);
			System.out.println("caractere: : " + caractere);
			System.out.println("boleano: " + boleano);
			System.out.println("sr: " + str);
			
			}
	
	}