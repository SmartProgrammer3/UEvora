package trabalho;

public class Artistas implements java.io.Serializable {
    private String nomeArtista;
    private String tipoDeArte;
    private String localizacao;
    private boolean aAtuar;
    private String estado;

    public int artistID;


    public Artistas(String nomeArtista, String tipoDeArte, String localizacao, boolean aAtuar, String estado) {
        this.nomeArtista = nomeArtista;
        this.tipoDeArte = tipoDeArte;
        this.localizacao = localizacao;
        this.aAtuar = aAtuar;
        this.estado = estado;
    }


    public void setNome(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }
    public void setTipoDeArte(String tipoDeArte) {
        this.tipoDeArte = tipoDeArte;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setAAtuar(boolean aAtuar) {
        this.aAtuar = aAtuar;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setID(int artistID) {
        this.artistID = artistID;
    }

    public String getNome() {
        return nomeArtista;
    }

    public String getTipoDeArte() {
        return tipoDeArte;
    }


    public String getLocalizacao() {
        return localizacao;
    }

    public boolean getAAtuar() {
        return aAtuar;
    }

    public String getEstado() {
        return estado;
    }

    public int getID() {
        return artistID;
    }


}