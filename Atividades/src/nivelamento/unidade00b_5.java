package nivelamento;

public class unidade00b_5 {
	
	static boolean doidao(char c) {
		boolean resp = false;
		int v = (int)c;
		
		if(v == 65 || v == 69 || v == 73 || v == 79 || v == 85 || v == 97 || v ==101 || v == 105 || v == 111 || v == 117) {
			resp = true;
		}
		
		return resp;
	}

	public static void main(String[] args) {
		
		char letra = 'e';
		boolean resp = false;
		
		resp = doidao(letra);
		
		System.out.println(resp);
		
		
	}
	
}
