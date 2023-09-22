#include <stdio.h>

int main()
{
    int x, cont, max = 0;
    
    printf(" Digite um valor para N: ");
    scanf ("%d", &max);
    
    for(x=1; x<=max; x++)
    {
        cont+=x;
    }
    printf(" SOMATÓRIO\n");
    printf(" A soma dos N primeiros inteiros é %d", cont);

    return(0);

}
