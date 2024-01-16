/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

/**
 *
 * @author gui
 */
public class  Ad implements java.io.Serializable {
    public String advertiser;
    public String typeAd;
    public String stateAd;
    public int price;
    public String gender;
    public String localAd;
    public String typology;
    public Date date;

    int aid;

    public Ad() {
        super();
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public void setType(String type) {
        this.typeAd = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocal(String local) {
        this.localAd = local;
    }


    public void setTypology(String typology) {
        this.typology = typology;
    }

    public void setState(String state) {
        this.stateAd = state;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // get's

    public String getAdvertiser() {
        return advertiser;
    }

    public String getType() {
        return typeAd;
    }

    public String getState() {
        return stateAd;
    }

    public int getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    public String getLocal() {
        return localAd;
    }

    public String getTypology() {
        return typology;
    }

    public Date getDate() {
        return date;
    }

    public int getAid() {return aid;}
}
