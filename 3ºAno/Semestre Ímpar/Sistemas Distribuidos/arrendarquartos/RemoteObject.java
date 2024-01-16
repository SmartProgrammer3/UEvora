/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package arrendarquartos;

import java.util.*;
/**
 *
 * @author gui
 */
public interface RemoteObject extends java.rmi.Remote{
    public int sendAd(Ad ad) throws java.rmi.RemoteException;
    public List<Ad> sendSearchFields(String fields) throws java.rmi.RemoteException;
    public void changeState(String state, int aid) throws java.rmi.RemoteException;
    public void sendMessage(Message msg) throws java.rmi.RemoteException;
    public List<Message> showMessages(String fields) throws java.rmi.RemoteException;
}
