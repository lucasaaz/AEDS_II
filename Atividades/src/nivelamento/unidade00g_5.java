package nivelamento;

import java.util.Scanner;

public class unidade00g_5 {

	public static void main(String[] args) {
		try (Scanner teclado = new Scanner(System.in)) {
			// ler placares	
			System.out.println("1° nota: ");
			int valor1 = teclado.nextInt();
			System.out.println("2° nota: ");
			int valor2 = teclado.nextInt();
			System.out.println("3° nota: ");
			int valor3 = teclado.nextInt();
			System.out.println("4° nota: ");
			int valor4 = teclado.nextInt();
			System.out.println("5° nota: ");
			int valor5 = teclado.nextInt();

			int soma = valor1 + valor2 + valor3 + valor4 + valor5;
			float media = soma/5;
			
			System.out.println("Media das 5 notas: " + media);
		}
		


	}
	
	
}
