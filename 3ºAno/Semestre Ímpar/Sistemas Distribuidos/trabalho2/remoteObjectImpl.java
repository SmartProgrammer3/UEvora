import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class remoteObjectImpl extends UnicastRemoteObject implements remoteObject, java.io.Serializable{
    private static ConexaoBD bd;

    // Constructor that takes host, db, user, and passe as parameters
    public remoteObjectImpl(String host, String db, String user, String passe) throws java.rmi.RemoteException {
        super();
        bd = new ConexaoBD(host, db, user, passe);
    }
    @Override

    public boolean registou(Utilizador utilizador) throws java.rmi.RemoteException{
        return bd.inserirTabelaUtilizador(utilizador);
    }

    public List<Utilizador> filtroUtilizadores(String fields) throws java.rmi.RemoteException{
        return bd.consultarTabelaUtilizadores(fields);
    }
    
    
    public int enviarArtista(Artistas artista) throws java.rmi.RemoteException {
        return bd.inserirTabelaArtistas(artista);
    }

    public List<Artistas> filtro(String fields) throws java.rmi.RemoteException{
        return bd.consultarTabelaArtistas(fields);
    }
        
    public void mudarLocalizacao(String set, int artistID) throws java.rmi.RemoteException{
        bd.localizacaoAlterar(set,artistID);
    }

    public int registarAtuacao(Atuacoes atuacao) throws java.rmi.RemoteException{
        return bd.inserirTabelaAtuacao(atuacao);
    }

    public List<Atuacoes> filtroAtuacao(String fields) throws java.rmi.RemoteException{
        return bd.consultarTabelaAtuacoes(fields);
    }

    public List<Integer> idsPresentesArtista() throws java.rmi.RemoteException {
        return bd.retornarIDSTabela();
    }
    

    public int enviarDonativo(Donativo donativo) throws java.rmi.RemoteException{
        return bd.inserirTabelaDonativos(donativo);
    }


    public List<Donativo> listarDonativos(String fields) throws java.rmi.RemoteException{
        return bd.consultarTabelaDonativos(fields);
    }


    public int enviarClassificacao(Classificacao classificacao)  throws java.rmi.RemoteException{
        return bd.inserirTabelaClassificacao(classificacao);
    }


    public double ratingDoArtista(int artistID)  throws java.rmi.RemoteException {
        return bd.mediaDoRatingDoArtista(artistID);
    }

    public void darAdmin(String username) throws java.rmi.RemoteException {
        bd.substituirPorAdmin(username);
    }

    public void alterarInformacoesArtista(String set, int artistId) throws java.rmi.RemoteException {
        bd.alterarArtista(set,artistId);
    }
}


 
