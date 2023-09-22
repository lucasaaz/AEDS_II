package nivelamento;

public class unidade00b_3 {

	public static int acharMaior(int[] num) {
		int i;
		int n = 0;
		int maior = n;
		
		for(i=0; i<num.length; i++) {
			if (n < num[i]) {
				maior = num[i];
				n = maior;
			}
		}
	return maior;
	}

	public static int acharMenor(int[] num) {
		int i;
		int n = num[0];
		int menor = n;
		
		for(i=0; i<num.length; i++) {
			if (n > num[i]) {
				menor = num[i];
				n = menor;
			}
		}
	return menor;
	}
	
	
	public static void main(String[] args) {
		
		int[] numeros = {22,44,66,88,100};
		
		int resposta = acharMaior(numeros);
		int resposta2 = acharMenor(numeros);
		
		System.out.println(resposta + " maior do array");
		
		System.out.println(resposta2 + " menor do array");
		
		
	}

}
