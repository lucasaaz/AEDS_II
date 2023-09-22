import java.io.*;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class No {
    public Filme elemento;
    public No esq, dir;

    public No(Filme elemento) {
        this(elemento, null, null);
    }

    public No(Filme elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean pesquisar(String x) {
        MyIO.println(x);
        MyIO.print("=>raiz");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            MyIO.println(" NAO");
            resp = false;

        } else if (x.compareTo(i.elemento.getTitle()) == i.elemento.getTitle().compareTo(x)) {
            MyIO.println(" SIM");
            resp = true;

        } else if (x.compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x)) {
            MyIO.print(" esq");
            resp = pesquisar(x, i.esq);

        } else {
            MyIO.print(" dir");
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void inserir(Filme x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(Filme x, No i) throws Exception {
        if (i == null) {
            i = new No(x);

        } else if (x.getTitle().compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x.getTitle())) {
            i.esq = inserir(x, i.esq);

        } else if (x.getTitle().compareTo(i.elemento.getTitle()) > i.elemento.getTitle().compareTo(x.getTitle())) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(String x, No i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x)) {
            i.esq = remover(x, i.esq);

        } else if (x.compareTo(i.elemento.getTitle()) > i.elemento.getTitle().compareTo(x)) {
            i.dir = remover(x, i.dir);

        } else if (i.dir == null) {
            i = i.esq;

        } else if (i.esq == null) {
            i = i.dir;

        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    private No maiorEsq(No i, No j) {

        if (j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;

        } else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }
}

public class Filme {

    // declara??o dos atributos
    private String name;
    private String originalTitle;
    private Date launchDate;
    private int duration;
    private String genre;
    private String originalLanguage;
    private String situation;
    private float budget;
    private ArrayList<String> keywords;

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // construtor prim?rio
    public Filme() {

        name = "";
        originalTitle = "";
        launchDate = null;
        duration = 0;
        genre = "";
        originalLanguage = "";
        situation = "";
        budget = 0;
        keywords = new ArrayList<String>();
    }

    // construtor secund?rio
    public Filme(String name, String originalTitle, Date launchDate, int duration, String genre,
            String originalLanguage, String situation, float budget, ArrayList<String> keywords) {

        this.name = name;
        this.originalTitle = originalTitle;
        this.launchDate = launchDate;
        this.duration = duration;
        this.genre = genre;
        this.originalLanguage = originalLanguage;
        this.situation = situation;
        this.budget = budget;
        this.keywords = keywords;
    }

    // m?todos sets
    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    // m?todos gets
    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.originalTitle;
    }

    public Date getDate() {
        return this.launchDate;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getLanguage() {
        return this.originalLanguage;
    }

    public String getSituation() {
        return this.situation;
    }

    public float getBudget() {
        return this.budget;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords;
    }

    // m?todo para clonar
    public Filme clone() {

        Filme clone = new Filme();

        clone.name = this.name;
        clone.originalTitle = this.originalTitle;
        clone.launchDate = this.launchDate;
        clone.duration = this.duration;
        clone.genre = this.genre;
        clone.originalLanguage = this.originalLanguage;
        clone.situation = this.situation;
        clone.budget = this.budget;
        clone.keywords = this.keywords;

        return clone;
    }

    // m?todo para imprimir
    public void printClass() {

        MyIO.println(
                this.name + " " +
                        this.originalTitle + " " +
                        sdf.format(this.launchDate) + " " +
                        this.duration + " " +
                        this.genre + " " +
                        this.originalLanguage + " " +
                        this.situation + " " +
                        this.budget + " " +
                        this.keywords);
    }

    // m?todo para remover tags
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

    // m?todo para obter a posi??o do primeiro numero
    public int posPrimeiro(String s, int pos) {

        for (int i = pos; i < s.length(); i++) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

                return i;
            }
        }

        return -1;
    }

    public int posPrimeiro(String s) {

        return posPrimeiro(s, 0);
    }

    // m?todos para tratar os atributos
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

    public void parseGenre() {

        String line = getGenre();

        line = line.substring(6);

        setGenre(line);
    }

    public void parseLanguage() {

        String line = getLanguage();

        line = line.substring(18);

        setLanguage(line);
    }

    public void parseSituation() {

        String line = getSituation();

        line = line.substring(13);

        setSituation(line);
    }

    public String removeLetters(String value) {
        // Data declaration
        String result = "";

        for (int i = 0; i < value.length(); i++) {
            // If char is a number, a blank space, or a '.' (Used on convertBudget), will be
            // stored into "result"
            if ((value.charAt(i) >= 48 && value.charAt(i) <= 57) || value.charAt(i) == ' ' || value.charAt(i) == '.')
                result += value.charAt(i);
        }
        return result;
    }

    public void parseDuration(String file) {

        String line = searchDuration(file, "runtime");
        int hours = 0, minutes = 0;

        line = line.trim();
        int posH = line.indexOf("h");

        if (line.contains("m")) {
            if (posH != -1) {

                hours = Integer.parseInt(line.substring(posPrimeiro(line), posH));
            }

            minutes = Integer.parseInt(line.substring(posH == -1 ? 0 : posH + 2, line.length() - 1));

            setDuration((hours * 60) + minutes);
        } else {

            if (posH != -1) {

                hours = Integer.parseInt(line.substring(posPrimeiro(line), posH));
            }

            setDuration(hours);
        }

    }

    public void parseBudget(String file) {

        float result = 0;
        String line = searchBudget(file, "Orçamento");

        if (line.contains("-")) {

            setBudget(0);
        } else {

            line = line.substring(13);

            line = line.replace(",", "");

            result = Float.parseFloat(line);

            setBudget(result);
        }
    }

    public void parseDate(String file) throws ParseException {

        String line = searchDate(file, "<span class=\"release\">");
        line = line.trim();

        line = line.substring(0, 10);

        Date result = new SimpleDateFormat("dd/MM/yyyy").parse(line);

        setDate(result);
    }

    // m?todos para pesquisar no arquivo
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

    public String searchDuration(String file, String search) {

        String result = "";
        boolean found = false;

        try {

            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            br.readLine();
            while (!found) {

                result = br.readLine();
                if (result.contains(search)) {

                    br.readLine();
                    result = br.readLine();
                    result = removeTags(result);
                    found = true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String searchBudget(String file, String search) {

        String result = "";
        boolean found = false;

        try {

            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            result = br.readLine();
            while (!found) {

                result = br.readLine();
                if (result.contains(search)) {

                    result = removeTags(result);
                    found = true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return removeTags(result);
    }

    public String searchDate(String file, String search) {

        String result = "";
        boolean found = false;

        try {

            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            result = br.readLine();
            while (!found) {

                result = br.readLine();
                if (result.contains(search)) {

                    result = br.readLine();
                    result = removeTags(result);
                    found = true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return removeTags(result);
    }

    public void searchKey(String file) {

        String result = "";
        this.keywords = new ArrayList<String>();

        try {

            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            result = br.readLine();
            while (!result.contains("<section class=\"keywords right_column\">")) {

                result = br.readLine();
            }

            result = br.readLine();
            while (!result.contains("</ul>")) {

                result = br.readLine();
                if (result.contains("/keyword/")) {

                    result = removeTags(result);
                    result = result.trim();
                    this.keywords.add(result);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // m?todo para ler o arquivo
    public void readClass(String fileName) {

        String file = "/tmp/filmes/" + fileName;
        String search = "";

        // read name
        search = "<title>";
        this.name = searchString(file, search);
        parseName();

        // read title
        search = "Título original";
        this.originalTitle = searchString(file, search);
        parseTitle();

        // read date
        try {

            parseDate(file);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // read duration
        parseDuration(file);

        // read genre
        search = "/genre/";
        this.genre = searchString(file, search);
        parseGenre();

        // read language
        search = "Idioma original";
        this.originalLanguage = searchString(file, search);
        parseLanguage();

        // read situation
        search = "<strong><bdi>Situação</bdi></strong>";
        this.situation = searchString(file, search);
        parseSituation();

        // read budget
        parseBudget(file);

        // read keywords
        searchKey(file);
    }

    public static void main(String[] args) throws Exception {

        MyIO.setCharset("utf-8");

        ArvoreBinaria a = new ArvoreBinaria();
        String fileName;

        while (true) {

            fileName = MyIO.readLine();
            if (fileName.equals("FIM")) {

                break;
            }

            Filme movie = new Filme();
            movie.readClass(fileName);
            a.inserir(movie);
        }
       
        String lineAction = "";

        int n = MyIO.readInt();
        for(int i=0; i<n; i++) {

            lineAction = MyIO.readLine();
            String action = lineAction.substring(0, 1);
            String xName = lineAction.substring(2);
           
            if(action.compareTo("I") == 0){

                Filme movieN = new Filme();
                movieN.readClass(xName);
                a.inserir(movieN);
            }else if(action.compareTo("R") == 0){
                a.remover(xName);
            }
        }

        String titulo;
        while (true) {
            titulo = MyIO.readLine();
            if (titulo.equals("FIM")) {
                break;
            }
            a.pesquisar(titulo);
        }
    }
}