package nivelamento;

import java.util.Scanner;

public class unidade00g_4 {
	
	public static void main(String[] args) {
		try (Scanner teclado = new Scanner(System.in)) {
			// ler placares	
			System.out.println("1° numero de gols: ");
			int valor1 = teclado.nextInt();
			System.out.println("2° numero de gols: ");
			int valor2 = teclado.nextInt();
			
			// aalisar placar
			if( valor1 > valor2) {
				System.out.println("Placar do jogo: " + valor1 + " X " + valor2);
				System.out.println("Vitoria time da casa");
			}else if( valor2 > valor1){
				System.out.println("Placar do jogo: " + valor1 + " X " + valor2);
				System.out.println("Vitoria time de fora");
			}
		}

	}
	
}
