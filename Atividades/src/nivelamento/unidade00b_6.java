package nivelamento;

public class unidade00b_6 {
	
	static boolean isVogal(char c) {
		boolean resp = false;
		
		if( c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			resp = true;
		}
		
		return resp;
	}
	

	static boolean isConsoante(String s, int n) {
		boolean resp = true;
		
		
		if(n != s.length() && n >= 0) {
			if(s.charAt(n) < '0' || s.charAt(n) > '9') {
				if(isVogal(s.charAt(n)) == true) {
					resp = false;
				}else {
					resp = isConsoante(s, n + 1);
				}
			}else {
				resp = false;
			}
		}
		
		return resp;
	}
	
	

	public static void main(String[] args) {
		
		int c = 0;
		String x = "awqdtf";
		boolean resp;
		
		resp = isConsoante(x, c);
		
		System.out.println(resp);
		
	}
	
}
