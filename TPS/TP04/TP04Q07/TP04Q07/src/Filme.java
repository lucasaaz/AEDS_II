import java.io.*;
import java.io.FileReader;

class HashIndiretoLista {
    Lista tabela[];
    int tamanho;

    public HashIndiretoLista(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }

    public int h(String elemento) {
        int soma = 0;
        for (char c : elemento.toCharArray()) {
            soma += c;
        }
        return soma % tamanho;
    }

    boolean pesquisar(String elemento) {
        int pos = h(elemento);
        return tabela[pos].pesquisar(elemento, h(elemento));
    }

    public void inserir(Filme elemento) {
        int pos = h(elemento.getTitle());
        tabela[pos].inserir(elemento);
    }
}

class Celula {
    public Filme elemento; 
    public Celula prox;

    Celula() {
        this.prox = null;
    }

    Celula(Filme elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    Celula(Filme elemento, Celula prox) {
        this.elemento = elemento;
        this.prox = prox;
    }
}

class Lista {
    private Celula primeiro;
    private Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public boolean pesquisar(String x, int pos) {
        boolean retorno = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento.getTitle().compareTo(x) == 0) {
                MyIO.println("Posicao: " + pos);
                retorno = true;
                i = ultimo;
            }
        }
        return retorno;
    }

    public void inserir(Filme elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }
}

public class Filme {
    private String name;
    private String originalTitle;

    public Filme() {
        name = "";
        originalTitle = "";
    }

    public Filme(String name, String originalTitle) {
        this.name = name;
        this.originalTitle = originalTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.originalTitle;
    }

    public Filme clone() {
        Filme clone = new Filme();
        clone.name = this.name;
        clone.originalTitle = this.originalTitle;
        return clone;
    }

    public void printClass() {
        MyIO.println(this.name + " " + this.originalTitle);
    }

    public String removeTags(String text) {
        String removed = "";
        int i = 0;
        while (i < text.length()) {
            if (text.contains("&amp;")) {
                if (text.charAt(i) == '<') {
                    i++;
                    while (text.charAt(i) != '>') {
                        i++;
                    }
                } else {
                    removed += text.charAt(i);
                }
            } else {
                if (text.charAt(i) == '<') {
                    i++;
                    while (text.charAt(i) != '>') {
                        i++;
                    }
                } else if (text.charAt(i) == '&') {
                    i++;
                    while (text.charAt(i) != ';') {
                        i++;
                    }
                } else {
                    removed += text.charAt(i);
                }
            }
            i++;
        }
        return removed;
    }

    public void parseName() {
        String line = getName();
        line = line.substring(4, line.indexOf(" ("));
        setName(line);
    }

    public void parseTitle() {
        String line = getTitle();
        if (line == null) {
            line = this.name;
        } else {
            line = line.substring(20);
        }
        setTitle(line);
    }

    public String searchString(String file, String search) {
        String result = null;
        try {
            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            result = br.readLine();
            while (result != null) {
                if (result.contains(search)) {
                    br.close();
                    return removeTags(result);
                }
                result = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void readClass(String fileName) {
        String file = "/tmp/filmes/" + fileName;
        String search = "";
        search = "<title>";
        this.name = searchString(file, search);
        parseName();
        search = "Título original";
        this.originalTitle = searchString(file, search);
        parseTitle();
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("utf-8");
        HashIndiretoLista movies = new HashIndiretoLista(21);
        String fileName;
        while (true) {
            fileName = MyIO.readLine();
            if (fileName.equals("FIM")) {
                break;
            }
            Filme movie = new Filme();
            movie.readClass(fileName);
            movies.inserir(movie);
        }
        String xName;
        while (true) {
            xName = MyIO.readLine();
            if (xName.equals("FIM")) {
                break;
            }
            MyIO.println("=> " + xName);
            if (!movies.pesquisar(xName)) {
                MyIO.println("NAO");
            }
        }
    }
}