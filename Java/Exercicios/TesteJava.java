import java.util.*;   
class TesteJava  
{  
   public static void main(String args[])  
   {  
      String original, reverse = ""; 
      String fim = "FIM"; 
      int contador = 0;  
      Scanner in = new Scanner(System.in);

      do{
      //System.out.println(" ");  
      original = in.nextLine(); 

      if(original.equals(fim)){
	  contador += 1;
	  System.exit(0);
      }	  

      int length = original.length();

      for ( int i = length - 1; i >= 0; i-- )  
         reverse = reverse + original.charAt(i);  
      if (original.equals(reverse))  
         System.out.println("SIM");  
      else  
         System.out.println("NAO");   
      }while(contador == 0);
   }  
}
