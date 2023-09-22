import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


class Film {
    // Attributes
    private String nome;
    private String tituloOriginal;
    private Date dataDeLancamento;
    private String duracao;
    private String genero;
    private String idiomaOriginal;
    private String situacao;
    private float orcamento;
    private String[] palavrasChave;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    // Constructors
    public Film() {
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
    public Film(String nome, String tituloOriginal, Date dataDeLancamento, 
                String duracao, String genero, String idiomaOriginal,
                String situacao, float orcamento) {
                    
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

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
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

    public float getOrcamento() {
        return this.orcamento;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }


    /* Clone */

    public Film clone(){
        Film clone = new Film();

        clone.nome = this.nome;
        clone.tituloOriginal = this.tituloOriginal;
        clone.dataDeLancamento = this.dataDeLancamento;
        clone.duracao = this.duracao;
        clone.genero = this.genero;
        clone.idiomaOriginal = this.idiomaOriginal;
        clone.situacao = this.situacao;
        clone.orcamento = this.orcamento;
        clone.palavrasChave = this.palavrasChave;

        return clone;
    }


    /* Entrada e saída */

    /**
     * Mostra na tela os valores de cada atributo, separados por espaço.
    */
    public void imprimir() {
        String strSerie = (
            nome + " " + tituloOriginal + " " + dataDeLancamento + " " + duracao
            + " " + genero + " " + idiomaOriginal + " "
            + situacao + " " + palavrasChave);
        MyIO.println(trimSpace(strSerie) + " " + orcamento);
    }

    public String trimSpace(String s) {
        return s.replaceAll("(\\s){2,}", " ");
    }


    /**
     * Lê de um arquivo HTML as linhas contendo os valores que serão designados
     * a cada atributo.
     * @param path Caminho do arquivo no SO.
     * @throws Exception
     */
    public void ler(String fileName) throws Exception {
        // definir caminho do arquivo
        String path = "/tmp/filmes/" + fileName;

        // criar objeto BufferedReader para leitura do arquivo
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        // atribuir título
        this.setNome(parseTitle(fileName));

        // procurar tabela que contem o restante dos dados
        while (!br.readLine().contains("infobox_v2"));

        // buscar as linhas que precedem dado procurado, fazer parse e passar
        // valor ao respectivo atributo
        // Film release date
        while(!br.readLine().contains("\"Data de Lançamento\""));
        this.dataDeLancamento = sdf.parse(br.readLine().trim());

        while (!br.readLine().contains("Gênero"));
        this.setGenero(parseHtml(br.readLine()));

        while (!br.readLine().contains("Duração"));
        this.setDuracao(parseHtml(br.readLine()));

        while (!br.readLine().contains("Idioma original"));
        this.setIdiomaOriginal(parseHtml(br.readLine()));

        while (!br.readLine().contains("Situação"));
        this.setSituacao(parseHtml(br.readLine()));

        while (!br.readLine().contains("Orçamento"));
        this.setOrcamento(parseHtmlInt(br.readLine()));

        while (!br.readLine().contains("Palavras Chave"));
        this.setPalavrasChave(parseHtml(br.readLine()));

        // fechar arquivo
        br.close();
    }


    /* Métodos auxiliares */

    public String parseHtml(String line) {
        // limpar tags e referências de caracteres html com regex
        return line.replaceAll("(<[^>]*>)|(&.*?;)", "");
    }

    public int parseHtmlInt(String line) {
        // limpar tags e referências de caracteres html com regex
        return Integer.parseInt(line.replaceAll("(<[^>]*>)|(\\d+)|(.*)", "$2"));
    }

    public String parseTitle(String fileName) {
        String title = "";
        int n = fileName.length() - 5;

        for (int i = 0; i < n; i++)
        {
            if (fileName.charAt(i) == '_') {
                title += ' ';
            } else {
                title += fileName.charAt(i);
            }
        }
        return title;
    }
}


/* Classe de estrutura de dados */

public class ListaFilm {
    // declarar arranjo e tamanho
    private Film lista[];
    private int size;

    // definir variáveis de contagem
    private int cmpCount;
    private int swpCount;

    // construtor-padrão
    public ListaFilm() {
        lista = new Film[100];
        size = 0;
        cmpCount = 0;
        swpCount = 0;
    }

    /**
     * Insere um objeto Filmes no início da lista.
     * @param filme Objeto a inserir.
     * @throws Exception
     */
    public void inserirInicio(Film filme) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        for (int i = size; i > 0; i--) {
            // clonar objeto da posição anterior e copiar para a nova
            Film temp = lista[i-1].clone();
            lista[i] = temp;
        }

        // inserir objeto no início e incrementar tamanho da lista
        lista[0] = filme;
        size++;
    }


    /**
     * Insere um objeto Filmes no fim da lista.
     * @param filme Objeto a inserir.
     * @throws Exception
     */
    public void inserirFim(Film filme) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        // inserir objeto no fim e incrementar tamanho da lista
        lista[size] = filme;
        size++;
    }


    /**
     * Insere um objeto Filmes na lista em um índice específico.
     * @param filme Objeto a inserir.
     * @param index Posição para inserir.
     * @throws Exception
     */
    public void inserir(Film filme, int index) throws Exception {
        // verificar se tamanho e índice são válidos
        if (size >= lista.length || index < 0 || index > size) {
            throw new Exception("ERRO: tamanho excedido");
        }

        // deslocar objetos para o final, a partir da posição informada
        for (int i = size; i > index; i--) {
            Film temp = lista[i-1].clone();
            lista[i] = temp;
        }

        // inserir objeto na posição e incrementar tamanho da lista
        lista[index] = filme;
        size++;
    }


    /**
     * Remove um objeto do início da lista.
     * @throws Exception
     */
    public void removerInicio() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        // guardar objeto removido e decrementar tamanho
        Film removed = lista[0].clone();
        size--;

        // deslocar objetos restantes para o início da lista
        for (int i = 0; i < size; i++) {
            Film temp = lista[i+1].clone();
            lista[i] = temp;
        }

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Remove um objeto do fim da lista.
     * @throws Exception
     */
    public void removerFim() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        // guardar objeto removido e decrementar tamanho
        Film removed = lista[--size].clone();

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Remove um objeto da lista de um índice específico.
     * @throws Exception
     */
    public void remover(int index) throws Exception {
        // verificar se tamanho e índice são válidos
        if (size == 0 || index < 0 || index >= size) {
            throw new Exception("ERRO: lista vazia ou posição inválida");
        }

        // guardar objeto removido e decrementar tamanho
        Film removed = lista[index].clone();
        size--;

        // deslocar para a esquerda os objetos à direita da posição
        for (int i = index; i < size; i++) {
            Film temp = lista[i+1].clone();
            lista[i] = temp;
        }

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Método para trocar dois elementos de posição em uma lista.
     * @param i Índice do primeiro elemento.
     * @param j Índice do segundo elemento.
     */
    public void swap(int i, int j) {
        Film tmp = lista[i];
        lista[i] = lista[j];
        lista[j] = tmp;
    }


    /**
     * Método para ordenação de lista baseado no algoritmo de ordenação por
     * seleção.
     */
    public void sort() {
        // reiniciar contadores, se aplicável
        if (cmpCount != 0) this.cmpCount = 0;
        if (swpCount != 0) this.swpCount = 0;

        if (size > 1) {
            for (int i = 0; i < (size - 1); i++) {
                int menor = i;
                for (int j = (i + 1); j < size; j++) {
                    String s1 = lista[menor].getTituloOriginal().trim();
                    String s2 = lista[j].getTituloOriginal().trim();
                    String t1 = lista[menor].getNome();
                    String t2 = lista[j].getNome();

                    // contar comparação
                    cmpCount++;
                    int cmpElements = s1.compareTo(s2);

                    if (cmpElements > 0) {
                        menor = j;
                    } else if (cmpElements == 0) {
                        // se elementos forem iguais, ordenar pelo nome do filme
                        if (t1.compareTo(t2) > 0) {
                            menor = j;
                        }
                    }
                }

                if (menor != i) {
                    // contar movimentação e trocar
                    swpCount++;
                    swap(menor, i);
                }
            }
        }
    }


    /**
     * Mostra todos os atributos de cada objeto da lista.
     */
    public void mostrar() {
        for (int i = 0; i < size; i++) {
            lista[i].imprimir();
        }
    }


    /* Arquivo log */

    /**
     * Método para criar um arquivo de log com matrícula, total de comparações,
     * total de movimentações e tempo de execução, em segundos, do algoritmo de
     * ordenação utilizado.
     * @param t Tempo de execução em segundos.
     * @throws Exception
     */
    public void logFile(double t) throws Exception {
        FileWriter fw = new FileWriter("712433_sequencial.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("712433\t" + cmpCount + "\t" + swpCount + "\t" + t);
        writer.close();
    }


    /* Main */

    public static void main(String[] args) {
        // definir charset
        MyIO.setCharset("UTF-8");

        // definir dados para contagem de tempo de execução
        double start;
        double end;
        double runtime;

        // definir dados
        ListaFilm lista = new ListaFilm();

        // ler nome do arquivo
        String line = MyIO.readLine();

        // criar e inserir objetos na lista
        while (!line.equals("FIM")) {
            Film filme = new Film();
            try {
                // tentar ler arquivo
                filme.ler(line);
            } catch (Exception e) {
                MyIO.println("Erro ao ler arquivo `" + line + "`");
            }

            try {
                lista.inserirFim(filme);
            } catch (Exception e) {
                e.printStackTrace();
            }

            line = MyIO.readLine();
        }

        // iniciar contagem de tempo
        start = new Date().getTime();

        // realizar ordenação
        lista.sort();

        // encerrar contagem de tempo e calcular tempo de execução
        end = new Date().getTime();
        runtime = (end - start) / 1000.0;

        // toString() de todos os objetos da lista
        lista.mostrar();

        // registrar em arquivo log
        try {
            lista.logFile(runtime);
        } catch (Exception e) {
            MyIO.println("Erro ao criar arquivo de log");
        }
    }
}