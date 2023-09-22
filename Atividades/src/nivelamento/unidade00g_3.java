package nivelamento;

import java.util.Scanner;

public class unidade00g_3 {

	static int Menor(int valor1, int valor2, int valor3) {
		
		int resp = 0;
		
		if( valor1 > valor2 && valor3 > valor2) {
			resp = valor2;
		}else if( valor2 > valor1 && valor3 > valor1  ){
			resp = valor1; 
		}else if( valor2 > valor3 && valor1 > valor3 ) {
			resp = valor3;
		}
		
		return resp;
	}
	
	static int Maior(int valor1, int valor2, int valor3) {
		
		int resp = 0;
		
		if( valor1 < valor2 && valor3 < valor2) {
			resp = valor2;
		}else if( valor2 < valor1 && valor3 < valor1  ){
			resp = valor1; 
		}else if( valor2 < valor3 && valor1 < valor3 ) {
			resp = valor3;
		}
		
		return resp;
	}
	
	
	public static void main(String[] args) {
		try (Scanner teclado = new Scanner(System.in)) {
			// ler valores
			System.out.println("1° valor: ");
			int valor1 = teclado.nextInt();
			System.out.println("2° valor: ");
			int valor2 = teclado.nextInt();
			System.out.println("3° valor: ");
			int valor3 = teclado.nextInt();
			
			int menor = Menor(valor1, valor2, valor3);
			int maior = Maior(valor1, valor2, valor3);
			
			
			System.out.println("Menor valor: " + menor);
			System.out.println("Maior valor: " + maior);
		}

	}

}
