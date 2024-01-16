/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
/**
 *
 * @author gui
 */
public class Message implements java.io.Serializable
{
    String sender;
    String content;
    Date date;
    int aid;

    public Message() { super(); }

    public void setSender(String sender) { this.sender = sender; }
    public void setContent(String content) { this.content = content; }
    public void setDate(Date date) { this.date = date; }

    public void setAid(int aid) { this.aid = aid; }

    public String getSender(){
        return sender;
    }
    public String getContent(){
        return content;
    }
    public Date getDate(){
        return date;
    }
    public int getAid(){
        return aid;
    }
}
