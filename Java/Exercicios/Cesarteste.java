import java.util.Scanner;  
   
public class Cesarteste {  
      
      
   public static void main(String[] args) {  
           
           
  Scanner entrada = new Scanner(System.in);  
  String CP;
  String fim = "FIM";	
  int chave = 3;
	int i = 0;  
  int cont = 0;

	while(cont == 0){	
        //System.out.println("Digite seu texto aqui:");  
        String texto = entrada.nextLine();

	if(texto.equals(fim)){
           cont += 1;
	   System.exit(0);
	}   
  	
           
        while (chave>26){  
           chave = chave - 26;  
        }  
	

        i = chave;    
            
        CP = criptografa ( texto, chave);
        System.out.printf("%s\n", CP);	  
	}          
   }
   
   
   public static String criptografa (String texto, int chave){  
            
         StringBuilder textoCifrado = new StringBuilder();  
            
         int tamanhoTexto = texto.length();  
           int totalm, totalM, menosm, menosM;
            
         for(int c=0; c < tamanhoTexto; c++){  
               
             int letraCifradaASCII = ((int) texto.charAt(c)) + chave; 
                
            if (((int) texto.charAt(c)) > 97 && ((int) texto.charAt(c)) <  122 ) {  
               while (((int) texto.charAt(c)) + chave > 122) {  
                   totalm = texto.charAt(c) + chave; 
                   menosm =   totalm - 122;
                   letraCifradaASCII = 96+menosm;
             }  
            }  
            if (((int) texto.charAt(c)) > 65 && ((int) texto.charAt(c)) <  90 ) {  
               while (((int) texto.charAt(c)) + chave > 90) {   
                  totalM = texto.charAt(c) + chave; 
                menosM =   totalM - 90;
               letraCifradaASCII = 64+menosM;
                }  
            }  
   
         textoCifrado.append( (char)letraCifradaASCII );  
         }  
             
            
         return textoCifrado.toString();  
     } 
   } 
