/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author gui
 */
public class RemoteObjectImpl extends UnicastRemoteObject implements RemoteObject, java.io.Serializable {
    private static ConnectionDB bd = new ConnectionDB();
    public RemoteObjectImpl() throws java.rmi.RemoteException {
        super();
    }

    public int sendAd(Ad ad) throws java.rmi.RemoteException{
       return bd.insertIntoTableAdvertisement(ad);
    }

    public List<Ad> sendSearchFields(String fields) throws java.rmi.RemoteException {
        return bd.consultTableAdvertisement(fields);
    }

    public void changeState(String state, int aid) throws java.rmi.RemoteException {
        bd.alterTableAdvertisement(state, aid);
    }

    public void sendMessage(Message msg) throws java.rmi.RemoteException {
        bd.insertIntoTableMessages(msg);
    }

    public List<Message> showMessages(String fields) throws java.rmi.RemoteException {
        return bd.consultTableMessages(fields);
    }
}
