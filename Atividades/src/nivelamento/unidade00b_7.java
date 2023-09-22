package nivelamento;

public class unidade00b_7 {
	
	static boolean isConsoante(String s, int i) {
		boolean resp = true;

		if(i == s.length()) {
			resp = true;
		}else if(isConsoante(s, s.charAt(i)) == false) {
			resp = false;
		}else {
			resp = isConsoante(s, i + 1);
		}
		
		return resp;
	}
	
	

	public static void main(String[] args) {
		
		int n = 6;
		String p = "awqdtf";
		boolean resp;
		
		resp = isConsoante(p, n);
		
		System.out.println(resp);
		
	}
	
}
