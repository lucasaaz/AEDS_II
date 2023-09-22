package nivelamento;

public class unidade00b_9 {

	static int m1(int i) {
		System.out.println(i);
		return i--;
	}

	static int m2(int i) {
		System.out.println(i);
		return --i;
	}
	
	public static void main(String[] args) {
		
		int j = 10;
		int h = 10;
		
		m1(j);
		m2(h);
		
		System.out.println(j);
		System.out.println(h);
		
	}
}
