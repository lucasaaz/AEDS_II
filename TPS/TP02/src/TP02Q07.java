import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

class Film{
    String nome;
    String tituloOriginal;
    Date dataDeLancamento;
    int duracao;
    String genero;
    String idiomaOriginal;
    String situacao;
    float orcamento;
    String[] palavrasChave;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public Date getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(Date dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Float orcamento) {
        this.orcamento = orcamento;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }


    public Film clone(){
        Film cloned = new Film();

        cloned.nome = this.nome;
        cloned.tituloOriginal = this.tituloOriginal;
        cloned.dataDeLancamento = this.dataDeLancamento;
        cloned.duracao = this.duracao;
        cloned.genero = this.genero;
        cloned.idiomaOriginal = this.idiomaOriginal;
        cloned.situacao = this.situacao;
        cloned.orcamento = this.orcamento;
        cloned.palavrasChave = this.palavrasChave;

        return cloned;
    }

    String removeTags(String line){
        String newline = "";
        int i=0;
        while(i<line.length()){
            if(line.charAt(i) == '<'){
                i++; 
                while(line.charAt(i) != '>') 
                i++;             
            } else {
                if(line.charAt(i) == '&'){
                    i+=5;
                } else{
                    newline += line.charAt(i);
                }  
                
            }
            i++;
        }

        return newline;
    }



    public int myParseInt(String str){
        String newline = "";
        int i=0;
        int num = 0;

        //System.out.println(str);
        while (i<str.length() && str.charAt(i) != ' ') {

                newline += str.charAt(i);
                i++;

        }
        //System.out.print(newline);
        num = Integer.parseInt(newline);
        return num;
    }



    public void ler(String nomeArquivo) throws Exception{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));

        BufferedReader br = new BufferedReader(isr);

        String line = "";

        while(!br.readLine().contains("title ott"));
        br.readLine();
        this.nome = removeTags(br.readLine());

        while(!br.readLine().contains("Título original"));
        this.tituloOriginal = removeTags(br.readLine());

        while(!br.readLine().contains("\"release\""));
        this.dataDeLancamento = sdf.parse(removeTags(br.readLine()));

        while(!br.readLine().contains("Duração"));
        this.duracao = myParseInt(removeTags(br.readLine()));

        while(!br.readLine().contains("genres"));
        this.genero = removeTags(br.readLine());

        while(!br.readLine().contains("Idioma original"));
        this.idiomaOriginal = removeTags(br.readLine());

        while(!br.readLine().contains("Situação"));
        this.situacao = removeTags(br.readLine());

        while(!br.readLine().contains("Orçamento"));
        //System.out.print("" + removeTags(br.readLine()));
        this.orcamento = myParseInt(removeTags(br.readLine()));
        
        line = "";
        while(!br.readLine().contains("Palavras-chave"));
        //System.out.print("" + removeTags(br.readLine()));
        this.palavrasChave = removeTags(line).trim().split("  ");

        br.close();
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(nome);
        sb.append(" ").append(tituloOriginal);
        sb.append(" ").append(sdf.format(getDataDeLancamento()));
        sb.append(" ").append(duracao);
        sb.append(" ").append(genero);
        sb.append(" ").append(idiomaOriginal);
        sb.append(" ").append(situacao);
        sb.append(" ").append(orcamento);
        sb.append(" ").append(palavrasChave == null ? "[]" : Arrays.asList(palavrasChave).toString());
        return sb.toString();
    }

    public void imprimir(){
        System.out.println(this.toString());
    }

}

class Fila {
	private Film[] array;
	private int frente;
    private int tras;
    private int n;

	/**
	 * Construtor da classe.
	 */
	public Fila() {
		this(6);
	}

	/**
	 * Construtor da classe.
	 */
	public Fila(int tamanho) {
		array = new Film[tamanho];
		frente = tras = 0;
        n = 0;
	}

    
	public void enfileira(Film item) throws Exception{
        if(((tras + 1) % array.length) == frente){
            desenfileira();
            
        }
        array[tras] = item;
        tras = (tras + 1) % array.length;
        n++;

    }

    public Film desenfileira() throws Exception{
        //validar remoção
        if(frente == tras)
            throw new Exception("ERRO!");

        Film resp = array[frente];
        frente = (frente + 1) % array.length;
        n--;
        return resp;
        
    }

    public static String limpaString(String s,int posicao){
        String resp = "";
        for(int i = posicao; i < s.length(); i++){
            resp += s.charAt(i);
        }

        return resp;
    }

    /*public static int myParseInt (String s){
        String resp = "";
        for(int i = 3; i < 5; i++){
            resp += s.charAt(i);
        }
        
        return Integer.parseInt(resp);
    }*/

    public void imprimir2(){
        for(int i = frente; i < tras; i++){
            array[i % array.length].imprimir();
        }
    }

    public boolean vazia() {
        return frente == tras; 
     }

    public int calculaMedia(){
        int media = 0;
        double soma = 0;
        int i = frente;
        /*for(i = frente; i < tras; i++){
            soma += array[i % array.length].getDuracao();
            
        }*/
        while(i != tras){   
            soma += array[i].getDuracao();
            i = (i+1) % array.length;
        }
        media = (int)(Math.ceil(soma/(double)n));
        return media;
    }

}

public class TP02Q07 {
    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }

    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Film filme = new Film();
        Fila fila = new Fila(6);
        String str = "";
        int i = 0;
        int declaracoes;

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            filme = new Film();
            filme.ler("/tmp/filmes/" + str);
            fila.enfileira(filme);
            System.out.println(fila.calculaMedia());
            //fila.imprimir2();
            i++;
            //filme.imprimir();
            str = MyIO.readLine();
            }
           
            declaracoes = MyIO.readInt();
            
            for(int j = 0; j < declaracoes; j++){
                str = MyIO.readLine();
                if(str.charAt(0) == 'I'){
                    filme = new Film();
                    filme.ler("/tmp/filmes/" + fila.limpaString(str,2));
                    fila.enfileira(filme);
                    System.out.println(fila.calculaMedia());
                } else if(str.charAt(0) == 'R'){
                    filme = new Film();
                    filme = fila.desenfileira();
                    
                } 

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
