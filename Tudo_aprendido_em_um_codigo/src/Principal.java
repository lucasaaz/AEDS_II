/**
 * Celula
 */
class Celula{
    int elemento;
    Celula prox;

    Celula(){
        this(0);
    }
    Celula(int x){
        this.elemento = x;
        this.prox = null;
    }
}

/**
 * CelulaDupla
 */
class CelulaDupla{
    int elemento;
    CelulaDupla prox;
    CelulaDupla ant;

    CelulaDupla(){
        this(0);
    }
    CelulaDupla(int x){
        this.elemento = x;
        this.ant  = null;
        this.prox = null;
    }
}

/**
 * Pilha - Simples
 */
class Pilha{
    Celula topo;

    Pilha(){
        this.topo = null;
    }

    public void inserir(int x){
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }
    public int remover() throws Exception{
        if(topo == null){
            throw new Exception("Erro!");
        }else{
            int removido = topo.elemento;
            Celula tmp = topo;
            topo = tmp.prox;
            tmp = tmp.prox = null;
            return removido;
        }
    }
    public void mostrar(){
        System.out.print("\nTopo\n");
        for(Celula i = topo; i != null; i = i.prox){
            System.out.print("  " + i.elemento + "\n");
        }
    }
}

/**
 * Pilha - Flexivel
 */
class PilhaFlexivel{
    CelulaDupla topo;

    PilhaFlexivel(){
        this.topo = null;
    }

    public void inserir(int x){
        if(topo == null){
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = topo;
            topo = tmp;
            tmp = null;   
        }else{
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = topo;
            topo.ant = tmp;
            topo = tmp;
            tmp = null;
        }
    }
    public int remover() throws Exception{
        if(topo == null){
            throw new Exception("Erro!");
        }else{
            int removido = topo.elemento;
            topo = topo.prox;
            topo.ant.prox = null;
            topo.ant = null;
            return removido;
        }
    }
    public void mostrar(){
        System.out.print("\nTopo\n");
        for(CelulaDupla i = topo; i != null; i = i.prox){
            System.out.print("  " + i.elemento + "\n");
        }
    }
}

/**
 * Fila - Simples
 */
class Fila{
    Celula primeiro;
    Celula ultimo;

    Fila(){
        this.primeiro = new Celula();
        this.ultimo = primeiro;
    }

    public void inserir(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }
    public int remover() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Eror!");
        }else{
            Celula tmp = primeiro.prox;
            int removido = tmp.elemento;
            primeiro.prox = tmp.prox;
            tmp = tmp.prox = null;
            return removido;
        }
    }
    public void mostrar(){
        System.out.print("\n[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.print("]\n");
    }
}

/**
 * Fila - Flexivel
 */
class FilaFlexivel{
    CelulaDupla primeiro;
    CelulaDupla ultimo;

    FilaFlexivel(){
        this.primeiro = new CelulaDupla();
        this.ultimo = primeiro;
    }

    public void inserir(int x){
        ultimo.prox = new CelulaDupla(x);
        ultimo = ultimo.prox;
        ultimo.ant = ultimo.ant;
    }
    public int remover() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Eror!");
        }else{
            CelulaDupla tmp = primeiro.prox;
            int removido = tmp.elemento;
            primeiro.prox = tmp.prox;
            tmp.prox.ant = tmp.ant;
            tmp = tmp.prox = null;
            return removido;
        }
    }
    public void mostrar(){
        System.out.print("\n[ ");
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.print("]\n");
    }
}

/**
 * Lista - Simples
 */
class Lista{
    Celula primeiro;
    Celula ultimo;

    Lista(){
        this.primeiro = new Celula();
        this.ultimo = primeiro;
    }

    public void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }
        tmp = null;
    }
    public void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }
    public void inserir(int x, int pos){
        Celula i = primeiro;
        for(int y = 0; y < pos; y++, i = i.prox);
        Celula tmp = new Celula(x);
        tmp.prox = i.prox;
        i.prox = tmp;
        tmp = null;
    }
    public int removerInicio() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");

        Celula tmp = primeiro.prox;
        int removido = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp = tmp.prox = null;
        return removido;
    }
    public int removerFim() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");

        Celula i;
        for(i = primeiro; i.prox.prox != null; i = i.prox);
        int removido = ultimo.elemento;
        ultimo = i;
        ultimo.prox = null;
        return removido;
    }
    public int remover(int pos) throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");
        
        Celula i = primeiro;
        for(int y = 0; y < pos; y++, i = i.prox);
        Celula tmp = i.prox;
        int removido = tmp.elemento;
        i.prox = tmp.prox;
        tmp = tmp.prox = null;
        return removido;
    }
    public void mostrar(){
        System.out.print("\n[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.print("]\n");
    }
}

/**
 * Lista - Flexivel
 */
class ListaFlexivel{
    CelulaDupla primeiro;
    CelulaDupla ultimo;

    ListaFlexivel(){
        this.primeiro = new CelulaDupla();
        this.ultimo = primeiro;
    }

    public void inserirInicio(int x){
        if(primeiro == ultimo){
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = primeiro.prox;
            primeiro.prox = tmp;
            tmp.ant = primeiro;
            ultimo = tmp;
            tmp = null;
        }else{
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = primeiro.prox;
            tmp.prox.ant = tmp;
            primeiro.prox = tmp;
            tmp.ant = primeiro;
            tmp = null;
        }
    }
    public void inserirFim(int x){
        ultimo.prox = new CelulaDupla(x);
        ultimo = ultimo.prox;
        ultimo.ant = ultimo.ant;
    }
    public void inserir(int x, int pos){
        CelulaDupla i = primeiro;
        for(int y = 0; y < pos; y++, i = i.prox);
        if(i == ultimo){
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp.ant = i;
            ultimo = tmp;
            tmp = null;
        }else{
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.prox = i.prox;
            tmp.prox.ant = tmp;
            i.prox = tmp;
            tmp.ant = i;
            tmp = null;
        }
    }
    public int removerInicio() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");

        CelulaDupla tmp = primeiro.prox;
        int removido = tmp.elemento;
        primeiro.prox = tmp.prox;
        tmp.prox.ant = primeiro;
        tmp = tmp.prox = tmp.ant = null;
        return removido;
    }
    public int removerFim() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");

        int removido = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return removido;
    }
    public int remover(int pos) throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");
        
        CelulaDupla i = primeiro;
        for(int y = 0; y < pos; y++, i = i.prox);
        if(i.prox == ultimo){
            CelulaDupla tmp = i.prox;
            int removido = tmp.elemento;
            i.prox = tmp.prox;
            ultimo = ultimo.ant;
            tmp = tmp.prox = tmp.ant = null;
            return removido;
        }else{
            CelulaDupla tmp = i.prox;
            int removido = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox.ant = i;
            tmp = tmp.prox = tmp.ant = null;
            return removido;
        }
    }
    public void mostrar(){
        System.out.print("\n[ ");
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.print("]\n");
    }
}


/**
 * Principal
 */
public class Principal {

    public static void main(String[] args) throws Exception {
        
        //Lista-Simples
        System.out.print("\n\n --Lista Simples-- \n");
        Lista lista = new Lista();
        lista.inserirInicio(10);
        lista.inserirInicio(0);
        lista.inserirFim(15);
        lista.inserirFim(25);
        lista.mostrar();
        lista.inserir(5, 1);
        lista.inserir(20, 4);
        lista.mostrar();
        lista.removerFim();
        lista.removerInicio();
        lista.mostrar();
        lista.remover(0);
        lista.remover(2);
        lista.mostrar();

        //Lista-Flexivel
        System.out.print("\n\n --Lista Flexivel-- \n");
        ListaFlexivel listafFlexivel = new ListaFlexivel();
        listafFlexivel.inserirInicio(30);
        listafFlexivel.inserirInicio(20);
        listafFlexivel.inserirFim(40);
        listafFlexivel.inserirFim(50);
        listafFlexivel.mostrar();
        listafFlexivel.inserir(10, 0);
        listafFlexivel.inserir(60, 5);
        listafFlexivel.mostrar();
        listafFlexivel.removerFim();
        listafFlexivel.removerInicio();
        listafFlexivel.mostrar();
        listafFlexivel.remover(1);
        listafFlexivel.remover(2);
        listafFlexivel.mostrar();

        //Pilha-Simples
        System.out.print("\n\n --Pilha Simples-- \n");
        Pilha pilha = new Pilha();
        pilha.inserir(2);
        pilha.inserir(4);
        pilha.inserir(6);
        pilha.inserir(8);
        pilha.mostrar();
        pilha.remover();
        pilha.remover();
        pilha.remover();
        pilha.mostrar();

        //Pilha-Simples
        System.out.print("\n\n --Pilha Flexivel-- \n");
        PilhaFlexivel pilhaFlexivel = new PilhaFlexivel();
        pilhaFlexivel.inserir(1);
        pilhaFlexivel.inserir(3);
        pilhaFlexivel.inserir(5);
        pilhaFlexivel.inserir(7);
        pilhaFlexivel.inserir(9);
        pilhaFlexivel.mostrar();
        pilhaFlexivel.remover();
        pilhaFlexivel.remover();
        pilhaFlexivel.remover();
        pilhaFlexivel.mostrar();

        //Fila-Simples
        System.out.print("\n\n --Fila Simples-- \n");
        Fila fila = new Fila();
        fila.inserir(50);
        fila.inserir(40);
        fila.inserir(30);
        fila.inserir(20);
        fila.mostrar();
        fila.remover();
        fila.remover();
        fila.remover();
        fila.mostrar();

        //Fila-Flexivel
        System.out.print("\n\n --Fila Flexivel-- \n");
        FilaFlexivel filafFlexivel = new FilaFlexivel();
        filafFlexivel.inserir(10);
        filafFlexivel.inserir(20);
        filafFlexivel.inserir(30);
        filafFlexivel.inserir(40);
        filafFlexivel.inserir(50);
        filafFlexivel.mostrar();
        filafFlexivel.remover();
        filafFlexivel.remover();
        filafFlexivel.remover();
        filafFlexivel.mostrar();
    }
}