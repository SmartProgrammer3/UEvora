import java.sql.Date;

public class Donativo implements java.io.Serializable{
    private int idartista;  
    private int valorDoDonativo;
    private String userEnvioDonativo; //QUEM enviou o donativo
    private int iddonativo;
    private Date dataDonativo;
    
    public Donativo(){
        super();
    }

    public void setidartista(int idartista) {
        this.idartista = idartista;
    }

    public void setValorDonativo(int valorDoDonativo) {
        this.valorDoDonativo = valorDoDonativo;
    }

    public void setUserEnvioDonativo(String userEnvioDonativo) {
        this.userEnvioDonativo = userEnvioDonativo;
    }

    public void setDataDonativo(Date dataDonativo) {
        this.dataDonativo = dataDonativo;
    }

    public void setDonativoID(int iddonativo) {
        this.iddonativo = iddonativo;
    }


    public int getidartista() {
        return idartista;
    }

    public int getValorDonativo() {
        return valorDoDonativo;
    }

    public String getUserEnvioDonativo() {
        return userEnvioDonativo;
    }

    public int getDonativoID() {
        return iddonativo;
    }

    public Date getDataDonativo() {
        return dataDonativo;
    }

}