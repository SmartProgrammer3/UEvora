/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrendarquartos;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author gui
 */
public class ConnectionDB implements java.io.Serializable
{

    public static Statement stmt = null; //variável responsável sobretudo por realizar alterações nas tabelas da base de dados SQL
    public static Connection con; //variável responsável por estabelecer a comunicação com a base de dados pretendida

    /*
    função responsável por criar a conexão com a base de dados
     */
    public void connectDb() {


        try{
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection("jdbc:postgresql://alunos.di.uevora.pt/l48921", "l48921", "teste");
            stmt = con.createStatement();
            System.out.println("Conectado com sucesso");
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Problems setting the connection");
        }
    }

    /*
    função que trata de inserir valores na tabela advertisement da base de dados
     */
    public int insertIntoTableAdvertisement(Ad ad) {
        int aid = -1;
        try {
            stmt.executeUpdate("insert into advertisement values ('"
                    +ad.getAdvertiser()+ "', " +
                    "'" +ad.getType()+ "', " +
                    "'" +ad.getState()+ "', "+ ad.getPrice()+
                    ", '" +ad.getGender()+ "', " +
                    "'" +ad.getLocal()+ "', " +
                    "'" +ad.getTypology()+ "', " +
                    "'" +ad.getDate()+ "' );");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS number FROM advertisement");

            rs.next();
            aid = rs.getInt("number");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
        return aid;
    }
    /*
    função que trata de inserir valores na tabela messages da base de dados
     */

    public void insertIntoTableMessages(Message msg) {
        try {
            stmt.executeUpdate("insert into messages values ('"
                    +msg.getSender()+ "', " +
                    "'" +msg.getContent()+ "', " +
                    "'" +msg.getDate()+ "', " +
                     msg.getAid()+ ");");
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Problems on insert...");
        }
    }

    /*
    função que serve para consultar os valores na tabela advertisement
     */
    public List<Ad> consultTableAdvertisement(String fields) {
        List<Ad> results = new ArrayList<Ad>();
        Ad ad = null;
        try {
            String[] arr = fields.split("&");
            String aux = "";
            for(String i : arr) {
                String[] camps = i.split("=");
                String temp = "";
                if(camps[0].equals("aid") || camps[0].equals("price")) {
                    temp = "";
                    temp = camps[1];
                }
                else {
                    temp = "'";
                    for (int j = 1; j < camps.length; j++)
                        temp += camps[j];
                    temp += "'";
                }
                i = camps[0] + "=" + temp;
                aux += i + " AND ";
            }
            aux = aux.substring(0, aux.length() - 4);
            aux = aux.trim();
            System.out.println(aux);
            ResultSet rs = stmt.executeQuery("SELECT * FROM advertisement WHERE " + aux + ";");
            System.out.println("SELECT * FROM advertisement WHERE " + aux + ";");

            int count = 0;
            while (rs.next()) {
                String advertiser = rs.getString("advertiser");
                String typeAd = rs.getString("typead");
                String stateAd = rs.getString("statead");
                int price = rs.getInt("price");
                String gender = rs.getString("gender");
                String localAd = rs.getString("localad");
                String typology = rs.getString("typology");
                Date date = rs.getDate("date");
                int aid = rs.getInt("aid");

                ad = new Ad();
                ad.setAdvertiser(advertiser);
                ad.setType(typeAd);
                ad.setState(stateAd);
                ad.setPrice(price);
                ad.setGender(gender);
                ad.setLocal(localAd);
                ad.setTypology(typology);
                ad.setDate(date);
                ad.setAid(aid);

                results.add(ad);
                count++;
            }
            if(count == 0)
                System.out.println("Nenhum anúncio encontrado com as especificações desejadas!");
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        return results;
    }

    /*
    função que serve para consultar os valores na tabela messages
     */
    public List<Message> consultTableMessages(String fields) {
        List<Message> results = new ArrayList<Message>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE " + fields + ";");

            int count = 0;
            while(rs.next()) {
                String sender = rs.getString("sender");
                String content = rs.getString("content");
                Date date = rs.getDate("date");
                int aid = rs.getInt("aid");

                Message msg = new Message();
                msg.setSender(sender);
                msg.setContent(content);
                msg.setDate(date);
                msg.setAid(aid);

                results.add(msg);
                count++;
            }
            if(count == 0)
                System.out.println("Nenhuma mensagem encontrada!");
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Problems retrieving data from db...");
        }
        return results;
    }

    /*
    função que serve para alterar valores, nomeadamente o estado, na tabela advertisement
     */
    public void alterTableAdvertisement(String state, int aid) {
        try {
            stmt.executeUpdate("UPDATE advertisement SET statead='" + state + "' WHERE aid=" + aid + ";");

            System.out.println("Estado do anúncio " + aid + " alterado para " + state);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("Problems updating the table...");
        }
    }

    /*
    função que encerra a conexão com a base de dados
     */
    public void closeConnection() {
        try {
            stmt.close();
            con.close();
            System.out.println("Conexão com a base de dados terminada");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
