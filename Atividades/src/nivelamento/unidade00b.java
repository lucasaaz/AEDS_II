package nivelamento;

public class unidade00b {

	public static boolean acharNumero(int n, int[] num) {
		boolean resposta = false;
		int i;
		
		for(i=0; i<5; i++) {
			if (n == num[i]) {
				resposta = true;
				return resposta;
			}
		}
	return resposta;
	}
	
	public static void main(String[] args) {
		
		int[] numeros = {2,4,6,8,10};

		int x = 6;
		
		boolean resposta = acharNumero(x, numeros);
		
		if(resposta == true) {
		System.out.println(x + " está contido no array");
		}else {
		System.out.println(x + " não está contido no array");
		}
		
	}

}
