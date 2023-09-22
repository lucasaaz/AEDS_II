#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {

    char name[100];
    char originalTitle[100];
    char launchDate[100];
    int duration;
    char genre[100];
    char originalLanguage[100];
    char situation[100];
    float budget;
    char keywords[100][100];
    int numKeywords;
} Filme;

//método para retirar os primeiros espaços de uma string
char* trim(char* s) {

    static char str[100];
    int i = 0, j, k;
    while(s[i] == ' ') {

        i++;
    }

    for(j = i, k = 0; s[j] != '\0'; j++, k++) {

        str[k] = s[j];
    }

    str[k] = '\0';

    return str;
}

//imprimir
void printClass(Filme *filme) {

    printf("%s", filme->name);
    printf("%s", filme->originalTitle);
    printf(" %s",filme->launchDate);
    printf("%i", filme->duration);
    printf(" %s",filme->genre);
    printf(" %s",filme->originalLanguage);
    printf(" %s",filme->situation);
    printf(" %g",filme->budget);

    printf(" [");
    for(int i = 1; i <= filme->numKeywords; i++) {

        strcpy(filme->keywords[i], trim(filme->keywords[i]));

        if(i == 1) {

            printf("%s", trim(filme->keywords[i]));
        }else {

             printf(", %s", filme->keywords[i]);
        }
    }
    
    printf("]\n");
}

//método para remover tags
char *removeTag(char *html, char *texto) {
    
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

//método para retira parte da string
char* substr(const char *src, int m, int n) {

    int len = n - m;
 
    char *dest = (char*)malloc(sizeof(char) * (len + 1));
 
    for (int i = m; i < n && (*(src + i) != '\0'); i++) {

        *dest = *(src + i);
        dest++;
    }
 
    *dest = '\0';
 
    return dest - len;
}

//método para retirar um char
void removeChar(char * str, char charToRemmove) {
    
    int i, j;
    int len = strlen(str);
    for(i=0; i<len; i++) {

        if(str[i] == charToRemmove) {

            for(j=i; j<len; j++) {

                str[j] = str[j+1];
            }

            len--;
            i--;
        }
    }
}

//método para procurar e ler dentro do arquivo
void readClass(char *fileName, Filme *filme) {

    FILE *fp;
    char *line = NULL;
    size_t tam = 0;
    ssize_t read;
    
    char result[100];
    char prefix[100];

    strcpy(prefix, "/tmp/filmes/");
    strcat(prefix, fileName);

    fp = fopen(prefix, "r");
    if(fp == NULL) {

        printf("Arquivo Inexistente!\n");
        exit(EXIT_FAILURE);
    }

    while((read = getline(&line, &tam, fp)) != - 1) {

        //read name
        if(strstr(line, "<title>") != NULL) {

            strcpy(result, trim(line));
            strcpy(result, strtok(result, "(") + 7);
            strcpy(filme->name, result);
            //printf("%s\n", filme.name);
        }

        //read title
        if(strstr(line, "Título original") != NULL) {

            strcpy(result, trim(line));
            strcpy(filme->originalTitle, result);
            if(strlen(filme->originalTitle) < 2) {

                strcpy(filme->originalTitle, filme->name);
            }else {

                strcpy(result, strtok(result, "</p") + 48);
                strcpy(result, strtok(result, "</"));
                strcpy(filme->originalTitle, result);
            }
            //printf("%s\n", filme.originalTitle);
        }

        //read date
        if(strstr(line, "<span class=\"release\">") != NULL) {

            read = getline(&line, &tam, fp);
            strcpy(result, trim(line));
            strcpy(result, (strtok(result, "(")));
            strcpy(filme->launchDate, result);
            //printf("%s\n", filme.launchDate);
        }

        //read duration
        if(strstr(line, "runtime") != NULL) {

            char hourS[10];
            char minuteS[10];
            int runtime = 0;
            read = getline(&line, &tam, fp);
            read = getline(&line, &tam, fp);
            strcpy(result, trim(line));
            if(strstr(line, "h")) {

                strcpy(hourS, strtok(result, "h"));
                strcpy(minuteS, strtok(NULL, "m"));
                strcpy(minuteS, trim(minuteS));
                runtime = (atoi(hourS) * 60 + atoi(minuteS));
                filme->duration = runtime;
                //printf("%d\n", filme.duration);
            }else {

                strcpy(minuteS, strtok(result, "m"));
                strcpy(minuteS, trim(minuteS));
                runtime = atoi(minuteS);
                filme->duration = runtime;
                //printf("%d\n", filme.duration);
            }
            
        }

        //read genre
        if(strstr(line, "/genre/") != NULL) {

            removeTag(line, result);
            strcpy(result, trim(result));
            strcpy(filme->genre, result);
            //printf("%s\n", filme.genre);
        }

        //read language
        if(strstr(line, "Idioma") != NULL) {

            strcpy(result, trim(line));
            strcpy(result, result+47);
            strcpy(result, substr(result, 0, strlen(result)-5));
            strcpy(filme->originalLanguage, result);
            //printf("%s\n", filme.originalLanguage);
        }

        //read situation
        if(strstr(line, "<strong><bdi>Situação</bdi></strong>") != NULL) {

            removeTag(line, result);
            strcpy(result, trim(result));
            strcpy(result, result+11);
            strcpy(filme->situation, result);
            //printf("%s\n", filme.situation);

        }

        //read budget
        if(strstr(line, "Orçamento") != NULL) {
            
            float bud;
            strcpy(result, trim(line));
            if(strlen(result) == 1) {

                filme->budget = 0;
                //printf("%.le\n", filme.budget);
            }else {

                strcpy(result, result+41);
                strcpy(result, substr(result, 2, strlen(result)-5));
                removeChar(result, ',');
                bud = atof(result);
                filme->budget = bud;
                //printf("%.le\n", filme.budget);
            }
        }

        //read keywords
         if(strstr(line,"Palavras-chave") != NULL){

            int count = 0;
            int i = 1;

            while(true) {

                if(strstr(line, "</ul>") != NULL) {
                    
                    break;
                }

                if(strstr(line, "/keyword/") != NULL) {

                    count++;
                    strcpy(result, trim(line));
                    int pos = 0;

                    for(int j = 0; j <= strlen(result); j++) {
                        
                        if((result[j] == '\"') && (result[j+1] == '>')) {

                            pos = j + 2;
                        }
                    }

                    strcpy(result, substr(result, pos, strlen(result)-10));
                    strcpy(filme->keywords[count], trim(result));
                }

                read = getline(&line, &tam, fp);
                i++;
            }  

            filme->numKeywords = count;                
        }

        /*
        //title = name
        if(strlen(filme->originalTitle) < 2) {
            strcpy(filme->originalTitle, filme->name);
        }*/
    }

    fclose(fp);
    
    if(line) {
        
        free(line);   
    }
}

int main() {

    Filme filme;
    char fileName[100];

    scanf(" %[^\n]", fileName);
    while(!(fileName[0] == 'F' && fileName[1] == 'I' && fileName[2] == 'M')){
        
        readClass(fileName, &filme);
        printClass(&filme);
        
        scanf(" %[^\n]", fileName);
    }
    
    return 0;
}