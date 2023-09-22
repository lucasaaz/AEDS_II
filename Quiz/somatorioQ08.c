#include <stdio.h>
#include <math.h>

float somatorioPA(int a, int b, int n)
{
    float an;
    float somatorio;
    
    an = (n - 1)*b + a;
    
    somatorio = n*(b+an)/2;
    
    return(somatorio);
    
}

int main()
{
    int        a1;
    int        b1;
    int        n1;
    float somatorio;
    
    printf(" Valor inicial: ");
    scanf ("%d", &a1);
    printf(" Razao: ");
    scanf ("%d", &b1);
    printf(" Total de valores: ");
    scanf ("%d", &n1);    

    somatorio = somatorioPA(a1,b1,n1);
    
    printf("\n Resultado final da PA de valor inicial %d, razao %d e %d valores = %f", a1, b1, n1, somatorio);

    return 0;
}

