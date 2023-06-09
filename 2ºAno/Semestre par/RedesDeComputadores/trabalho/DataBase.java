import java.util.HashMap;

public class DataBase extends servidor{
    private HashMap<String, String> logins;
    private HashMap<String, Boolean> presencas;
    private HashMap<String, String> perguntasRespostas;
    private HashMap<String, String> arquivos;

    public DataBase() {
        logins = new HashMap<>();
        presencas = new HashMap<>();
        perguntasRespostas = new HashMap<>();
        arquivos = new HashMap<>();
    }

    public void registrar(String numeroAluno, String senha) {
        logins.put(numeroAluno, senha);
    }

    public boolean autenticar(String numeroAluno, String senha) {
        String senhaRegistrada = logins.get(numeroAluno);
        return senhaRegistrada != null && senhaRegistrada.equals(senha);
    }

    public void adicionarPresenca(String numeroAluno) {
        presencas.put(numeroAluno, true);
    }

    public void removerPresenca(String numeroAluno) {
        presencas.remove(numeroAluno);
    }

    public boolean consultarPresenca(String numeroAluno) {
        Boolean presenca = presencas.get(numeroAluno);
        return presenca != null && presenca;
    }

    public void adicionarPerguntaResposta(String pergunta, String resposta) {
        perguntasRespostas.put(pergunta, resposta);
    }

    public void removerPerguntaResposta(String pergunta) {
        perguntasRespostas.remove(pergunta);
    }

    public String consultarResposta(String pergunta) {
        return perguntasRespostas.get(pergunta);
    }

    public void adicionarArquivo(String nomeArquivo, String conteudo) {
        arquivos.put(nomeArquivo, conteudo);
    }

    public void removerArquivo(String nomeArquivo) {
        arquivos.remove(nomeArquivo);
    }

    public String consultarConteudoArquivo(String nomeArquivo) {
        return arquivos.get(nomeArquivo);
    }
}


