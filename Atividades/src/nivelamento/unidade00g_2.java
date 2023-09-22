package nivelamento;

import java.util.Scanner;

public class unidade00g_2 {

	public static void main(String args[]){

		Scanner teclado = new Scanner(System.in);
		
		// ler lado do triangulo
		System.out.println("1° lado do triangulo: ");
		int lado1 = teclado.nextInt();
		System.out.println("2° lado do triangulo: ");
		int lado2 = teclado.nextInt();
		System.out.println("3° lado do triangulo: ");
		int lado3 = teclado.nextInt();
		
		// ver qual triangulo foi formado
		if( (lado1 == lado2 && lado1 != lado3) || (lado2 == lado3 && lado2 != lado1) || (lado3 == lado1 && lado1 != lado2) ) {
			System.out.println("Triangulo Escaleno!!");
		}else if(lado1 == lado2 && lado1 == lado3){
			System.out.println("Triangulo Equilatero!!");
		}else if(lado1 != lado2 && lado1 != lado3){
			System.out.println("Triangulo Isoceles!!");
		}
		
	}
	
}
