#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
  char frase[100];
  int i, tam, Inteiro = 0, contadorV = 0, contadorC = 0;
  int Real = 0;
  int cont = 0;

  while(cont == 0){
  //printf("Digite uma frase\n");
  fgets(frase, 100, stdin);

  tam = strlen(frase);

  //Ver se chegou o FIM
  for(i = 0; i < strlen(frase); i++){
  if(frase[i] == 'F' || frase[i] == 'I' || frase[i] == 'M')
     cont += 1;
     break;
  }

  //Ver se e tudo vogal / Ver se e tudo consoante
  for(i=0; i<strlen(frase); i++)
  {
	if((frase[i]=='a')||(frase[i]=='i')||(frase[i]=='e')||(frase[i]=='o')||(frase[i]=='u')
    	|| (frase[i]=='A')||(frase[i]=='I')||(frase[i]=='E')||(frase[i]=='O')||(frase[i]=='U'))
	{
		contadorV++;
	}else if((frase[i] >= 'a' && frase[i] <= 'z') || (frase[i] >= 'A' && frase[i] <= 'Z')){
	        contadorC++;
    	}
  }
  //Ver se e inteiro
  for(i=0; i<strlen(frase); i++)
  {
	if((frase[i]=='1')||(frase[i]=='2')||(frase[i]=='3')||(frase[i]=='4')||(frase[i]=='5')
         ||(frase[i]=='6')||(frase[i]=='7')||(frase[i]=='8')||(frase[i]=='9')||(frase[i]=='0'))
	{
		Inteiro++;
	}
  }
  //Ver se e real
  for(i=0; i<strlen(frase); i++)
  {
	if((frase[i]=='1')||(frase[i]=='2')||(frase[i]=='3')||(frase[i]=='4')||(frase[i]=='5')
         ||(frase[i]=='6')||(frase[i]=='7')||(frase[i]=='8')||(frase[i]=='9')||(frase[i]=='0')
         ||(frase[i]=='.') ||(frase[i]==','))
	 {
		Real++;
	}
  }


  //Mostrar se e tudo vogal
  if(contadorV == tam - 1){
    printf("SIM ");
  }else{
    printf("NAO ");
  }
  //Mostrar se e tudo consoante
  if(contadorC == tam - 1){
    printf("SIM ");
  }else{
    printf("NAO ");
  }
  //Mostrar se e inteiro
  if(Inteiro == tam - 1){
    printf("SIM ");
  }else{
    printf("NAO ");
  }
  //Mostrar se e real
  if(Real == tam - 1){
    printf("SIM \n");
  }else{
    printf("NAO \n");
  }
  }
}

