import java.util.HashMap;

public class DataBase extends Servidor{
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
}


