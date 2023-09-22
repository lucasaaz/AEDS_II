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

class NoAN {
    public boolean cor;
    public Filme elemento;
    public NoAN esq, dir;

    public NoAN() {
        //this(-1);
    }

    public NoAN(Filme elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(Filme elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(Filme elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class AlvinegraA{
    private NoAN raiz;

    public AlvinegraA() {
        raiz = null;
    }

    public boolean pesquisar(String elemento) {
        MyIO.println(elemento);
        MyIO.print("raiz");
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(String elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            MyIO.println(" NAO");
            resp = false;
        } else if (elemento.compareTo(i.elemento.getTitle()) == 0) {
            MyIO.println(" SIM");
            resp = true;
        } else if (elemento.compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(elemento)) {
            MyIO.print(" esq");
            resp = pesquisar(elemento, i.esq);
        } else {
            MyIO.print(" dir");
            resp = pesquisar(elemento, i.dir);
        }
        return resp;
    }

    public void inserir(Filme elemento) throws Exception {
        if (raiz == null) {
            raiz = new NoAN(elemento);

        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getTitle().compareTo(raiz.elemento.getTitle()) < raiz.elemento.getTitle().compareTo(elemento.getTitle())) {
                raiz.esq = new NoAN(elemento);
            } else {
                raiz.dir = new NoAN(elemento);
            }

        } else if (raiz.esq == null) {
            if (elemento.getTitle().compareTo(raiz.elemento.getTitle()) < raiz.elemento.getTitle().compareTo(elemento.getTitle())) {
                raiz.esq = new NoAN(elemento);

            } else if (elemento.getTitle().compareTo(raiz.dir.elemento.getTitle()) < raiz.dir.elemento.getTitle().compareTo(elemento.getTitle())) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } else if (raiz.dir == null) {
            if (elemento.getTitle().compareTo(raiz.elemento.getTitle()) > raiz.elemento.getTitle().compareTo(elemento.getTitle())) {
                raiz.dir = new NoAN(elemento);

            } else if (elemento.getTitle().compareTo(raiz.esq.elemento.getTitle()) > raiz.esq.elemento.getTitle().compareTo(elemento.getTitle())) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (pai.cor == true) {
            if (pai.elemento.getTitle().compareTo(avo.elemento.getTitle()) > avo.elemento.getTitle().compareTo(pai.elemento.getTitle())) { 
                if (i.elemento.getTitle().compareTo(pai.elemento.getTitle()) > pai.elemento.getTitle().compareTo(i.elemento.getTitle())) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { 
                if (i.elemento.getTitle().compareTo(pai.elemento.getTitle()) < pai.elemento.getTitle().compareTo(i.elemento.getTitle())) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getTitle().compareTo(bisavo.elemento.getTitle()) < bisavo.elemento.getTitle().compareTo(avo.elemento.getTitle())) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } 
    }

    private void inserir(Filme elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.getTitle().compareTo(pai.elemento.getTitle()) < pai.elemento.getTitle().compareTo(elemento.getTitle())) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getTitle().compareTo(i.elemento.getTitle()) < i.elemento.getTitle().compareTo(elemento.getTitle())) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getTitle().compareTo(i.elemento.getTitle()) > i.elemento.getTitle().compareTo(elemento.getTitle())) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

public class Alvinegra {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("utf-8");
        long start = System.currentTimeMillis();
        Filme film;
        AlvinegraA a = new AlvinegraA();
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
        
        gravarArq.println("712433\t" + elapsed + "ms\t" );

        arq.close();
    
    }
}