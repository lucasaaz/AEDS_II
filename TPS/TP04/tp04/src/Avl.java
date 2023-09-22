import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//atributos da class Filme
class Filme{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //utilizado para converter string to date
    private String name;
    private String title;
    private Date realeseDate;
    private Integer duration;
    private String genre;
    private String originalIdiom;
    private String situation;
    private float budget; // (orçamento)
    private ArrayList<String> keyWord;


    //construtor primario (valor inicial)
    public Filme(){
        name = "";
        title = "";
        realeseDate = null;
        duration = 0;
        genre = "";
        originalIdiom = "";
        situation = "";
        budget = 0;
        keyWord = null;
    }

    //construtor secundario
    public Filme(String name, String title, Date realeseDate, Integer duration, String genre, String originalIdiom, String situation, float budget,ArrayList<String> keyWord){
        this.name = name;
        this.title = title;
        this.realeseDate = realeseDate;
        this.duration = duration;
        this.genre = genre;
        this.originalIdiom = originalIdiom;
        this.situation = situation;
        this.budget = budget;
        this.keyWord = keyWord; 
    }

    //metodos para setar(set) e retornar(get) cada atributo de filme
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setRealeseDate(Date realeseDate){
        this.realeseDate = realeseDate;
    }

    public Date getRealeseDate(){ 
        return realeseDate;
    }

    public void setDuration(Integer duration){
        this.duration = duration;
    }

    public Integer getDuration(){
        return duration;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getGenre(){
        return genre;
    }

    public void setOriginalIdiom(String originalIdiom){
        this.originalIdiom = originalIdiom;
    }

    public String getOriginalIdiom(){
        return originalIdiom;
    }

    public void setSituation(String situation){
        this.situation = situation;
    }

    public String getSituation(){
        return situation;
    }

    public void setBudget(float budget){
        this.budget = budget;
    }

    public float getBudget(){
        return budget;
    }

    public void setKeyWord(ArrayList<String> keyWord){
        this.keyWord = keyWord;
    }

    public ArrayList<String> getKeyWord(){
        return keyWord;
    }

    //clonar a classe
    public Filme clone(){
        Filme film = new Filme();
        film.name = this.name;
        film.title = this.title;
        film.duration = this.duration;
        film.realeseDate = this.realeseDate;
        film.genre = this.genre;
        film.originalIdiom = this.originalIdiom;
        film.situation = this.situation;
        film.budget = this.budget;
        film.keyWord = this.keyWord;
        return film;
    }

    //imprimir a classe
    public void imprimirClass(){
        System.out.println(getName() + " " + getTitle() + " " + sdf.format(getRealeseDate()) + " " + getDuration() + " " + getGenre() + " " + getOriginalIdiom() + " " +
        getSituation() + " " + getBudget() + " " + getKeyWord());
        //System.out.println(getTitle());
    }


    //remover as tags do html
    public static String removeTag(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){ //o valor de i deve ser menor que o tamanho da linha
            if(line.charAt(i) == '<'){ // se na posição i houver o sinal de abrir tag ele passa para a proxima posição
                while(line.charAt(i) != '>'){
                    i++;
                }
            }else if(line.charAt(i) == '&'){
                while(line.charAt(i) != ';') i++;
            }else{
                resp += line.charAt(i); // atribui a resp a frase/palavra sem as tags
            }
        }     
        //System.out.println(resp);  
        return resp;
    }

    // identificar o fim do pub.in
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); 
    }

    //passar numero para inteiro 
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ 
                resp += line.charAt(i);
            } else { 
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //transforma a string em  inteiro
    }

    //converter string to float
    public Float isFloat(String line){
        String resp = "";
        float r  =  0;
        for(int i = 0; i < line.length(); i++){
            if((line.charAt(i) >= '0' && line.charAt(i) <= '9') || line.charAt(i) == '.'){ //procura por numeros na string
                resp += line.charAt(i); //armazena os numero em uma string resposta
            }else if(line.charAt(i) == '-'){
                resp = "0";
            }
        }
        r = Float.parseFloat(resp); //passa string para float e retorna
        return r;
    }

    //tratar name
    public String tratarName(String line){
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '_'){ //remove os underlines presentes na string
                line = line.replaceAll("_", "");
            }else{
                i = line.length();
            }
        }
        
        return line;
    }

    //tratar Situation
    public String tratatSituation(String line){
        line = line.replaceAll("Situação","");
        return line.trim();
    }

    //tratar idioma
    public String tratarIdioma(String line){
        line = line.replaceAll("Idioma original", "");
        return line.trim();
    }

    //ler name
    public void lerName(String film)throws ParseException{
        String line;
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));

            line = br.readLine();

            //setar o nome da serie
            while(!line.contains("h2 class")){ //enquanto não encontrar h2 class o arquivo continua sendo lido
               line = br.readLine();
            }
            line = br.readLine().trim(); //tira os espaços em branco da linha
            line = removeTag(line); //remove as tags e deixa só o nome da serie
            setName(tratarName(line));

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }

    }

    //ler release date
    public void lerDate(String film)throws ParseException{
        String line;

        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));

            //setar releaseDate
            line = br.readLine();
            while(!line.contains("class=\"release\"")){//enquanto não encontrar a class release o arquivo continua sendo lido
                line = br.readLine();
            }   
            line = br.readLine().trim(); //remover espaços em branco
            setRealeseDate(sdf.parse(line));
            //System.out.println(line.split(" ")[0]);

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    //ler genero
    public void lerGenre(String film)throws ParseException{
        String line;

        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));

            //setar genre
            line = br.readLine();
            while(!line.contains("class=\"genres\"")){//enquanto não encontrar a classe genre o arquivo continua sendo lido
                line = br.readLine();
            }
            line = br.readLine();
            line = br.readLine().trim();//remover espaços em branco
            setGenre(removeTag(line));
            //System.out.println(removeTag(line));

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerDuration(String film)throws ParseException{
        String line;

        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));

            //setar duration
            line = br.readLine();
            while(!line.contains("class=\"runtime\"")){//enquanto não encontrar a classe genre o arquivo continua sendo lido
                line = br.readLine();
            }
            line = br.readLine();
            line = br.readLine().trim();
            line = removeTag(line);
            String time = line;
            String horas = "";
            String min = "";

            if(time.length() > 3){
                horas = time.substring(0,1); //armazenar numero de horas em uma string
                int h = Integer.parseInt(horas); //passar string horas para int
                min = time.substring(3,5); //armazenar numero de min em uma string
                int m = justInt(min); //passar string min para int
                setDuration((h * 60) + m); // calacular o valor de horas
            }else{ // caso o filme tenha só min tratar somente min
                min = time.substring(0,2);
                int m = justInt(min); 
                setDuration(m);
            }

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerTitle(String film)throws ParseException{
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));
            String line = br.readLine() ;

            //setar title
            String titulo = null;
            while(line != null){
                if(line.contains("Título original")){ // ler linha até Título original
                    titulo = removeTag(line.replace("Título original", "").trim()); 
                    break;
                }
                line = br.readLine();
            }

            if(titulo == null){ //se o titulo for null, retorna o nome do filme
                this.title = name;
            }else{
                this.title = titulo.substring(1);
            }
            //System.out.println(removeTag(line));

            //fechar arquivo
            br.close();
            //tratar exceções 
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        }catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerSituation(String film)throws ParseException{
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));
            String line = br.readLine() ;

           //setar situation
           while(!(line = br.readLine()).contains("Situação</bdi>")); //enquanto não encontrar a tag situação o arquivo continua sendo lido
           line = tratatSituation(line);
           setSituation(removeTag(line));
           //System.out.println(removeTag(line));

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        }catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerIdiom(String film)throws ParseException{
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));
            String line = br.readLine() ;

           //setar original idiom
           while(!(line = br.readLine()).contains("Idioma original")); //enquanto não encontrar a string Idioma original o arquivo continua sendo lido
           line = tratarIdioma(line);
           setOriginalIdiom(removeTag(line));
           //System.out.println(removeTag(line));

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerBudget(String film)throws ParseException{
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));
            String line = br.readLine() ;

            //setar budget
            //line = br.readLine();
            while(!(line = br.readLine()).contains("Orçamento</bdi>")); //enquanto não encontrar a tag Orçamento o arquivo continua sendo lido
            //line = br.readLine().trim();
            line = removeTag(line); 
            line = line.replaceAll("Orçamento","");
            line = line.replace("$","");  //remove o cifrão de orçamento
                        
            setBudget(isFloat(line));
            //System.out.println(isFloat(line));

            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }

    public void lerKey(String film)throws ParseException{
        try{
            BufferedReader br = new BufferedReader(new FileReader("/tmp/filmes/" + film));

            //setar keywords
            this.keyWord = new ArrayList<String>();

            String line = br.readLine();
            while(!line.contains("Palavras-chave")) //ler linha até Palavras-chave
                line = br.readLine();

            line = br.readLine();
            while(!line.contains("</ul>")) { //ler linha até fechar a tag ul
                line = br.readLine();
                if(line.contains("/keyword/")){ 
                    line = removeTag(line).trim();//remover espaços em branco
                    this.keyWord.add(line);
                }
            }


            //fechar arquivo
            br.close();
            //tratar exceções
        }catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + film + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + film + "'");
        }
    }
    
    //ler os atributos de um html
    public void ler(String film)throws ParseException{
        //chamar os metodos de leitura
        lerName(film);
        lerDate(film);
        lerGenre(film);
        lerDuration(film);
        lerTitle(film);
        lerSituation(film);
        lerIdiom(film);
        lerBudget(film);
        lerKey(film);

    }
}

class No {
    public Filme elemento;
    public No esq, dir;
    public int nivel;

    public No(Filme elemento) {
        this(elemento, null, null, 1);
    }

    public No(Filme elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }

    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }
}

class AVL {
    private No raiz;
    public int comp = 0;

    public AVL() {
        raiz = null;
    }

    public boolean pesquisar(String x) {
        MyIO.println(x);
        MyIO.print("raiz");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            MyIO.println(" NAO");
            resp = false;
            comp++;

        } else if (x.compareTo(i.elemento.getTitle()) == i.elemento.getTitle().compareTo(x)) {
            MyIO.println(" SIM");
            resp = true;
            comp++;

        } else if (x.compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x)) {
            MyIO.print(" esq");
            comp++;
            resp = pesquisar(x, i.esq);

        } else {
            MyIO.print(" dir");
            resp = pesquisar(x, i.dir);
            comp++;
        }
        return resp;
    }

    public void inserir(Filme x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(Filme x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
            comp++;
            
        } else if (x.getTitle().compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x.getTitle())) {
            i.esq = inserir(x, i.esq);
            comp++;

        } else if (x.getTitle().compareTo(i.elemento.getTitle()) > i.elemento.getTitle().compareTo(x.getTitle())) {
            i.dir = inserir(x, i.dir);
            comp++;

        } else {
            throw new Exception("Erro ao inserir!");

        }
        return balancear(i);
    }

    public void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(String x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(x)) {
            i.esq = remover(x, i.esq);
            comp++;

        } else if (x.compareTo(i.elemento.getTitle()) > i.elemento.getTitle().compareTo(x)) {
            i.dir = remover(x, i.dir);
            comp++;

        } else if (i.dir == null) {
            i = i.esq;
            comp++;

        } else if (i.esq == null) {
            i = i.dir;
            comp++;

        } else {
            i.esq = maiorEsq(i, i.esq);
            comp++;
        }

        return balancear(i);
    }

    private No maiorEsq(No i, No j) {
        if (j.dir == null) {
            i.elemento = j.elemento; 
            j = j.esq; 
            comp++;

        } else {
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    private No balancear(No no) throws Exception {
        if (no != null) {
            int fator = No.getNivel(no.dir) - No.getNivel(no.esq);
            comp++;

            if (Math.abs(fator) <= 1) {
                no.setNivel();
                comp++;

            } else if (fator == 2) {
                int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);
                comp++;

                if (fatorFilhoDir == -1) {
                    no.dir = rotacionarDir(no.dir);
                    comp++;

                }
                no = rotacionarEsq(no);

            } else if (fator == -2) {
                int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);
                comp++;

                if (fatorFilhoEsq == 1) {
                    no.esq = rotacionarEsq(no.esq);
                    comp++;

                }
                no = rotacionarDir(no);

            } else {
                throw new Exception("Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
            }
        }
        return no;
    }

    private No rotacionarDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;
        no.setNivel();
        noEsq.setNivel(); 

        return noEsq;
    }

    private No rotacionarEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.setNivel();
        noDir.setNivel();
        return noDir;
    }
}

public class Avl {
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("utf-8");
        long start = System.currentTimeMillis();
        Filme film;
        AVL a = new AVL();
        String line;

        while (true) {
            line = MyIO.readLine();
            if (line.equals("FIM")) {
                break;
            }

            film = new Filme();
            film.ler(line);
            a.inserir(film);
        }
        
        String lerLine = "";

        String tam = "";
        tam = MyIO.readLine();
        int n = Integer.parseInt(tam);

        for(int i = 0; i < n; i++){
            lerLine = MyIO.readLine();
            String linha = lerLine.substring(0,1);
            String nome = lerLine.substring(2);

            if(linha.compareTo("I") == 0){
                Filme movie = new Filme();
                movie.ler(nome);
                a.inserir(movie);
            }else if(linha.compareTo("R") == 0){
                a.remover(nome);
            }

        }

        String pesq = "";
        while(true){
            pesq = MyIO.readLine();
            if(pesq.equals("FIM")){
                break;
            }

            a.pesquisar(pesq);
        }

        long elapsed = System.currentTimeMillis() - start;

        FileWriter arq = new FileWriter("712433_avl.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        gravarArq.println("712433\t" + elapsed + "ms\t" + a.comp);

        arq.close();
    }
}