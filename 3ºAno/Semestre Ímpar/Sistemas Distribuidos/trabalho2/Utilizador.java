public class Utilizador implements java.io.Serializable{
    protected String nomeUtilizador;
    protected String passwordUtilizador;
    protected String emailUtilizador;
    protected String tipoUtilizador;

    public Utilizador(){
        super();
    }
    
    public Utilizador(String nomeUtilizador,String passwordUtilizador, String emailUtilizador, String tipoUtilizador){
        this.nomeUtilizador = nomeUtilizador;
        this.passwordUtilizador = passwordUtilizador;
        this.emailUtilizador = emailUtilizador;
        this.tipoUtilizador = tipoUtilizador;
    }

    public void setUtilizador(String nomeUtilizador){
        this.nomeUtilizador = nomeUtilizador;
    }

    public void setPasse(String passwordUtilizador){
        this.passwordUtilizador = passwordUtilizador;
    }

    public void setEmail(String emailUtilizador){
        this.emailUtilizador = emailUtilizador;
    }

    public void setTipo(String tipoUtilizador){
        this.tipoUtilizador = tipoUtilizador;
    }


    public String getUtilizador(){
        return nomeUtilizador;
    }

    public String getPasse(){
        return passwordUtilizador;
    }

    public String getEmail(){
        return emailUtilizador;
    }


    public String getTipo(){
        return tipoUtilizador;
    }

}