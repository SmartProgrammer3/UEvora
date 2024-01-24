import java.util.*;


public interface remoteObject extends java.rmi.Remote {
    public boolean registou(Utilizador utilizador) throws java.rmi.RemoteException;
    public List<Utilizador> filtroUtilizadores(String fields) throws java.rmi.RemoteException;
    public int enviarArtista(Artistas artista) throws java.rmi.RemoteException;
    public List<Artistas> filtro(String fields) throws java.rmi.RemoteException;
    public void mudarLocalizacao(String set, int artistId) throws java.rmi.RemoteException;
    public int registarAtuacao(Atuacoes atuacao) throws java.rmi.RemoteException;
    public List<Atuacoes> filtroAtuacao(String fields) throws java.rmi.RemoteException;
    public List<Integer> idsPresentesArtista() throws java.rmi.RemoteException;
    public int enviarDonativo(Donativo donativo) throws java.rmi.RemoteException;
    public List<Donativo> listarDonativos(String fields) throws java.rmi.RemoteException;
    public int enviarClassificacao(Classificacao classificacao) throws java.rmi.RemoteException;
    public double ratingDoArtista(int artistID)  throws java.rmi.RemoteException;
    public void darAdmin(String username) throws java.rmi.RemoteException;
    public void alterarInformacoesArtista(String set, int artistId) throws java.rmi.RemoteException;


}
