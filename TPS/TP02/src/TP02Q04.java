import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


class Film {
    // Attributes
    private String nome;
    private String tituloOriginal;
    private Date dataDeLancamento;
    private int duracao;
    private String genero;
    private String idiomaOriginal;
    private String situacao;
    private float orcamento;
    private String[] palavrasChave;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Constructors
    public Film() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * @param nome
     * @param tituloOriginal
     * @param dataDeLancamento
     * @param duracao
     * @param genero
     * @param idiomaOriginal
     * @param situacao
     * @param orcamento
     */
    public Film(String nome, String tituloOriginal, Date dataDeLancamento, Integer duracao, String genero, String idiomaOriginal, String situacao, Float orcamento) {
        this.nome = nome;
        this.tituloOriginal = tituloOriginal;
        this.dataDeLancamento = dataDeLancamento;
        this.duracao = duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavrasChave = null;
    }

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


    /**
     * @param fileName
     */
    public void ler(String fileName){
        // Getting the right path for each read file
        String path = "./filmes/" + fileName;

        // Method that will split chunks of the read HTML and will assign the value to each Film's attribute
        splittingString(path);
    }

    private void splittingString(String path){
        // Data declaration
        String line = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))) {

            // Film name
            while(!reader.readLine().contains("title ott"));
            while(!reader.readLine().contains("h2"));
            this.nome = removeTags(reader.readLine().trim());

            // Film release date
            while(!reader.readLine().contains("\"release\""));
            this.dataDeLancamento = sdf.parse(removeTags(reader.readLine().trim()));

            // Film genre
            while(!reader.readLine().contains("genres"));
                // In this case, will use "line" because the last readLine will have the content that we want
            while(!(line = reader.readLine()).contains("<a href"));
            this.genero = removeTags(line).trim();

            // Film duration
            while(!reader.readLine().contains("runtime"));
            reader.readLine(); // Needed because an empty line was found
            this.duracao = hoursToMinutes(reader.readLine().trim());

            // Film original title (if there is) & situation
            this.idiomaOriginal = this.nome;
            while( !(line = reader.readLine()).contains("Situação</bdi>") ) {
                if(line.contains("Título original")){
                    this.tituloOriginal = removeTags(line.replace("Título original", " ")).trim();
                }
            }
            this.situacao = removeTags(line.replace("Situação", " ")).trim();

            // Film original language
            while( !(line = reader.readLine()).contains("Idioma original</bdi>") );
            this.idiomaOriginal = removeTags(line.replace("Idioma original", " ")).trim();

            // Film budget
            while( !(line = reader.readLine()).contains("Orçamento</bdi>") );
            String aux = removeTags(line.replace("Orçamento", " ")).trim();
            this.orcamento = (aux.equals("-")) ? 0.0F : convertBudget(aux);

            // Film key-words
            line = "";
            while( !reader.readLine().contains("Palavras-chave</bdi>") );
            while( !(line += reader.readLine().trim() + " ").contains("</ul>") );
            if(!line.contains("Nenhuma palavra-chave foi adicionada")){
                palavrasChave = removeTags(line).trim().split("  ");
            }


        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("O arquivo não pode ser lido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives a line that contains an HTML content and removes its tags
     * @param line
     * @return
     */
    private String removeTags(String line){
        // Data declaration
        String resp = "";
        int i = 0;

        /*
           The main idea here is to check if the char is equals to '<', if it's, it means that an HTML tag has opened
           So, CAN'T read anything until the tag is closed, '>' is found.

           It's also checking if any HTML special character (&....;) or if any "()" is found
           IF found, don't read anything until it has ended.
         */
        while (i < line.length()) {
            if (line.charAt(i) == '<') {
                i++;
                while (line.charAt(i) != '>') i++;
            }else {
                resp += line.charAt(i);
            }
            i++;
        }
        // Returning cleaned line
        return resp.replace("&nbsp;", "");
    }

    /**
     * Receives a String that contains hours, and convert it to minutes (Integer)
     * @param value
     * @return
     */
    private int hoursToMinutes(String value){
        // Data declaration
        int result = 0, minutes = 0;

        String[] splitValue = value.split(" ");
        if(splitValue.length > 1) {
            int hour = Integer.parseInt(removeLetters(splitValue[0]));
            minutes = Integer.parseInt(removeLetters(splitValue[1]));
            result = (60 * hour) + minutes;
        } else {
            if(splitValue[0].contains("h")){
                minutes = Integer.parseInt(removeLetters(splitValue[0]))*60;
            } else {
                minutes = Integer.parseInt(removeLetters(splitValue[0]));
            }
            result = minutes;
        }
        return result;
    }

    /**
     * Receives a String that contains hours, and leave only the numbers (ex: 1h 49m = 1 49)
     * @param value
     * @return
     */
    private String removeLetters(String value){
        // Data declaration
        String result = "";

        for(int i = 0; i < value.length(); i++){
            // If char is a number, a blank space, or a '.' (Used on convertBudget), will be stored into "result"
            if( (value.charAt(i) >= 48 && value.charAt(i) <= 57) || value.charAt(i) == ' ' || value.charAt(i) == '.')
                result += value.charAt(i);
        }
        return result;
    }

    /**
     * Receives a String that contains a FLOAT number, and converts it to a FLOAT number
     * (PS: It's necessary to remove few characters because String has ',' on it)
     * @param value
     * @return
     */
    private Float convertBudget(String value){
        return Float.parseFloat(removeLetters(value));
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





/*

public class TP02Q04 {

    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }

     public static boolean pesqBin(Film[] array, String str,int i){
        boolean resp = false;
        int dir = (i - 1), esq = 0, meio;
  
        while (esq <= dir){
           meio = (esq + dir) / 2;
           if(str.equals( array[meio].getNome())){
              resp = true;
              esq = dir + 1;
           } else if (str.compareTo(array[meio].getNome())>0) {
              esq = meio + 1;
           } else {
              dir = meio - 1;
           }
        }
        return resp;
     }
    
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Film filme = new Film();
        Film[] array = new Film[100];
        String str = "";
        int i = 0;

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            array[i] = new Film();
            array[i].ler("/tmp/filmes/" + str);
            i++;
            str = MyIO.readLine();
            }
            str = MyIO.readLine();
            while(isFim(str) != true){
            if (pesqBin(array,str,i)){
                System.out.print("SIM\n");
            } else {
                System.out.print("NÃO\n");
            }
            str = MyIO.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

*/