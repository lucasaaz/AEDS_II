import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.io.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

class Filme{
   private String nome;
   private String tituloOriginal;
   private Date dataDeLancamento;
   private int duracao;
   private String genero;
   private String idiomaOriginal;
   private String situacao;
   private float orcamento;
   private String[] palavrasChave = new String[1000];

   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   
   public Filme(){
      
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
    
    
   
   //clonagem
    public Filme clone(){
        Filme resp = new Filme();
        resp.nome = this.nome;

        return resp;
    }
    
    //Imprimir
     public void imprimir(){
        MyIO.println("" + nome + " " + tituloOriginal + " " + dataDeLancamento + " " + duracao + " " + genero +  " " + idiomaOriginal + " " + situacao + " " + orcamento);
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
    
    

   //get set NOME
   public String getNome(){
      return nome;
   }
   public void setNome (String nome){
      this.nome = nome;
   }
   //get set Titulo Original
   public String getTituloOriginal(){
      return tituloOriginal;
   }
   public void setTituloOriginal(String tituloOriginal){
      this.tituloOriginal = tituloOriginal;
   }
   //get set Data De Lancamento
   public Date getDataDeLancamento(){
      return dataDeLancamento; 
   }
   public void setDataDeLancamento(Date dataDeLancamento){
      this.dataDeLancamento = dataDeLancamento;
   }
   //get set Duracao
   public int getDuracao(){
      return duracao;
   }   
   public void setDuracao(int duracao){
      this.duracao = duracao;
   }
   //get set Genero
   public String getGenero(){
      return genero;
   }   
   public void setGenero(String genero){
      this.genero = genero;
   }
   //get set Idioma Original
   public String getIdiomaOriginal(){
      return idiomaOriginal;
   }   
   public void setIdiomaOriginal(String idiomaOriginal){
      this.idiomaOriginal = idiomaOriginal;
   }
   //get set Situacao
   public String getSituacao(){
      return situacao;
   }
   public void setSituacao(String situacao){
      this.situacao = situacao;
   }
   //get set Orcamento   
   public float getOrcamento(){
      return orcamento;
   }
   public void setOrcamento(float orcamento){
      this.orcamento = orcamento;
   }
   //get set Orcamento 
   public String[] getPalavrasChave() {
	  return palavrasChave;
   }
   public void setPalavrasChave(String[] palavrasChave) {
	  this.palavrasChave = palavrasChave;
   }
 

}



public class TP02Q1 {

    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }
    
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Filme Filme = new Filme();
        Filme[] array = new Filme[100];
        String str = "";
        //pesquisa(array);

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            Filme.ler("/tmp/filme/" + str);
            Filme.imprimir();
            str = MyIO.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


