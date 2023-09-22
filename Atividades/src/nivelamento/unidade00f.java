package nivelamento;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class unidade00f {

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				System.out.println(linha);

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Escreva algo: ");
			linha = in.nextLine();
		}
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}
	
	public static void main(String args[]) throws IOException {
		String path = "/home/lucas/Documentos/file.txt";

		unidade00f.escritor(path);
		unidade00f.leitor(path);
	}

}
