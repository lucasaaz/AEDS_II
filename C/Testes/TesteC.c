#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){

    char palavra[30], copia[30];
    int  i, tam, iguais, cont = 0;
    //bool false;

    //do{
    while(cont == 0){
    //printf("");
    scanf("%s", palavra);

    for(i = 0; i < strlen(palavra); i++){
    if(palavra[i] == 'F' || palavra[i] == 'I' || palavra[i] == 'M')
       cont += 1;
       break;
    }
    
    tam = strlen(palavra);
    for(i = 0; i < strlen(palavra); i++){
        copia[i] = palavra[tam - 1];
        tam--;
    }

    copia[i] = '\0';
    tam = strlen(palavra);

    for(i = 0; i < tam; i++){
        if(palavra[i] == copia[i])
            iguais++;
    }

    if(tam == iguais)
        printf("SIM\n");
    else
        printf("NAO\n");
    }
    //}while(cont == 0);

    return 0;
}
