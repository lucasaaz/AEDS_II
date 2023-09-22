public class Palindromo {
	
	//metodo para finalizar
	public static boolean isFim(String s){
	    return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}
	
	//ver se e Palindromo
	public static boolean isPalindromo(String s) {
		boolean resp = false;
		int n = 0;
		int cont = 0;
		
		
		for(int i = 0; i < s.length(); i++) {
			n++;		
			for(int j = s.length() - n; j > s.length() - (n + 1); j--) {			
				
				System.out.println(n);
				System.out.println(i);
				System.out.println(cont);
				System.out.println(j);
				System.out.println(s.charAt(i));
				System.out.println(s.charAt(j));			
				
				if( s.charAt(i) == s.charAt(j) ) {
					System.out.println("letra igual");
					cont++;
				}else {
					System.out.println("letra diferente");
				}						
			}			
		}

		//confirmar se a palavra e Palindromo
		if(cont == s.length()) {
			resp = true;
		} 
		
		return resp;
	}
	
	public static String simNao(Boolean x) {
		String resp = null;
		
		if( x == true) {
			System.out.println("SIM");
		}else {
			System.out.println("NAO");
		}
		
		return resp;
	}
	

	public static void main(String[] args) {
		String[] entradas = new String[1000];
		int numEntradas = 0;
		//String teste = "anqsna";
		
		
		//colher dados de entrada
		while( isFim(entradas[numEntradas]) == false) {
			
			entradas[numEntradas] = MyIO.readLine();
			
			numEntradas++;
		}
		
		//chamar funcao
		for(int i = 0; i < numEntradas; i++) {
			boolean resp = isPalindromo(entradas[i]);
			simNao(resp);
		}
		
		
		
		//boolean resp = isPalindromo(teste);
		//System.out.println(resp);

	}//end Main

}//end TP01Q01

