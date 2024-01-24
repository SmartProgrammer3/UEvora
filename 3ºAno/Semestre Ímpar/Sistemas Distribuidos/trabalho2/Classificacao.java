public class Classificacao implements java.io.Serializable {
    private int iddoartista;
    private String username ;
    private int rating;
    private int idclassificacao;


    public Classificacao(){
        super();
    }

    public Classificacao( int iddoartista, String username, int rating) {
        this.iddoartista = iddoartista;
        this.username = username;
        this.rating = rating;
    } 

    public void setIDArt(int iddoartista) {
        this.iddoartista = iddoartista;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setIDClassificacao(int idclassificacao) {
        this.idclassificacao = idclassificacao;
    }

    public int getIDArt() {
        return iddoartista;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public int getIDClassificacao() {
        return idclassificacao;
    }
}
