import java.util.Scanner;

class Main {

    public static class No {
        public char elemento;
        public No esq, dir;

        public No(char elemento) {
            this(elemento, null, null);
        }

        public No(char elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    public static class ArvoreBinaria {
        private No raiz;

        public ArvoreBinaria() {
            raiz = null;
        }

        public boolean pesquisar(char x) {
            return pesquisar(x, raiz);
        }

        private boolean pesquisar(char x, No i) {
            boolean resp;
            if (i == null) {
                resp = false;

            } else if (x == i.elemento) {
                resp = true;

            } else if (x < i.elemento) {
                resp = pesquisar(x, i.esq);

            } else {
                resp = pesquisar(x, i.dir);
            }
            return resp;
        }

        public void caminharCentral() {
            caminharCentral(raiz);
            System.out.println("");
        }

        private void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq);
                System.out.print(i.elemento + " ");
                caminharCentral(i.dir);
            }
        }

        public void caminharPre() {
            caminharPre(raiz);
            System.out.println("");
        }

        private void caminharPre(No i) {
            if (i != null) {
                System.out.print(i.elemento + " ");
                caminharPre(i.esq);
                caminharPre(i.dir);
            }
        }

        public void caminharPos() {
            caminharPos(raiz);
            System.out.println("");
        }

        private void caminharPos(No i) {
            if (i != null) {
                caminharPos(i.esq);
                caminharPos(i.dir);
                System.out.print(i.elemento + " ");
            }
        }

        public void inserir(char x) throws Exception {
            raiz = inserir(x, raiz);
        }

        private No inserir(char x, No i) throws Exception {
            if (i == null) {
                i = new No(x);

            } else if (x < i.elemento) {
                i.esq = inserir(x, i.esq);

            } else if (x > i.elemento) {
                i.dir = inserir(x, i.dir);

            } else {
                throw new Exception("Erro ao inserir!");
            }

            return i;
        }
    }

    public static void main(String[] args) throws Exception {
        ArvoreBinaria a = new ArvoreBinaria();
        String input;
        char operation, result;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            input = sc.nextLine();
            operation = input.charAt(0);
            result = input.charAt(2);

            if (operation == 'I' && input.charAt(1) == ' ') {
                a.inserir(result);
            } else if (operation == 'P' && input.charAt(1) == ' ') {
                if (a.pesquisar(result)) {
                    System.out.println(result + " existe");
                } else {
                    System.out.println("nao existe");
                }
            } else if(operation == 'I' && result == 'F') {
                a.caminharCentral();
            } else if(operation == 'P' && result == 'E') {
                a.caminharPre();
            } else if(operation == 'P' && result == 'S') {
                a.caminharPos();
            }
        }
        sc.close();
    }
}