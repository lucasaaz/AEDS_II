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
    public char elemento;
    public No esq, dir;
    public No2 outro;
    
    public No(char elemento) {
        this.elemento = elemento;
        this.dir = this.esq = null;
        this.outro = null;
    }
    
    public No(char elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

class No2{
    public Filme elemento;
    public No2 esq, dir;

    No2(Filme elemento){
        this.elemento = elemento;
        this.esq = this.dir = null;
    }

    No2(Filme elemento, No2 esq, No2 dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;
    
    public ArvoreBinaria() throws Exception{
        raiz = null;
        raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');
        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');
        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');
        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');
        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');
        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');
        inserir('Y');
        inserir('Q');

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

    public void inserir(Filme x) throws Exception {
        inserir(x,raiz);
    }
    
    public void inserir(Filme x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir!!");
    
        } else if (x.getTitle().charAt(0) < i.elemento) {
            inserir(x, i.esq);
    
        } else if (x.getTitle().charAt(0) > i.elemento) {
            inserir(x, i.dir);
    
        } else {
            i.outro = inserir(x, i.outro);
        }
    
    }
    
    private No2 inserir(Filme x, No2 i) throws Exception {
        if (i == null) {
            i = new No2(x);
            //comp++;
    
        } else if (x.getTitle().compareTo(i.elemento.getTitle()) < 0) {
            i.esq = inserir(x, i.esq);
            //comp++;
    
        } else if (x.getTitle().compareTo(i.elemento.getTitle()) > 0) {
            i.dir = inserir(x, i.dir);
            //comp++;
    
        } else {
            throw new Exception("Erro ao inserir!");
        }
    
        return i;
    }

    public void pesquisar(String elemento){
        boolean resp;
        System.out.println("=> " + elemento);
        System.out.print("raiz ");
        resp = pesquisar(raiz, elemento);
        if(resp){
            System.out.println(" SIM");
        }else if(!resp){
            System.out.println(" NAO");
        }
    }

    public boolean pesquisar(No no, String x) {
        boolean resp = false;

        if (no != null) {
            resp = pesquisarArvore2(no.outro, x);
            if (resp == false) {
                System.out.print(" ESQ ");

                resp = pesquisar(no.esq, x);
            }
            if (resp == false) {
                System.out.print(" DIR ");

                resp = pesquisar(no.dir, x);
            }

        }

        return resp;

    }

    public boolean pesquisarArvore2(No2 no, String x) {
        boolean resp;
        if (no == null) {

            resp = false;

        } else if (x.compareTo(no.elemento.getTitle()) < no.elemento.getTitle().compareTo(x)) {
            System.out.print("esq ");
            resp = pesquisarArvore2(no.esq, x);

        } else if (x.compareTo(no.elemento.getTitle()) > no.elemento.getTitle().compareTo(x)) {
            System.out.print("dir ");
            resp = pesquisarArvore2(no.dir, x);

        } else {
            resp = true;
        }
        return resp;
    }

    public void mostrar(){
        mostrar(raiz);
    }

    public void mostrar(No i){
        if( i != null){
            mostrar(i.esq);
            mostrar(i.outro);
            mostrar(i.dir);
        }
    }

    public void mostrar(No2 i){
        if(i != null){
            mostrar(i.esq);
            mostrar(i.dir);
        }
    }
}

public class ArvoreArv{
    public static void main(String[] args) throws Exception{
        MyIO.setCharset("utf-8");
        long start = System.currentTimeMillis();
        Filme film;
        ArvoreBinaria a = new ArvoreBinaria();
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

        FileWriter arq = new FileWriter("712433_arvoreArvore.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        
        gravarArq.println("712433\t" + elapsed + "ms\t" );

        arq.close();
    }
}