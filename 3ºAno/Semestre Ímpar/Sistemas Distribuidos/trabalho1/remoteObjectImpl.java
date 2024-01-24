package trabalho;

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
    
    public int enviarArtista(Artistas artista) throws java.rmi.RemoteException {
        return bd.inserirTabelaArtistas(artista);
    }


    public List<Artistas> filtroLocalizacaoEArte(String fields) throws java.rmi.RemoteException{
        return bd.consultarTabelaArtistas(fields);
    }

    public List<String> artistasAAtuar() throws java.rmi.RemoteException {
        return bd.obterArtistasAAtuar();
    }

    public List<Integer> idsPresentesArtista() throws java.rmi.RemoteException {
        return bd.retornarIDSTabela();
    }

    public List<Artistas> listarArtistasPorEstado(String estado) throws java.rmi.RemoteException{
        return bd.ObterArtistasEstado(estado);
    }

    public boolean aprovacao(int artistId){
        return bd.aprovarArtista(artistId);
    }

    public void enviarDonativo(Donativo valor) throws java.rmi.RemoteException {
        bd.inserirTabelaDonativos(valor);
    }

    public List<Integer> retornarListaDonativosArtista(Integer id) throws java.rmi.RemoteException {
        return bd.donativosDeUmArtista(id);
    }
}



