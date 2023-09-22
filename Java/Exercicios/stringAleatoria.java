import java.util.Random;
import java.util.Scanner;

public class stringAleatoria {

    public static void main(String[] args) {

        Random  gerador = new Random();
	Scanner     ler = new Scanner(System.in);
	int        cont = 0;
	String      fim = "FIM";
	String  palavra;

	while(cont == 0){
		//System.out.printf("Informe uma palavra: ");
		palavra = ler.nextLine();

		if(palavra.equals(fim)){
			cont += 1;
			System.exit(0);
		}

		gerador.setSeed(4);

		for (int i = 0; i < palavra.length(); i++)
        	{
			System.out.print((char)( 'a' + (Math.abs(gerador.nextInt()) % 26)));
        	}
		System.out.printf("\n");
    	}
    }

}
