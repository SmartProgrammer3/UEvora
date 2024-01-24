package trabalho;

import java.util.*;



public interface remoteObject extends java.rmi.Remote {
    public int enviarArtista(Artistas artista) throws java.rmi.RemoteException;
    public List<Artistas> filtroLocalizacaoEArte(String fields) throws java.rmi.RemoteException;
    public List<String> artistasAAtuar() throws java.rmi.RemoteException;
    public List<Artistas> listarArtistasPorEstado(String estado)throws java.rmi.RemoteException;
    public boolean aprovacao(int artistId) throws java.rmi.RemoteException;
    public void enviarDonativo(Donativo valor) throws java.rmi.RemoteException;
    public List<Integer> idsPresentesArtista() throws java.rmi.RemoteException;
    public List<Integer> retornarListaDonativosArtista(Integer id) throws java.rmi.RemoteException;
}
