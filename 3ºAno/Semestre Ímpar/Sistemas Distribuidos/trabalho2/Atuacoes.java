import java.sql.Date;

public class Atuacoes implements java.io.Serializable{
    private int iddoartista;
    private double latitude;
    private double longitude;
    private Date datadaatuacao;
    private int idatuacao;

    public Atuacoes() {
        super();
    }

    public void setidArtista(int artistID) {
        this.iddoartista = artistID;
    }

    public void setLati(double latitude) {
        this.latitude = latitude;
    }

    public void setLongi(double longitude) {
        this.longitude = longitude;
    }

    public void setDataAtuacao(Date datadaatuacao) {
        this.datadaatuacao = datadaatuacao;
    }

    public void setIdAtuacao(int idatuacao) {
        this.idatuacao = idatuacao;
    }

    public int getidArtista() {
        return iddoartista;
    }

    public double getLati() {
        return latitude;
    }

    public double getLongi() {
        return longitude;
    }

    public Date getDataAtuacao() {
        return datadaatuacao;
    }

    public int getIdAtuacao() {
        return idatuacao;
    }

}
