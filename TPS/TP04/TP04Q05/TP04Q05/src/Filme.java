import java.io.*;
import java.io.FileReader;

class Hash {
    Filme tabela[];
    int m1, m2, m, reserva;

    public Hash(int m1, int m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.m = m1 + m2;
        this.tabela = new Filme[this.m];
        for (int i = 0; i < m1; i++) {
            tabela[i] = null;
        }
    }

    public int h(String elemento) {
        int soma = 0;
        for (char c : elemento.toCharArray()) {
            soma += c;
        }
        return soma % m1;
    }

    public boolean inserir(Filme elemento) {
        boolean resp = false;
        if (elemento != null) {
            int pos = h(elemento.getTitle());
            if (tabela[pos] == null) {
                tabela[pos] = elemento;
                resp = true;
            } else if (reserva < m2) {
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;
        int pos = h(elemento);

        if (tabela[pos] != null) {
            if (tabela[pos].getTitle().compareTo(elemento) == 0) {
                MyIO.println("Posicao: " + pos);
                resp = true;
            } else {
                for (int i = 0; i < reserva; i++) {
                    if (tabela[m1 + i].getTitle().compareTo(elemento) == 0) {
                        MyIO.println("Posicao: " + (m1 + i));
                        resp = true;
                        i = reserva;
                    }
                }
            }
        }

        return resp;
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
        Hash movies = new Hash(21, 9);
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
            if(!movies.pesquisar(xName)) {
                MyIO.println("NAO");
            }
        }
    }
}