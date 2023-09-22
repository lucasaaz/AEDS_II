import java.util.Scanner;

class No{

    char elemento;
    No esq;
    No dir;

    No(){}
    No(char elemento){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class arvore{
    
    No raiz;

    arvore(){
        this.raiz = null;
    }

    public void inserir(char elemento) throws Exception{
        raiz = inserir(elemento, raiz);
    }
    public No inserir(char elemento, No i) throws Exception{
        if(i == null){
            i = new No(elemento);
        }else if( elemento < i.elemento){
            i.esq = inserir(elemento, i.esq);
        }else if( elemento > i.elemento){
            i.dir = inserir(elemento, i.dir);
        }else{
            throw new Exception("Erro!");
        }
        return i;
    }

    public void INFIXA(){
        INFIXA(raiz);
        System.out.print("\n");
    }
    public void INFIXA(No i){
        if( i != null){
            INFIXA(i.esq);
            System.out.print(" " + i.elemento);
            INFIXA(i.dir);
        }
    }

    public void PREFIXA(){
        PREFIXA(raiz);
        System.out.print("\n");
    }
    public void PREFIXA(No i){
        if( i != null){
            System.out.print(" " + i.elemento);
            PREFIXA(i.esq);
            PREFIXA(i.dir);
        }
    }

    public void POSFIXA(){
        POSFIXA(raiz);
        System.out.print("\n");
    }
    public void POSFIXA(No i){
        if( i != null){
            POSFIXA(i.esq);
            POSFIXA(i.dir);
            System.out.print(" " + i.elemento);
        }
    }

    public boolean pesquisar(char elemento){
        return pesquisar(elemento, raiz);
    }
    public boolean pesquisar(char elemento, No i){
        boolean resp = false;

        if( i == null){
            resp = false;
        }else{
            if( elemento == i.elemento){
                resp = true;
            }else if(elemento < i.elemento){
                resp = pesquisar(elemento, i.esq);
            }else if(elemento > i.elemento){
                resp = pesquisar(elemento, i.dir);
            }
        }
        return resp;
    }
}

/**
 * TP04Q09
 */
public class TP04Q09 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        arvore av = new arvore();

        int fim = 11;

        while(fim-- > 0){

            String resp = sc.nextLine();
    
            if( resp.equals("INFIXA")){
                av.INFIXA();
            }else if( resp.charAt(0) == 'I'){
                av.inserir(resp.charAt(2));
            }else if( resp.equals("PREFIXA")){
                av.PREFIXA();
            }else if( resp.equals("POSFIXA")){
                av.POSFIXA();
            }else if( resp.charAt(0) == 'P'){
                boolean b = av.pesquisar(resp.charAt(2));
                if(b == true){
                    System.out.println(" " + resp.charAt(2) + " existe");
                }else{
                    System.out.println(" " + resp.charAt(2) + " nao existe");
                }
            }
        }

    }
    
}