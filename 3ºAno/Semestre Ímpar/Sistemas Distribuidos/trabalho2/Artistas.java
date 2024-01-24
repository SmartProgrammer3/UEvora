public class Artistas implements java.io.Serializable {
    private String nomeArtista;
    private String tipoDeArte;
    private double latitude;
    private double longitude;
    private boolean aAtuar;
    private String estado;

    public int artistID;


    public Artistas() {
        super();
    }


    public void setNome(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }
    public void setTipoDeArte(String tipoDeArte) {
        this.tipoDeArte = tipoDeArte;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public double getLongitude() {
        return longitude;
    }
    
    public double getLatitude() {
        return latitude;
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